<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="uts.isd.model.User"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !(user.getRole().equalsIgnoreCase("Admin") || user.getRole().equalsIgnoreCase("Staff"))) {
        response.sendRedirect("/accessDenied.jsp"); // Redirect to access denied page if not logged in or not authorized
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create New Product</title>
    <link rel="stylesheet" href="/css/admin.css">
    <!-- Inline Styles for the form -->
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

        .product-form input,
        .product-form select {
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

    <!-- Navigation Bar -->
    <nav>
        <ul>
            <li><a href="/admin.jsp">AdminPage</a></li>
            <li><a href="/admin/createUser.jsp">Create User</a></li>
            <li><a href="/admin/viewUsers.jsp">View Users</a></li>
            <li><a href="/admin/searchUsers.jsp">Search Users</a></li>
            <li><a href="/listProductsAdmin">View Products</a></li>
            <li><a href="/admin/addProduct.jsp">Add Products</a></li>
            <li><a href="/admin/logout.jsp">Logout</a></li>
        </ul>
    </nav>

    <!-- Page Content -->
    <div class="content">
        <h1>Create a New Product</h1>
        <p>Fill out the form below to add a new product.</p>
        <form action="/createProduct" method="post" class="product-form">
            <% String errorMessage = (String) session.getAttribute("createProductError");
               if (errorMessage != null) {
                   session.removeAttribute("createProductError"); // Clear after display
            %>
            <div class="error-message"><%= errorMessage %></div>
            <% } %>

            <label for="name">Name:</label>
            <input type="text" id="name" name="name">

            <label for="category">Category:</label>
            <select id="category" name="category">
                <option value="">Select a category</option>
                <option value="Actuator">Actuator</option>
                <option value="Gateway">Gateway</option>
                <option value="Sensor">Sensor</option>
                <option value="Other">Other</option>
            </select>

            <label for="description">Description:</label>
            <input type="text" id="description" name="description">

            <label for="price">Price ($):</label>
            <input type="textr" step="0.01" id="price" name="price">

            <label for="stock">Stock:</label>
            <input type="text" id="stock" name="stock">

            <button type="submit" class="btn btn-primary">Create Product</button>
        </form>
    </div>

</body>
</html>
