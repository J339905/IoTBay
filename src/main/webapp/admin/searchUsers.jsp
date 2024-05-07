<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Users</title>
    <link rel="stylesheet" href="/css/admin.css">

    <!-- Inline Styles -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .content {
            max-width: 800px;
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

        .search-form {
            display: flex;
            flex-direction: column;
            gap: 10px;
            margin-top: 20px;
        }

        .search-form label, .search-form input, .search-form button {
            font-size: 16px;
        }

        .search-form input {
            padding: 10px;
            border: 1px solid #bdc3c7;
            border-radius: 5px;
        }

        .btn {
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            text-align: center;
        }

        .btn-search {
            background-color: #3498db;
            color: white;
        }

        .btn-search:hover {
            background-color: #2980b9;
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
            <li><a href="/admin/logout.jsp">Logout</a></li>
        </ul>
    </nav>

    <!-- Page Content -->
    <div class="content">
        <h1>Search Users</h1>
        <p>Search for users by full name or phone number:</p>

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
