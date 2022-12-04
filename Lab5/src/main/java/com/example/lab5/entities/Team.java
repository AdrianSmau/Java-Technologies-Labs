package com.example.lab5.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "teams")
@NamedNativeQueries({
        @NamedNativeQuery(name="updateTeamCity",query="update teams set city=(?) where name=(?)"),
        @NamedNativeQuery(name="updateTeamFounding",query="update teams set foundingDate=(?) where name=(?)"),
        @NamedNativeQuery(name="deleteTeam",query="delete from teams where city=(?)")
})
public class Team implements Serializable {
    @Id
    private String id;
    private String city;
    private String foundingDate;
    private String name;

    public Team() {
    }

    public Team(String id, String city, String foundingDate, String name) {
        this.id = id;
        this.city = city;
        this.foundingDate = foundingDate;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
