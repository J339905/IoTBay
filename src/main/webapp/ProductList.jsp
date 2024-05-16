<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        h1 {
            margin-top: 20px;
            color: #333;
        }
        table {
            width: 90%;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 12px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #007BFF;
            border: none;
            border-radius: 4px;
            text-decoration: none;
            margin: 20px 0;
            transition: background-color 0.3s;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .search-form {
            margin-bottom: 20px;
        }
        .search-form input[type="text"] {
            padding: 8px;
            margin-right: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .checkbox-control {
            display: flex;
            align-items: center;
            justify-content: center;
        }
    </style>
</head>
<body>
    <h1>Product List</h1>
    <form action="listProducts" method="get" class="search-form">
        <input type="text" name="name" placeholder="Product Name" value="${param.name}">
        <input type="text" name="category" placeholder="Product Category" value="${param.category}">
        <input type="submit" class="btn" value="Search">
    </form>
    <form action="addToCart" method="post">
        <table>
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Stock</th>
                    <th>Select</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td>${product.productid}</td>
                        <td>${product.productname}</td>
                        <td>${product.productcategory}</td>
                        <td>${product.productdescription}</td>
                        <td>${product.productprice}</td>
                        <td>${product.productstock}</td>
                        <td>
                            <input type="checkbox" name="selectedProduct_${product.productid}"
                                   value="${product.productid}" ${product.productstock == 0 ? 'disabled' : ''} ${sessionScope.selectedProductIds.contains(product.productid) ? 'disabled' : ''}>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <input type="submit" class="btn" value="Order Selected Products">
    </form>
    <a href="dashboard.jsp" class="btn">Return to Dashboard</a>
</body>
</html>
