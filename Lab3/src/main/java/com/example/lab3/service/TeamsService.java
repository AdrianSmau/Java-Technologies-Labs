package com.example.lab3.service;

import com.example.lab3.entities.Team;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class TeamsService implements Serializable {
    private static final String url = "jdbc:postgresql://localhost:5433/Lab3-Java";

    private String chosenCity;
    private String chosenFoundingDate;
    private String chosenName;

    public List<Team> getTeams() throws SQLException {
        return fetchTeamsFromDB(getConnection());
    }

    public String addTeamAndCity() throws SQLException {
        Connection conn = getConnection();
        insertIntoDB(chosenName, chosenFoundingDate, chosenCity, conn);
        conn.close();
        return "/manage.xhtml?faces-redirect=true";
    }

    private void insertIntoDB(String teamName, String teamFoundationDate, String city, Connection conn) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("insert into cities values (?)");

        preparedStatement.setString(1, city);

        preparedStatement.execute();

        preparedStatement = conn.prepareStatement("insert into teams values (?,?,?)");

        preparedStatement.setString(1, teamName);
        preparedStatement.setString(2, teamFoundationDate);
        preparedStatement.setString(3, city);

        preparedStatement.execute();
    }

    public String deleteTeamAndCity(String cityName) throws SQLException {
        Connection conn = getConnection();
        deleteFromDB(cityName, conn);
        conn.close();
        return "/manage.xhtml?faces-redirect=true";
    }

    private void deleteFromDB(String cityName, Connection conn) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("delete from teams where city = (?)");

        preparedStatement.setString(1, cityName);

        preparedStatement.execute();

        preparedStatement = conn.prepareStatement("delete from cities where name = (?)");

        preparedStatement.setString(1, cityName);

        preparedStatement.execute();
    }

    private List<Team> fetchTeamsFromDB(Connection conn) throws SQLException {
        ResultSet resultSet = conn.createStatement().executeQuery("SELECT * from teams");
        List<Team> result = new ArrayList<>();
        while (resultSet.next()) {
            Team team = new Team(resultSet.getString("city"),
                    resultSet.getString("foundingDate"), resultSet.getString("name"));
            result.add(team);
        }
        return result;
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, "postgres", "123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
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
}
