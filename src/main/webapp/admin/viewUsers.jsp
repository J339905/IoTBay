<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Users</title>
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

        .user-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .user-table th, .user-table td {
            border: 1px solid #bdc3c7;
            padding: 10px;
            text-align: left;
        }

        .user-table th {
            background-color: #2c3e50;
            color: white;
        }

        .user-table tbody tr:nth-child(even) {
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
        <h1>View All Users</h1>
        <p>Below is a list of all registered users:</p>

        <!-- User Table -->
        <table class="user-table">
            <thead>
                <tr>
                    <th>User ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Role</th>
                    <th>Gender</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <!-- Placeholder rows -->
                <tr>
                    <td>1</td>
                    <td>John</td>
                    <td>Doe</td>
                    <td>johndoe@example.com</td>
                    <td>1234567890</td>
                    <td>Admin</td>
                    <td>Male</td>
                    <td>
                        <a href="#" class="btn btn-secondary">Edit</a>
                        <a href="#" class="btn btn-primary">Delete</a>
                    </td>
                </tr>
                <!-- Add more static rows as placeholders -->
            </tbody>
        </table>
    </div>

</body>
</html>
