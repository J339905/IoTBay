<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Product</title>
    <link rel="stylesheet" href="/css/admin.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .content {
            max-width: 600px;
            margin: 50px auto;
            background: white;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        .content h1 {
            text-align: center;
            color: #34495e;
        }
        .content p {
            text-align: center;
            color: #7f8c8d;
        }
        .product-form {
            display: flex;
            flex-direction: column;
        }
        .product-form label {
            margin: 10px 0 5px 0;
            color: #34495e;
        }
        .product-form input {
            padding: 10px;
            border: 1px solid #bdc3c7;
            border-radius: 5px;
            margin-bottom: 10px;
        }
        .btn {
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .btn-primary {
            background-color: #3498db;
            color: white;
        }
        .btn-primary:hover {
            background-color: #2980b9;
        }
        .btn-secondary {
            background-color: #2ecc71;
            color: white;
        }
        .btn-secondary:hover {
            background-color: #27ae60;
        }
    </style>
</head>
<body>
    <div class="content">
        <h1>Update Product</h1>
        <p>Update the details of the product below.</p>
        <form action="/updateProduct" method="post" class="product-form">
            <input type="hidden" id="id" name="id" value="${product.productid}">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${product.productname}" required>
            <label for="category">Category:</label>
            <input type="text" id="category" name="category" value="${product.productcategory}" required>
            <label for="description">Description:</label>
            <input type="text" id="description" name="description" value="${product.productdescription}" required>
            <label for="price">Price:</label>
            <input type="number" step="0.01" id="price" name="price" value="${product.productprice}" required>
            <label for="stock">Stock:</label>
            <input type="number" id="stock" name="stock" value="${product.productstock}" required>
            <button type="submit" class="btn btn-primary">Update Product</button>
        </form>
    </div>
</body>
</html>
