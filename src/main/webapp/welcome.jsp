User
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
      
       
        body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
        background-image: url('/css/Mountain-Background.jpeg') center center/cover no-repeat fixed; /* Add your background image URL here */
        background-size: cover;
        background-position: center;
        background-attachment: fixed;
    }
    .container {
        max-width: 500px;
        padding: 15px;
        margin: auto;
        background: rgba(255, 255, 255, 0.8); /* Optional: Make container slightly transparent so text is easier to read */
    }
    .alert {
        margin-top: 20px;
    }
    </style>
</head>
<body>
    <div class="container text-center">
        <% User user = (User) session.getAttribute("user"); %>
        <% String logout = request.getParameter("logout"); %>
        <% if("true".equals(logout)) { %>
            <div class="alert alert-success" role="alert">
                You have been successfully logged out.
            </div>
        <% } %>
        <% if(user != null) { %>
            <h1 class="h3 mb-3 font-weight-normal">Welcome, <%= user.getName() %>!</h1>
            <p>Your email: <%= user.getEmail() %></p>
            <p>Your gender: <%= user.getGender() %></p>
            <p>Your favourite colour: <span style="color:<%= user.getFavCol() %>;">&#9632;</span></p>
            <!-- Logout button -->
            <a href="logout.jsp" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Logout</a>
        <% } else { %>
            <h1 class="h3 mb-3 font-weight-normal">Please login or register.</h1>
            <a href="login.jsp" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Login</a>
            <a href="register.jsp" class="btn btn-secondary btn-lg active" role="button" aria-pressed="true">Register</a>
        <% } %>
    </div>
    <!-- Bootstrap and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

