<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin User Management</title>
    <link rel="stylesheet" href="/css/admin.css">
</head>
<body>

    <nav>
        <ul>
            <li><a href="/admin.jsp">AdminPage</a></li>
            <li><a href="/admin/createUser.jsp">Create User</a></li>
            <li><a href="/admin/viewUsers.jsp">View Users</a></li>
            <li><a href="/admin/searchUsers.jsp">Search Users</a></li>
            <li><a href="/admin/logout.jsp">Logout</a></li>
            <li><a href=/productlist.jsp>View Product List</a></li>
            <li><a href="/admin/addproduct.jsp">Add New Product</a></li>
            <li><a href="/admin/removeproduct.jsp">Update Product</a></li>
        </ul>
    </nav>

    <div class="content">
        <h1>Admin User Management</h1>
        <p>Manage customer and staff users from this page.</p>

        <div class="actions">
            <a href="/admin/createUser.jsp" class="btn btn-primary">Create New User</a>
            <a href="/admin/viewUsers.jsp" class="btn btn-secondary">View All Users</a>
        </div>

        <form action="/SearchUserServlet" method="get" class="search-form">
            <label for="search-name">First Name:</label>
            <input type="text" id="search-name" name="firstName">

            <label for="search-name">Last Name:</label>
            <input type="text" id="search-name" name="lastName">

            <label for="search-phone">Phone Number:</label>
            <input type="text" id="search-phone" name="phoneNumber">

            <button type="submit" class="btn btn-search">Search</button>
        </form>

        <h2>Product Management</h2>
        <div class="actions">
            <a href="/addProduct.jsp" class="btn btn-success">Add New Product</a>
            <a href="/updateProduct.jsp" class="btn btn-warning">Update Product</a>
        </div>

        <a href="/productlist.jsp" class="btn btn-primary">View Product List</a>
        <a href="/searchProduct.jsp" class="btn btn-secondary">Search Products</a>
    </div>

</body>
</html>
