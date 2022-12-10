package com.example.lab6.entities;

public class Match {
    private String team1Name;
    private String team2Name;
    private int weekNumber;
    private int startHour;
    private int endHour;

    public Match(String team1Name, String team2Name, int weekNumber, int startHour, int endHour) {
        this.team1Name = team1Name;
        this.team2Name = team2Name;
        this.weekNumber = weekNumber;
        this.startHour = startHour;
        this.endHour = endHour;
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
