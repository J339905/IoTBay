<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome</title>
    <style>
        body {
            background-color: <%= request.getParameter("favcol") %>; /* Applying user's favourite colour as background */
        }
        .button {
            color: white;
            background-color: <%= request.getParameter("favcol") %>; /* Buttons in favourite colour */
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            margin: 5px;
        }
    </style>
</head>
<body>
    <%
        String favcol = request.getParameter("favcol");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String tos = request.getParameter("tos");
    %>
    <h1>Welcome</h1>
    <% if(tos != null && tos.equals("on")) { %>
        <h2>Email: <%= email %></h2>
        <h2>Name: <%= name %></h2>
        <h2>You have successfully created an account!</h2>
    <% } else { %>
        <h2>You have not accepted the terms of service!</h2>
    <% } %>

    <!-- Buttons -->
    <div>
        <a href="index.jsp" class="button">Home</a>
        <% if(tos != null && tos.equals("on")) { %>
            <a href="logout.jsp" class="button">Logout</a>
            <a href="account.jsp" class="button">Account</a>
        <% }
        else { %>
            <!-- Register button appears only if the TOS is not accepted -->
            <a href="register.jsp" class="button">Register</a>
        <% } %>

    </div>

</body>
</html>


have a home button, logout button, account button
ge tstuff from jack git
