package com.example.lab5.service;

import com.example.lab5.entities.Team;
import com.example.lab5.repositories.RepositoryLayer;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@RequestScoped
public class ServiceLayer implements Serializable {
    @Inject
    RepositoryLayer repositoryLayer;
    private String chosenCity;
    private String chosenFoundingDate;
    private String chosenName;

    public void goToManage() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("manage.xhtml");
    }

    public void goToUpdate() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("update.xhtml");
    }

    public List<Team> getTeams() {
        return repositoryLayer.fetchTeamsFromDB();
    }

    public String addTeamAndCity() {
        repositoryLayer.insertIntoDB(chosenName, chosenFoundingDate, chosenCity);
        return "/manage.xhtml?faces-redirect=true";
    }

    public String updateTeamAndCity() {
        repositoryLayer.updateFromDB(chosenName, chosenFoundingDate, chosenCity);
        return "/manage.xhtml?faces-redirect=true";
    }

    public String deleteTeamAndCity(String cityName) {
        repositoryLayer.deleteFromDB(cityName);
        return "/manage.xhtml?faces-redirect=true";
    }

    public String getChosenCity() {
        return chosenCity;
    }

    public void setChosenCity(String chosenCity) {
        this.chosenCity = chosenCity;
    }

    public String getChosenName() {
        return chosenName;
    }

    public void setChosenName(String chosenName) {
        this.chosenName = chosenName;
    }

    public String getChosenFoundingDate() {
        return chosenFoundingDate;
    }

    public void setChosenFoundingDate(String chosenFoundingDate) {
        this.chosenFoundingDate = chosenFoundingDate;
    }

    public void setRepositoryLayer(RepositoryLayer repositoryLayer) {
        this.repositoryLayer = repositoryLayer;
    }
}
