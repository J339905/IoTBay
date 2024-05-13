<<<<<<< HEAD
<%-- <%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.User"%>
<%@page import="uts.isd.model.UserIDGenerator"%>
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
            background-color: #f5f5f5;
        }
        .form-register {
            max-width: 330px;
            padding: 15px;
            margin: auto;
        }
    </style>
</head>
<body>
<%
        String nameErr = (String) session.getAttribute("nameErr");
    %>
    <%-- <form class="form-register" action="register.jsp" method="post"> --%>
            <%-- <form method="POST" action="/RegisterServlet">
        <h1 class="h3 mb-3 font-weight-normal">Please register</h1>
        <input type="email" name="email" class="form-control" placeholder="Email address" required autofocus>
        <input type="text" name="FirstName" class="form-control" placeholder="First Name" required>
         <input type="text" name="SecondName" class="form-control" placeholder="Second Name" required>
         <% if(nameErr != null) { %>
                <h1><%=nameErr%></h1>
            <% } %>
        <input type="password" name="password" class="form-control" placeholder="Password" required>
        <input type="text" name="phone" class="form-control" placeholder="Phone Number" required>
        <select class="form-control" name="gender" required>
            <option value="">Select Gender</option>
            <option value="male">Male</option>
            <option value="female">Female</option>
            <option value="other">Other</option>
        </select>
        <input type="color" name="favcol" class="form-control" placeholder="Favourite Colour" required>
        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" name="tos" value="agree" required> Agree to Terms of Service
            </label>
        </div>
        <input type="hidden" name="submitted" value="true">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
        <div class="mt-3">
            Already have an account? <a href="login.jsp">Click here</a>
        </div>
        <% 
            String submitted = request.getParameter("submitted");
            boolean tosAgreed = "agree".equals(request.getParameter("tos"));
            if ("true".equals(submitted) && tosAgreed) {
                int userId = uts.isd.model.UserIDGenerator.generateUserId();          

                String email = request.getParameter("email");
                String FirstName = request.getParameter("FirstName");
                String lastname = request.getParameter("SecondName");
                String password = request.getParameter("password");
                String gender = request.getParameter("gender");
                String favCol = request.getParameter("favcol");
                int phone = Integer.parseInt(request.getParameter("phone"));
                String role = "Customer";
                User newUser = new User(userId, FirstName,lastname,email, phone, password, gender, role);
                session.setAttribute("user", newUser);
                response.sendRedirect("welcome.jsp");
            } else if ("true".equals(submitted)) {
                out.println("<div class='alert alert-danger' role='alert'>You must agree to the Terms of Service to register.</div>");
            }
        %>

    </form>
</body>
</html> --%> 
<%-- 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            body {
                background: url('feit.jpg') no-repeat center center; /*center center fixed; */
                /*
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;  
                */
                width: 200px;
                height: 200px;
                background-size: contain;
                border-radius: 50%;
                letter-spacing: 4px;
                font-family: Arial, Helvetica, sans-serif;
            }

            #bar {
                position: fixed;
                background-color: #134c5b;
                padding: 10px 5px 6px 15px;
                color: white;
                font-size: 30px;
                font-weight: bold;
                top: 0;
                left: 0;
                right: 0;
                opacity: 0.7;
            }

            #links {
                font-size: 18px;
                padding: 5px;
                word-spacing: 2px;
                float: right;
            }

            .footer {
                position: fixed;
                background-color: #134c5b;
                left: 0;
                right: 0;
                bottom: 0;
                font-size: 36px;
                color: #afeb00;
                padding: 3px;
                text-align: center;
                opacity: 0.7;
            }

            .message {
                color: #4caf50;
                font-weight: bold;
                font-size: 20px;
            }

            input {
                margin-bottom: 16px;
            }

        </style>
        <title>Register</title>
    </head>
    <body >
    <%
        String nameErr = (String) session.getAttribute("nameErr");
    %>
        <form method="POST" action="/RegisterServlet">
            <label for="email">Email:</label>
            <input type="email" name="email" id="email" placeholder="email" required/>
            <label for="name">Name:</label>
            <input name="name" id="name" placeholder="name" />
            <% if(nameErr != null) { %>
                <h1><%=nameErr%></h1>
            <% } %>
            <label for="password">Password:</label>
            <input type="password" name="password" id="password" placeholder="password" />
            <label for="gender">Gender:</label>
            <input name="gender" id="gender" placeholder="gender" />
            <label for="favcol">Favourite Colour:</label>
            <input type="color" name="favcol" id="favcol" placeholder="favcol" />
            <br/>
            <label for="tos">TOS:</label>
            <input type="checkbox" name="tos" id="tos" placeholder="tos" />
            <br/>
            <input type="hidden" name="submitted" id="submitted" value="true" />
            <button type="submit">Register Account</button>
        </form>

        <button><a href="/">Cancel Registration</a></button>
    </body>
</html> --%>
=======
>>>>>>> NasBranch-LogAccess

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body { padding-top: 40px; padding-bottom: 40px; background-color: #f5f5f5; }
        .form-register { max-width: 330px; padding: 15px; margin: auto; }
    </style>
</head>
<body>
<<<<<<< HEAD
 <%
        String emailErr = (String) session.getAttribute("nameErr");
        
    %>
    <form class="form-register" method="POST" action="/RegisterServlet">
        <h1 class="h3 mb-3 font-weight-normal">Please register</h1>
        <input type="email" name="email" class="form-control" placeholder="Email address" required autofocus>
        <%-- <% if(emailErr != null) { %>
                <h1><%=emailErr%></h1>
            <% } %> --%>
        <input type="text" name="firstname" class="form-control" placeholder="First Name" required>
        <input type="text" name="secondname" class="form-control" placeholder="Second Name" required>
        
        <input type="password" name="password" class="form-control" placeholder="Password" required>
        <input type="text" name="phone" class="form-control" placeholder="Phone Number" required>
        <select class="form-control" name="gender" required>
            <option value="">Select Gender</option>
            <option value="male">Male</option>
            <option value="female">Female</option>
            <option value="other">Other</option>
        </select>
        <input type="color" name="favcol" class="form-control" placeholder="Favourite Colour" required>
        <div class="checkbox mb-3">
            <label><input type="checkbox" name="tos" value="agree" required> Agree to Terms of Service</label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
        <div class="mt-3">Already have an account? <a href="login.jsp">Click here</a></div>
    </form>
</body>
</html>
=======
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
    <input type="color" name="favcol" class="form-control" placeholder="Favourite Colour" required>
    <div class="checkbox mb-3">
        <label><input type="checkbox" name="tos" value="agree" required> Agree to Terms of Service</label>
    </div>
    <% if(tosErr != null) { %>
        <div class="alert alert-danger"><%= tosErr
         %></div>
    <% } %>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
    <div class="mt-3">Already have an account? <a href="login.jsp">Click here</a></div>
</form>
>>>>>>> NasBranch-LogAccess
