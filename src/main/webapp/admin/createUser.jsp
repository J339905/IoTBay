<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String role = (String) session.getAttribute("role");
    if (role == null || (!role.equals("Admin"))) {
        response.sendRedirect("/unauthorized.jsp"); 
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create New User</title>
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

        .user-form {
            display: flex;
            flex-direction: column;
        }

        .user-form label {
            margin: 10px 0 5px 0;
            color: #34495e;
        }

        .user-form input,
        .user-form select {
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

    <nav>
        <ul>
            <li><a href="/admin.jsp">AdminPage</a></li>
            <li><a href="/admin/createUser.jsp">Create User</a></li>
            <li><a href="/admin/viewUsers.jsp">View Users</a></li>
            <li><a href="/admin/searchUsers.jsp">Search Users</a></li>
            <li><a href="/LogoutServlet">Logout</a></li>
        </ul>
    </nav>

     <% 
        String emailErr = (String) session.getAttribute("emailErr");
        String nametypeErr = (String) session.getAttribute("nametypeErr");
        String nullErr = (String) session.getAttribute("nullErr");
        String phoneErr = (String) session.getAttribute("phoneErr");
        String passwordErr = (String) session.getAttribute("passwordErr");
        String userexistsErr = (String) session.getAttribute("userexistsErr");
    %>

    <div class="content">
        <h1>Create a New User</h1>
        <p>Fill out the form below to add a new customer or staff member.</p>

        <% if (nullErr != null) { %>
            <div class="alert alert-danger"><%= nullErr %></div>
        <% } %>
        <% if(userexistsErr != null) { %>
            <div class="alert alert-danger"><%= userexistsErr
            %></div>
        <% } %>

        <form action="/AdminCreateUserServlet" method="post" class="user-form">
            <label for="first-name">First Name:</label>
            <input type="text" id="first-name" name="firstname" required>

            <label for="last-name">Last Name:</label>
            <input type="text" id="last-name" name="lastname" required>
            <% if(nametypeErr != null) { %>
                <div class="alert alert-danger"><%= nametypeErr %></div>
            <% } %>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            <% if(emailErr != null) { %>
                <div class="alert alert-danger"><%= emailErr %></div>
            <% } %>

            <label for="phone-number">Phone Number:</label>
            <input type="text" id="phone-number" name="phone" required>
            <% if(phoneErr != null) { %>
                <div class="alert alert-danger"><%= phoneErr %></div>
            <% } %>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <% if(passwordErr != null) { %>
                <div class="alert alert-danger"><%= passwordErr %></div>
            <% } %>

            <label for="gender">Gender:</label>
            <select id="gender" name="gender" required>
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="other">Other</option>
            </select>

            <label for="role">Role:</label>
            <select id="role" name="role" required>
                <option value="Customer">Customer</option>
                <option value="Staff">Staff</option>
            </select>

            <button type="submit" class="btn btn-primary">Create User</button>
        </form>
    </div>

</body>
</html>
