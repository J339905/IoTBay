<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
            background-image: url('/css/Mountain-Background.jpeg');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
        }
        .container {
            max-width: 500px;
            padding: 15px;
            margin: auto;
            background: rgba(255, 255, 255, 0.8);
        }
    </style>
</head>
<body>
    <div class="container text-center">
        <% User user = (User) session.getAttribute("user"); %>
        <% if(user != null) { %>
            <h1>Welcome, <%= user.getfirstName() %>!</h1>
            <div class="mt-3">
                <a href="/productlist.jsp" class="btn btn-primary btn-lg">View Product List</a>
            </div>
        <% } else { %>
            <h1>Please login or register.</h1>
            <a href="login.jsp" class="btn btn-primary btn-lg">Login</a>
        <% } %>
    </div>
</body>
</html>
