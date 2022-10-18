<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP/JSF - Team addition</title>
</head>
<body>
<h1><%= "Java Technologies - Lab 3" %>
</h1>
<br/>
<form action="teams-servlet" method="POST">
    Insert Team name:
    <label>
        <input type="text" name="name"/>
    </label>
    Insert Team city:
    <label>
        <input type="text" name="city"/>
    </label>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>