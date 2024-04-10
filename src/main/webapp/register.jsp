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
    <h2>REGISTRATION FORM</h2>
    <form action="/welcome.jsp" method="post">
        <%-- Server-side script --%>
        <%
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            String favCol = request.getParameter("favcol");
            String gender = request.getParameter("gender");
            String tos = request.getParameter("tos");
            String submitted = request.getParameter("submitted");

            if (submitted != null){
                User user = new User(email, name, phone, password, gender, favCol);
                session.setAttribute("user", user);
            }
        %>

        <% if (submitted != null) { %>
            <h1>Welcome</h1>
            <h2>Email: <%= email%></h2>
            <h2>Name: <%= name%></h2>

            
        <% } else { %>
            <form>
                <div class="form-control">
                    <label for="email">Email:</label>
                    <input type="email" name="email" id="email" placeholder="Enter your email" required>
                </div>
                <div class="form-control">
                    <label for="name">Name:</label>
                    <input name="name" id="name" placeholder="Enter your name">
                </div>
                <div class="form-control">
                    <label for="password">Password:</label>
                    <input type="password" name="password" id="password" placeholder="Create a password">
                </div>
                <div class="form-control">
                <label for="gender">Gender:</label>
                <select id="gender" name="gender">
                    <option value="">Select gender</option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option value="other">Other</option>
                </select>
                </div>
                <div class="form-control">
                    <label for="favcol">Favourite Colour:</label>
                    <input type="color" name="favcol" id="favcol">
                </div>
                <div class="form-control">
                    <input type="checkbox" name="tos" id="tos">
                    <label for="tos">Agree to Terms of Service</label>
                </div>
                <input type="hidden" name="submitted" value="true">
                <button type="submit">Register Account</button>
                <a href="/">Cancel Registration</a>
            </form>
        <% } %>
    </form>
    </div>
</body>
</html>