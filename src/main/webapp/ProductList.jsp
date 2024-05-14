<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="uts.isd.model.Product"%>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <title>Product List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
    </style>
</head>
<body>
    <h1>Product List</h1>
    <table>
        <tr>
            <th>Product ID</th>
            <th>Name</th>
            <th>Category</th>
            <th>Description</th>
            <th>Price</th>
            <th>Stock</th>
        </tr>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.productid}</td>
                <td>${product.productname}</td>
                <td>${product.productcategory}</td>
                <td>${product.productdescription}</td>
                <td>${product.productprice}</td>
                <td>${product.productstock}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
