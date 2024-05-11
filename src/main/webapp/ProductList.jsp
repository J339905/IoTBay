<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uts.isd.model.Product"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Product List</title>
</head>
<body>
    <h1>Product List</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Type</th>
                <th>Description</th>
                <th>Price</th>
                <th>Stock</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${productList}" var="product">
                <tr>
                    <td><c:out value="${product.productid}"/></td>
                    <td><c:out value="${product.productName}"/></td>
                    <td><c:out value="${product.productType}"/></td>
                    <td><c:out value="${product.description}"/></td>
                    <td><c:out value="${product.price}"/></td>
                    <td><c:out value="${product.quantity}"/></td>
                    <td>
                        <a href="UpdateProduct.jsp?id=${product.productid}">Edit</a>
                        <a href="DeleteProductServlet?id=${product.productid}" onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="CreateProduct.jsp">Add New Product</a>
</body>
</html>
