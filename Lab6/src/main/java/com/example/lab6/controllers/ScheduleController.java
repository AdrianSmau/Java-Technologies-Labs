package com.example.lab6.controllers;

import com.example.lab6.entities.Match;
import com.example.lab6.service.ScheduleAssignmentService;
import com.example.lab6.service.ScheduleInMemoryService;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@RequestScoped
public class ScheduleController implements Serializable {
    @EJB
    ScheduleInMemoryService inMemoryService;
    @EJB
    ScheduleAssignmentService scheduleAssignmentService;
    private String team1Name;
    private String team2Name;
    private int weekNumber;
    private int startHour;
    private int endHour;
    private boolean retourWanted;

    public List<Match> getMatches() {
        return inMemoryService.fetchMatches();
    }

    public boolean isRetourWanted() {
        return retourWanted;
    }

    public void setRetourWanted(boolean retourWanted) {
        this.retourWanted = retourWanted;
    }

    public String addMatch() {
        scheduleAssignmentService.addMatches(team1Name, team2Name, weekNumber, startHour, endHour, retourWanted);
        return "/manage.xhtml?faces-redirect=true";
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public void setTeam1Name(String team1Name) {
        this.team1Name = team1Name;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public void setTeam2Name(String team2Name) {
        this.team2Name = team2Name;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }
}
