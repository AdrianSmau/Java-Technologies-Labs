package com.example.lab3;

import com.example.lab3.entities.City;
import com.example.lab3.entities.Team;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "teamsServlet", value = "/teams-servlet")
public class TeamsServlet extends HttpServlet {
    private static final String url = "jdbc:postgresql://localhost:5433/Lab3-Java";

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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = getConnection();
        response.setContentType("text/html");
        String teamName = request.getParameter("name");
        OffsetDateTime teamFoundationDate = OffsetDateTime.now();
        String city = request.getParameter("city");

        try {
            insertIntoDB(teamName, teamFoundationDate, city, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            List<Team> currentTeams = getTeams(conn);
            request.setAttribute("teams", currentTeams);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("result.jsp").forward(request, response);
    }

    private void insertIntoDB(String teamName, OffsetDateTime teamFoundationDate, String city, Connection conn) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("insert into cities values (?)");

        preparedStatement.setString(1, city);

        preparedStatement.execute();

        preparedStatement = conn.prepareStatement("insert into teams (name, foundingDate, city) values (?,?,?)");

        preparedStatement.setString(1, teamName);
        preparedStatement.setString(2, teamFoundationDate.toString());
        preparedStatement.setString(3, city);

        preparedStatement.execute();
    }

    private List<Team> getTeams(Connection conn) throws SQLException {
        ResultSet resultSet = conn.createStatement().executeQuery("SELECT * from teams");
        List<Team> result = new ArrayList<>();
        while (resultSet.next()) {
            Team team = new Team(new City(resultSet.getString("city")),
                    OffsetDateTime.parse(resultSet.getString("foundingDate")), resultSet.getString("name"));
            result.add(team);
        }
        return result;
    }

    private List<City> getCities(Connection conn) throws SQLException {
        ResultSet resultSet = conn.createStatement().executeQuery("SELECT * from cities");
        List<City> result = new ArrayList<>();
        while (resultSet.next()) {
            City city = new City(resultSet.getString("name"));
            result.add(city);
        }
        return result;
    }
}