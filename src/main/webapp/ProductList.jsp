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
        .quantity-control {
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .quantity-control input {
            width: 40px;
            text-align: center;
            border: 1px solid #ddd;
            margin: 0 5px;
            line-height: 1.5;
        }
        .quantity-control button {
            border: 1px solid #ddd;
            background-color: #fff;
            color: #007BFF;
            font-size: 18px;
            width: 30px;
            height: 30px;
            cursor: pointer;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 0;
        }
        .quantity-control button:hover {
            background-color: #f2f2f2;
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
    </style>
</head>
<body>
    <h1>Product List</h1>
    <form action="listProducts" method="get" class="search-form">
        <input type="text" name="name" placeholder="Product Name" value="${param.name}">
        <input type="text" name="category" placeholder="Product Category" value="${param.category}">
        <input type="submit" class="btn" value="Search">
    </form>
    <form action="orderForm.jsp" method="post">
        <table>
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Stock</th>
                    <th>Order Quantity</th>
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
                            <div class="quantity-control">
                                <button type="button" onclick="decrementQuantity(${product.productid})">-</button>
                                <input type="number" name="orderQuantity_${product.productid}" id="orderQuantity_${product.productid}" value="0" min="0">
                                <button type="button" onclick="incrementQuantity(${product.productid})">+</button>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <input type="submit" class="btn" value="Order Selected Products">
    </form>
    <a href="dashboard.jsp" class="btn">Return to Dashboard</a>

    <script>
        function incrementQuantity(productId) {
            var quantityInput = document.getElementById('orderQuantity_' + productId);
            quantityInput.value = parseInt(quantityInput.value) + 1;
        }

        function decrementQuantity(productId) {
            var quantityInput = document.getElementById('orderQuantity_' + productId);
            if (quantityInput.value > 0) {
                quantityInput.value = parseInt(quantityInput.value) - 1;
            }
        }
    </script>
</body>
</html>
