package com.example.lab6.service;

import com.example.lab6.entities.Team;
import com.example.lab6.repositories.RepositoryLayer;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@RequestScoped
public class ServiceLayer implements Serializable {
    @EJB
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

    public void goToSchedule() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("schedule.xhtml");
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
