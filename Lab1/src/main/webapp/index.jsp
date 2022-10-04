<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Main Page</title>
</head>
<body>
<h1><%= "Java Technologies - Lab 1" %>
</h1>
<br/>
<form action="my-servlet" method="GET">
    Please insert a word:
    <label>
        <input type="text" name="word"/>
    </label>
    Size (numbers only):
    <label>
        <input type="number" name="size" value="0" />
    </label>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>