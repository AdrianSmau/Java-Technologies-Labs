<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Result Page</title>
</head>
<body>
<h1><%= "Java Technologies - Lab 2 Result Page" %>
</h1>
<br/>
<%
    Cookie[] cookies = request.getCookies();
    String name = "IntelliJ User";
    if (cookies != null) {
        for (Cookie c : cookies) {
            if (c.getName().equals("userName")) {
                name = c.getValue();
            }
        }
    }
%>
<h2>
    <%= "[COOKIE] Welcome back, " + name + "!" %>
</h2>
<br/>
<%
    Set<String> permutations = (Set<String>) request.getAttribute("permutationList");
%>
<h2><%= "Java Technologies - Result Table" %>
</h2>
<br/>
<ul>
    <%
        for (String entry : permutations) {
    %>
    <li>
        <%= entry %>
    </li>
    <%
        }
    %>
</ul>
</body>
</html>
