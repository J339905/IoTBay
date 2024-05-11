<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Create Product</title>
</head>
<body>
    <h1>Create Product</h1>
    <form action="CreateProductServlet" method="post">
        Name: <input type="text" name="name" required><br>
        Type: <input type="text" name="type" required><br>
        Description: <input type="text" name="description"><br>
        Price: <input type="number" name="price" step="0.01" required><br>
        Stock: <input type="number" name="stock" required><br>
        <input type="submit" value="Submit">
    </form>
    <a href="ProductList.jsp">Back to Product List</a>
</body>
</html>
