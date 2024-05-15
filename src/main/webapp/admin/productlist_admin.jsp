<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        }

        .btn-cancel:hover {
            background-color: #31b0d5;
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
    <nav>
        <ul>
            <li><a href="/admin.jsp">AdminPage</a></li>
            <li><a href="/admin/createUser.jsp">Create User</a></li>
            <li><a href="/admin/viewUsers.jsp">View Users</a></li>
            <li><a href="/admin/searchUsers.jsp">Search Users</a></li>
            <li><a href="/listProductsAdmin">View Products</a></li>
            <li><a href="/admin/addProduct.jsp">Add Products</a></li>
            <li><a href="/admin/updateProduct.jsp">Update Products</a></li>
            <li><a href="/admin/logout.jsp">Logout</a></li>
        </ul>
    </nav>
    <div class="content">
        <h1>Product List - Admin</h1>
        <p>Below is a list of all products available:</p>
        <form action="listProductsAdmin" method="get" class="search-form">
            <input type="text" name="name" placeholder="Product Name" value="${param.name}">
            <input type="text" name="category" placeholder="Product Category" value="${param.category}">
            <input type="submit" class="btn" value="Search">
        </form>
        <table class="product-table">
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Stock</th>
                    <th>Actions</th>
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
            </tbody>
        </table>
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