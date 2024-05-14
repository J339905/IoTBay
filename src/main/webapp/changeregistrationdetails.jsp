<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="uts.isd.model.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Registration Details</title>
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
            padding: 15px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .form-control {
            margin-bottom: 10px;
            width: 100%;
        }
        label {
            font-weight: bold;
        }
        .alert {
            margin-top: 10px;
            margin-bottom: 20px;
        }
        .btn-custom {
            width: 100%;
            padding: 10px;
        }
        a {
            display: block;
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
 <%
        String nullErr = (String) session.getAttribute("nullErr");
        String phoneErr = (String) session.getAttribute("phoneErr");
        String samedetailsErr = (String) session.getAttribute("samedetailsErr");
         
        String nametypeErr = (String) session.getAttribute("nametypeErr");
        String passwordErr = (String) session.getAttribute("passwordErr");


    %>
<body>
    <div class="form-register">
        <h1 class="h3 mb-3 font-weight-normal">Update Your Registration Details</h1>
        <form method="POST" action="/ChangeRegistrationDetailsServlet" novalidate>
            <input type="hidden" name="email" value="${user.email}" disabled>
            
            <% if (nullErr != null) { %>
                <div class="alert alert-danger"><%= nullErr %></div>
            <% } %>
            <% if (samedetailsErr != null) { %>
                <div class="alert alert-danger"><%= samedetailsErr %></div>
            <% } %>
            
            <input type="text" id="firstname" name="firstname" class="form-control" value="${user.getfirstName()}" required>
            
            <input type="text" id="lastname" name="lastname" class="form-control" value="${user.getlastname()}" required>
            
            <% if (nametypeErr != null) { %>
                <div class="alert alert-danger"><%= nametypeErr %></div>
            <% } %>
            
            <input type="text" id="phone" name="phone" class="form-control" value="${user.getPhone()}" required>
            
            <% if(phoneErr != null) { %>
                <div class="alert alert-danger"><%= phoneErr %></div>
            <% } %>
            
            <input type="password" id="password" name="password" class="form-control" value="${user.getPassword()}" required>
            
            <% if (passwordErr != null) { %>
                <div class="alert alert-danger"><%= passwordErr %></div>
            <% } %>
            
            <select id="gender" name="gender" class="form-control" required>
                <option value="Male" ${user.gender == 'Male' ? 'selected' : ''}>Male</option>
                <option value="Female" ${user.gender == 'Female' ? 'selected' : ''}>Female</option>
                <option value="Other" ${user.gender == 'Other' ? 'selected' : ''}>Other</option>
            </select>
            
            <button type="submit" class="btn btn-primary btn-custom">Update Details</button>
        </form>
        <a href="welcome.jsp">Back to Profile</a>
    </div>
</body>
</html>
