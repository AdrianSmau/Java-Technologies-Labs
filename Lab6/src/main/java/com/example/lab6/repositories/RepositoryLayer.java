package com.example.lab6.repositories;

import com.example.lab6.entities.City;
import com.example.lab6.entities.Team;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Stateless
@Transactional
public class RepositoryLayer {
    @PersistenceContext(unitName = "lab-6")
    EntityManager em;

    public void insertIntoDB(String teamName, String teamFoundationDate, String city) {
        City cityToBeInserted = new City(UUID.randomUUID().toString(), city);
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(cityToBeInserted);

        Team teamToBeInserted = new Team(UUID.randomUUID().toString(), city, teamFoundationDate, teamName);
        em.persist(teamToBeInserted);

        et.commit();
    }

    public void updateFromDB(String teamName, String teamFoundationDate, String city) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        List<City> existentCity = em.createNativeQuery("SELECT * from cities where name=(?)", City.class).setParameter(1, city).getResultList();
        if (existentCity.size() == 0) {
            City cityToBeInserted = new City(UUID.randomUUID().toString(), city);
            em.persist(cityToBeInserted);
        }

        em.createNamedQuery("updateTeamCity").setParameter(1, city).setParameter(2, teamName).executeUpdate();

        em.createNamedQuery("updateTeamFounding").setParameter(1, teamFoundationDate).setParameter(2, teamName).executeUpdate();
        et.commit();
    }

    public void deleteFromDB(String cityName) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.createNamedQuery("deleteTeam").setParameter(1, cityName).executeUpdate();

        em.createNamedQuery("deleteCity").setParameter(1, cityName).executeUpdate();
        et.commit();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Team> fetchTeamsFromDB() {
        return em.createNativeQuery("SELECT * FROM teams", Team.class).getResultList();
    }
}
