
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <%-- <style>
        body { padding-top: 40px; padding-bottom: 40px; background-color: #f5f5f5; }
        .form-login {
             max-width: 330px;  
             padding: 15px;
             margin: auto;
}
    </style> --%>
    <%-- <style>
        body { 
            padding-top: 40px; 
            padding-bottom: 40px; 
            background-color: #f5f5f5; 
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
        .form-login {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .form-control {
            position: relative;
            box-sizing: border-box;
            height: auto;
            padding: 10px;
            font-size: 16px;
        }
        .form-control:focus {
            z-index: 2;
        }
        .form-register input[type="color"] {
            height: auto;
        }
        .form-register button {
            margin-top: 10px;
        }--%>
        <style> 
       body { 
            padding-top: 40px; 
            padding-bottom: 40px; 
            background-image: url('/css/Mountain-Background.jpeg'); 
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            background-color: #f5f5f5; 
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
        .form-login {
            width: 100%;
            max-width: 330px;
            padding: 10px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .form-control {
            position: relative;
            box-sizing: border-box;
            height: auto;
            padding: 10px;
            font-size: 16px;
            margin-bottom: 15px; /* Added space for visual comfort */
        }
        .form-control:focus {
            z-index: 2;
        }
        .form-login button {
            margin-top: 10px;
        }
        .back-home {
            display: block;
            width: 100%;
            margin-top: 20px; /* Space between register and back home button */
        }
    </style>
    <%-- </style> --%>
</head>
<body>
 <% 
        String loginErr = (String) session.getAttribute("loginErr");
        String nullErr = (String) session.getAttribute("nullErr");
    %>
<form class="form-login" method="POST" action="/LoginServlet" novalidate>
    <h1 class="h3 mb-3 font-weight-normal">Please Login</h1>

    <% if (nullErr != null) { %>
        <div class="alert alert-danger"><%= nullErr %></div>
    <% } %>


    

    <input type="email" name="email" class="form-control" placeholder="Email address" required autofocus>
    <input type="password" name="password" class="form-control" placeholder="Password" required>
 <% if(loginErr != null) { %>
<div class="alert alert-danger"><%= loginErr %></div>
<% } %>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
 <a href="index.jsp" class="btn btn-lg btn-secondary btn-block back-home">Back to Home</a>
    <div class="mt-3">Don't have an account? <a href="register.jsp">Click here</a></div>
</form>
