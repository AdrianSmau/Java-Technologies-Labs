package com.example.lab5.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cities")
@NamedNativeQueries({
        @NamedNativeQuery(name="deleteCity",query="delete from cities where name=(?)")
})
public class City implements Serializable {
    @Id
    private String id;
    private String name;

    public City(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public City() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
