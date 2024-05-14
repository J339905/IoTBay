
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
      
        .form-register {
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
            margin-bottom: 15px; 
        }
        .form-control:focus {
            z-index: 2;
        }
        .form-register button {
            margin-top: 10px;
        }
        .back-home {
            display: block;
            width: 100%;
            margin-top: 20px; /
        }
    </style>
</head>
<body>
 <% 
        String emailErr = (String) session.getAttribute("emailErr");
        String nametypeErr = (String) session.getAttribute("nametypeErr");
        String nullErr = (String) session.getAttribute("nullErr");
        String phoneErr = (String) session.getAttribute("phoneErr");
        String passwordErr = (String) session.getAttribute("passwordErr");
        String tosErr = (String) session.getAttribute("tosErr");
        String userexistsErr = (String) session.getAttribute("userexistsErr");
    %>
    <form class="form-register" method="POST" action="/RegisterServlet" novalidate>
        <h1 class="h3 mb-3 font-weight-normal">Please register</h1>
        <!-- Error alerts and input fields here -->
    <% if (nullErr != null) { %>
        <div class="alert alert-danger"><%= nullErr %></div>
    <% } %>
    <% if(userexistsErr != null) { %>
        <div class="alert alert-danger"><%= userexistsErr
         %></div>
    <% } %>

    

    <input type="email" name="email" class="form-control" placeholder="Email address" required autofocus>
    <% if(emailErr != null) { %>
        <div class="alert alert-danger"><%= emailErr %></div>
    <% } %>
    <input type="text" name="firstname" class="form-control" placeholder="First Name" required>

    <input type="text" name="lastname" class="form-control" placeholder="Last Name" required>
    <% if(nametypeErr != null) { %>
        <div class="alert alert-danger"><%= nametypeErr %></div>
    <% } %>
    <input type="password" name="password" class="form-control" placeholder="Password" required>
    <% if(passwordErr != null) { %>
        <div class="alert alert-danger"><%= passwordErr %></div>
    <% } %>
    <input type="text" name="phone" class="form-control" placeholder="Phone Number" required>
    <% if(phoneErr != null) { %>
        <div class="alert alert-danger"><%= phoneErr %></div>
    <% } %>
    <select class="form-control" name="gender" required>
        <option value="">Select Gender</option>
        <option value="male">Male</option>
        <option value="female">Female</option>
        <option value="other">Other</option>
    </select>
    <div class="checkbox mb-3">
        <label><input type="checkbox" name="tos" value="agree" required> Agree to Terms of Service</label>
    </div>
    <% if(tosErr != null) { %>
        <div class="alert alert-danger"><%= tosErr
         %></div>
    <% } %>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
        <a href="index.jsp" class="btn btn-lg btn-secondary btn-block back-home">Back to Home</a>
        <div class="mt-3">Already have an account? <a href="login.jsp">Click here</a></div>
    </form>
</body>
</html>
