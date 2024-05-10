<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin User Management</title>
    <link rel="stylesheet" href="/css/admin.css">
</head>
<body>

    <!-- Navigation Bar -->
    <nav>
        <ul>
            <li><a href="/admin.jsp">AdminPage</a></li>
            <li><a href="/admin/createUser.jsp">Create User</a></li>
            <li><a href="/admin/viewUsers.jsp">View Users</a></li>
            <li><a href="/admin/searchUsers.jsp">Search Users</a></li>
            <li><a href="/admin/logout.jsp">Logout</a></li>
        </ul>
    </nav>

    <!-- Page Content -->
    <div class="content">
        <h1>Admin User Management</h1>
        <p>Manage customer and staff users from this page.</p>

        <!-- Actions Section -->
        <div class="actions">
            <a href="/admin/createUser.jsp" class="btn btn-primary">Create New User</a>
            <a href="/admin/viewUsers.jsp" class="btn btn-secondary">View All Users</a>
        </div>

        <!-- User Search Form -->
        <form action="searchUsers.jsp" method="get" class="search-form">
            <label for="search-name">Search by Full Name:</label>
            <input type="text" id="search-name" name="fullName">
            <label for="search-phone">Search by Phone Number:</label>
            <input type="text" id="search-phone" name="phoneNumber">
            <button type="submit" class="btn btn-search">Search</button>
        </form>
    </div>

</body>
</html>
