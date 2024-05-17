<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="uts.isd.model.User"%>
<%
    String role = (String) session.getAttribute("role");
    if (role == null || (!role.equals("Staff"))) {
        response.sendRedirect("/unauthorized.jsp"); 
        return;
    }
%>
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
        .product-form input, .product-form select {
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
        .error-message {
            color: #e74c3c;
            margin: 10px 0;
            padding: 10px;
            background-color: #fee;
            border: 1px solid #e74c3c;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="content">
        <h1>Update Product</h1>
        <p>Fill out the form below to update the product.</p>
            <form action="/updateproduct" method="post" class="product-form">
                <% 
                    String errorMessage = (String) session.getAttribute("updateProductError");
                    if (errorMessage != null) {
                        session.removeAttribute("updateProductError");
                %>
                    <div class="error-message"><%= errorMessage %></div>
                <% } %>
            <input type="hidden" name="id" value="${product.productid}">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${product.productname}">
            <label for="category">Category:</label>
            <select id="category" name="category">
                <option value="Actuator" ${product.productcategory == 'Actuator' ? 'selected' : ''}>Actuator</option>
                <option value="Gateway" ${product.productcategory == 'Gateway' ? 'selected' : ''}>Gateway</option>
                <option value="Sensor" ${product.productcategory == 'Sensor' ? 'selected' : ''}>Sensor</option>
                <option value="Other" ${product.productcategory == 'Other' ? 'selected' : ''}>Other</option>
            </select>
            <label for="description">Description:</label>
            <input type="text" id="description" name="description" value="${product.productdescription}">
            <label for="price">Price:</label>
            <input type="int" step="0.01" id="price" name="price" value="${product.productprice}">
            <label for="stock">Stock:</label>
            <input type="int" id="stock" name="stock" value="${product.productstock}">
            <button type="submit" class="btn btn-primary">Update Product</button>
        </form>
    </div>
</body>
</html>
