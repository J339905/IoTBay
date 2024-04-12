<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.User"%>
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
    <form class="form-register" action="register.jsp" method="post">
        <h1 class="h3 mb-3 font-weight-normal">Please register</h1>
        <input type="email" name="email" class="form-control" placeholder="Email address" required autofocus>
        <input type="text" name="name" class="form-control" placeholder="Name" required>
        <input type="password" name="password" class="form-control" placeholder="Password" required>
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
        <!-- Added text for existing account -->
        <div class="mt-3">
            Already have an account? <a href="login.jsp">Click here</a>
        </div>
        <% 
            String submitted = request.getParameter("submitted");
            boolean tosAgreed = "agree".equals(request.getParameter("tos"));
            if ("true".equals(submitted) && tosAgreed) {
                String email = request.getParameter("email");
                String name = request.getParameter("name");
                String password = request.getParameter("password");
                String gender = request.getParameter("gender");
                String favCol = request.getParameter("favcol");
                
                User newUser = new User(email, name, "", password, gender, favCol);
                session.setAttribute("user", newUser);
                response.sendRedirect("welcome.jsp");
            } else if ("true".equals(submitted)) {
                out.println("<div class='alert alert-danger' role='alert'>You must agree to the Terms of Service to register.</div>");
            }
        %>
    </form>
</body>
</html>