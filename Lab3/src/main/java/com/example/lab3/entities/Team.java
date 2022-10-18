package com.example.lab3.entities;

import java.time.OffsetDateTime;

public class Team {
    private City city;
    private OffsetDateTime foundingDate;
    private String name;

    public Team(City city, OffsetDateTime foundingDate, String name) {
        this.city = city;
        this.foundingDate = foundingDate;
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public OffsetDateTime getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(OffsetDateTime foundingDate) {
        this.foundingDate = foundingDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Team{" +
                "city=" + city +
                ", foundingDate=" + foundingDate +
                ", name='" + name + '\'' +
                '}';
    }
}
