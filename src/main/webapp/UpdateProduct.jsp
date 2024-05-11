<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.Product"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Product</title>
</head>
<body>
    <h1>Update Product</h1>
    <form action="UpdateProductServlet" method="post">
        <input type="hidden" name="id" value="${product.productid}">
        Name: <input type="text" name="name" value="${product.productName}" required><br>
        Type: <input type="text" name="type" value="${product.productType}" required><br>
        Description: <input type="text" name="description" value="${product.description}"><br>
        Price: <input type="number" name="price" value="${product.price}" step="0.01" required><br>
        Stock: <input type="number" name="stock" value="${product.quantity}" required><br>
        <input type="submit" value="Update">
    </form>
    <a href="ProductList.jsp">Back to Product List</a>
</body>
</html>
