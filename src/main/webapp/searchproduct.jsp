<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.Product"%>
<!DOCTYPE html>
<html>
<head>
    <title>Search Products</title>
</head>
<body>
    <h1>Search Products</h1>
    <form action="SearchProductServlet" method="get">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name"><br>
        <label for="type">Type:</label>
        <input type="text" id="type" name="type"><br>
        <button type="submit">Search</button>
    </form>
</body>
</html>
