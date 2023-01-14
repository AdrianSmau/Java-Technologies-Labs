package com.example.lab8.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "documents")
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    private String authors;
    @NotNull
    @Size(min = 1, max = 500)
    private String content;
    private String registrationNumber;

    public Document(String name, String authors, String content, String registrationNumber) {
        this.name = name;
        this.authors = authors;
        this.content = content;
        this.registrationNumber = registrationNumber;
    }

    public Document() {
    }

    public int getId() {
        return id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
