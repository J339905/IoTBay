
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <!-- Link to the external CSS file -->
    <link rel="stylesheet" href="css/register.css">
</head>
<body>
    <div class="registration-form">
    <h2>LOGIN FORM</h2>
       
    <form action="LoginController" method="post">
        <div class="form-control">
            <label for="email">Email:</label>
            <input type="email" name="email" id="email" placeholder="Enter your email" required>
        </div>

        <div class="form-control">
            <label for="password">Password:</label>
            <input type="password" name="password" id="password" placeholder="Create a password" required>
        </div>

        <button type="submit">Register Account</button>
        <a href="/">Cancel Registration</a>
    </form>
    </div>
</body>
</html>