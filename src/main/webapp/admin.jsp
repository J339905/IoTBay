<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String role = (String) session.getAttribute("role");
    if (role == null || (!role.equals("Staff") && !role.equals("Admin"))) {
        response.sendRedirect("unauthorized.jsp"); 
        return;
    }
%>
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
            <li><a href="/productlistadmin">View Products</a></li>
            <li><a href="/admin/addProduct.jsp">Add Products</a></li>
            <li><a href="/LogoutServlet">Logout</a></li>
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
            <label for="search-first-name">First Name:</label>
            <input type="text" id="search-first-name" name="firstName">

            <label for="search-last-name">Last Name:</label>
            <input type="text" id="search-last-name" name="lastName">

            <label for="search-phone">Phone Number:</label>
            <input type="text" id="search-phone" name="phoneNumber">

            <button type="submit" class="btn btn-search">Search</button>
        </form>
    </div>

</body>
</html>
