package com.example.lab6.service;

import com.example.lab6.entities.Match;
import com.example.lab6.entities.Team;
import com.example.lab6.repositories.RepositoryLayer;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.List;
import java.util.Objects;

@Stateful
public class ScheduleAssignmentService {
    @EJB
    ScheduleInMemoryService inMemoryService;
    @EJB
    WeekAvailabilityService weekAvailabilityService;
    @EJB
    RepositoryLayer repositoryLayer;

    public void addMatches(String team1, String team2, int weekNumber, int startHour, int endHour, boolean isRetourWanted) {
        if (!weekAvailabilityService.checkAvailability(weekNumber, startHour, endHour)) {
            System.out.println("Time frame not available!");
            return;
        }
        int retourWeek = -1;
        if (isRetourWanted) {
            retourWeek = weekAvailabilityService.findRetourWeek(weekNumber, startHour, endHour);
        }
        List<Team> teams = repositoryLayer.fetchTeamsFromDB();
        if (teams.stream().noneMatch(entry -> Objects.equals(entry.getName(), team1)) || teams.stream().noneMatch(entry -> Objects.equals(entry.getName(), team2))) {
            System.out.println("One or both inserted teams do not exist!");
            return;
        }
        inMemoryService.addMatch(new Match(team1, team2, weekNumber, startHour, endHour));
        if (retourWeek != -1) {
            inMemoryService.addMatch(new Match(team1, team2, retourWeek, startHour, endHour));
        }
    }
}
