package com.example.lab6.service;

import com.example.lab6.entities.Match;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ScheduleInMemoryService {
    private final List<Match> scheduledMatches = new ArrayList<>();

    public void addMatch(Match match){
        scheduledMatches.add(match);
    }

    public List<Match> fetchMatches(){
        return scheduledMatches;
    }
}
