<%@ page import="java.util.Set" %>
<%@ page import="com.example.lab3.entities.Team" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Result Page</title>
</head>
<body>
<h1><%= "Java Technologies - Lab 3 Teams view Page" %>
</h1>
<br/>
<%
    List<Team> teams = (List<Team>) request.getAttribute("teams");
%>
<h2><%= "Java Technologies - Teams Table" %>
</h2>
<br/>
<ul>
    <%
        for (Team team : teams) {
    %>
    <li>
        <%= teams %>
    </li>
    <%
        }
    %>
</ul>
</body>
</html>