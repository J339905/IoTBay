<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>Product List - Admin</title>
    <link rel="stylesheet" href="/css/admin.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .content {
            max-width: 1350px;
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

        .product-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .product-table th, .product-table td {
            border: 1px solid #bdc3c7;
            padding: 15px;
            text-align: center;
        }

        .product-table th {
            background-color: #2c3e50;
            color: white;
        }

        .product-table tbody tr:nth-child(even) {
            background-color: #f4f4f4;
        }

        .btn {
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            text-align: center;
            background-color: #3498db;
        }

        .btn-update {
            background-color: #5bc0de;
            color: white;
        }

        .btn-update:hover {
            background-color: #31b0d5;
        }

        .btn-delete {
            background-color: #d9534f;
            color: white;
        }

        .btn-delete:hover {
            background-color: #c9302c;
        }

        .btn-primary {
            background-color: #3498db;
            color: white;
        }

        .btn-primary:hover {
            background-color: #2980b9;
        }

        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
        }

        .modal-content {
            background-color: #fff;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            max-width: 400px;
            border-radius: 10px;
            text-align: center;
        }

        .modal-buttons {
            margin-top: 20px;
        }

        .btn-confirm {
            background-color: #d9534f;
            color: white;
        }

        .btn-confirm:hover {
            background-color: #c9302c;
        }

        .btn-cancel {
            background-color: #5bc0de;
            color: white;
            padding: 10px 30px; /* Increase padding for more horizontal space */
            min-width: 100px;   /* Optional: increase min-width for a wider button */
        }

        .btn-cancel:hover {
            background-color: #31b0d5;
        }
        .search-form {
            margin-bottom: 20px;
        }
        .search-form input[type="text"], .search-form select {
            padding: 8px;
            margin-right: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
    </style>
</head>
<body>
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
    <div class="content">
        <h1>Product List - Admin</h1>
        <p>Below is a list of all products available:</p>
        <form action="listProductsAdmin" method="get" class="search-form">
            <input type="text" name="name" placeholder="Product Name" value="${param.name}">
            <select name="category">
                <option value="">All Categories</option>
                <option value="Actuator" ${param.category == 'Actuator' ? 'selected' : ''}>Actuator</option>
                <option value="Gateway" ${param.category == 'Gateway' ? 'selected' : ''}>Gateway</option>
                <option value="Sensor" ${param.category == 'Sensor' ? 'selected' : ''}>Sensor</option>
                <option value="Other" ${param.category == 'Other' ? 'selected' : ''}>Other</option>
            </select>
            <input type="submit" class="btn" value="Search">
        </form>
        <table class="product-table">
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Description</th>
                    <th>Price ($)</th>
                    <th>Stock</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty products}">
                        <c:forEach var="product" items="${products}">
                            <tr>
                                <td>${product.productid}</td>
                                <td>${product.productname}</td>
                                <td>${product.productcategory}</td>
                                <td>${product.productdescription}</td>
                                <td>${product.productprice}</td>
                                <td>${product.productstock}</td>
                                <td class="action-buttons">
                                    <form action="/updateProduct" method="get" style="display:inline;">
                                        <input type="hidden" name="id" value="${product.productid}">
                                        <button type="submit" class="btn btn-update">Update</button>
                                    </form>
                                    <form id="deleteForm-${product.productid}" action="/deleteProduct" method="post" style="display:inline;">
                                        <input type="hidden" name="id" value="${product.productid}">
                                        <button type="button" class="btn btn-delete" onclick="confirmDelete(${product.productid})">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="7" class="not-found">Product not found</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
        <div style="text-align: center; margin-top: 20px;">
            <a href="/admin/addProduct.jsp" class="btn btn-primary" style="padding: 10px 50px; min-width: 140px;">Add New Product</a>   
        </div>
    </div>

    <!-- The Modal -->
    <div id="confirmModal" class="modal">
        <div class="modal-content">
            <h2>Confirm Deletion</h2>
            <p>Are you sure you want to delete this? This cannot be undone.</p>
            <div class="modal-buttons">
                <button id="confirmBtn" class="btn btn-confirm">Yes, I am sure</button>
                <button onclick="closeModal()" class="btn btn-cancel">No</button>
            </div>
        </div>
    </div>

    <script>
        let formToSubmit;

        function confirmDelete(productId) {
            formToSubmit = document.getElementById('deleteForm-' + productId);
            document.getElementById('confirmModal').style.display = 'block';
        }

        function closeModal() {
            document.getElementById('confirmModal').style.display = 'none';
        }

        document.getElementById('confirmBtn').onclick = function() {
            formToSubmit.submit();
        }
    </script>
</body>
</html>
