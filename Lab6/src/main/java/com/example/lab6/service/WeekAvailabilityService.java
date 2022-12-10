package com.example.lab6.service;

import com.example.lab6.entities.Match;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class WeekAvailabilityService {
    @EJB
    ScheduleInMemoryService inMemoryService;

    public boolean checkAvailability(int weekNumber, int startHour, int endHour) {
        List<Match> sameWeekMatches = inMemoryService.fetchMatches().stream().filter(entry -> entry.getWeekNumber() == weekNumber).collect(Collectors.toList());
        if (sameWeekMatches.size() == 0)
            return true;
        long overlappingMatches = sameWeekMatches.stream().filter(entry -> ((entry.getStartHour() < startHour && entry.getEndHour() > startHour) || (entry.getStartHour() > startHour && entry.getStartHour() < endHour))).count();
        return overlappingMatches == 0L;
    }

    public int findRetourWeek(int tourWeek, int startHour, int endHour) {
        boolean weekFound = false;
        int currentWeek = 0;
        while (!weekFound) {
            if (tourWeek != currentWeek && checkAvailability(currentWeek, startHour, endHour))
                weekFound = true;
            else
                currentWeek++;
        }
        return currentWeek;
    }
}
