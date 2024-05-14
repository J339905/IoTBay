<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <link rel="stylesheet" href="/css/admin.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .content {
            max-width: 1200px;
            margin: 20px auto;
            background: white;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        .content h1 {
            text-align: center;
            color: #34495e;
            font-size: 24px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            font-size: 16px;
        }

        th, td {
            border: 1px solid #bdc3c7;
            padding: 20px;
            text-align: left;
        }

        th {
            background-color: #2c3e50;
            color: white;
        }

        tbody tr:nth-child(even) {
            background-color: #f4f4f4;
        }

        .btn {
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            text-align: center;
            font-size: 16px;
        }

        .btn-primary {
            background-color: #3498db;
            color: white;
        }

        .btn-primary:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <div class="content">
        <h1>Product List</h1>
        <c:if test="${not empty productList}">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Stock</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${productList}" var="product">
                        <tr>
                            <td><c:out value="${product.productid}"/></td>
                            <td><c:out value="${product.productname}"/></td>
                            <td><c:out value="${product.productcategory}"/></td>
                            <td><c:out value="${product.productdescription}"/></td>
                            <td><c:out value="${product.productprice}"/></td>
                            <td><c:out value="${product.productstock}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty productList}">
            <p>No products found.</p>
        </c:if>
    </div>
</body>
</html>
