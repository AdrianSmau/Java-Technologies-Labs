package com.example.lab4.entities;

import java.io.Serializable;

public class Team implements Serializable {
    private String city;
    private String foundingDate;
    private String name;

    public Team() {
    }

    public Team(String city, String foundingDate, String name) {
        this.city = city;
        this.foundingDate = foundingDate;
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(String foundingDate) {
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
        return "[Team " + name + "] City of the team is " + city + ", and the founding date is " + foundingDate + "!";
    }
}
