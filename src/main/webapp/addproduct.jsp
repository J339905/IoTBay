<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.Product"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
    <h1>Add New Product</h1>
    <form action="AddProductServlet" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name"><br>
        <label for="type">Type:</label>
        <input type="text" id="type" name="type"><br>
        <label for="description">Description:</label>
        <input type="text" id="description" name="description"><br>
        <label for="price">Price:</label>
        <input type="text" id="price" name="price"><br>
        <label for="stock">Stock:</label>
        <input type="text" id="stock" name="stock"><br>
        <button type="submit">Add Product</button>
    </form>
</body>
</html>
