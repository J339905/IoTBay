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
        .form-register { max-width: 210px; padding: 15px; margin: auto; }
    </style>
</head>
<body>
 <%
        String emailErr = (String) session.getAttribute("emailErr");
        
    %>
    <form class="form-register" method="POST" action="/RegisterServlet">
        <h1 class="h3 mb-3 font-weight-normal">Please register</h1>
        <input type="email" name="email" class="form-control" placeholder="Email address" required autofocus>
        <%-- <% if(emailErr != null) { %>
                <h1><%=emailErr%></h1>
            <% } %> --%>
        <input type="text" name="firstname" class="form-control" placeholder="First Name" required>
        <input type="text" name="lastname" class="form-control" placeholder="Last Name" required>
        
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
