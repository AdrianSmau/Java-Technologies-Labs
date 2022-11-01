package com.example.lab4.service;

import com.example.lab4.entities.Team;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ManagedBean
@RequestScoped
public class TeamsService implements Serializable {
    private static final String url = "jdbc:postgresql://localhost:5433/Lab3-Java";

    private String chosenCity;
    private String chosenFoundingDate;
    private String chosenName;

    public List<Team> getTeams() throws SQLException {
        return fetchTeamsFromDB(Objects.requireNonNull(getConnection()));
    }

    public String addTeamAndCity() throws SQLException {
        Connection conn = getConnection();
        assert conn != null;
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
        assert conn != null;
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

    private static Connection getConnection() {
        InitialContext ctx;
        try {
            ctx = new javax.naming.InitialContext();
            javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup("jdbc/demo");
            return ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            return null;
        }
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
