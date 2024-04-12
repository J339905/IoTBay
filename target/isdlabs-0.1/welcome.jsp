<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uts.isd.model.User" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login Page</title>
        <!-- Bootstrap CSS for responsive design -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <!-- Web fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
        <style>
        body {
            display: flex;
            justify-content: center; 
            background-color: #f5f5f5;
            height: 100vh;
            padding-top: 100px;
        }

        .mountain-background {
            background-image: url('/css/Mountain-Welcome.webp');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            height: 100vh;
            padding: 0;
        }

        </style>
    </head>
    <% 
        // ArrayList<User> users = (ArrayList<User>) session.getAttribute("users");
    
        // User user = new User();
        // if (session.getAttribute("user") != null) {
        //     user = (User) session.getAttribute("user");
        // }
        String email = (String) request.getAttribute("email");
        String name = (String) request.getAttribute("name");
        String favCol = (String) request.getParameter("favcol");
        String gender = (String) request.getAttribute("gender");

        // if (users == null)  {
    %> 

        <%-- <h1>Welcome</h1> --%>
    
    <%-- <% } else { %> --%>
    <body class="mountain-background" bgcolor="<%= favCol%>" >
        
        <div style="margin-top: 100px; color: <%= favCol%>;">
            <h1>Welcome</h1>
            <div style="display: flex; align-items:center;">
                <svg style="margin-right: 10px;" xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-envelope-arrow-up-fill" viewBox="0 0 16 16">
                <path d="M.05 3.555A2 2 0 0 1 2 2h12a2 2 0 0 1 1.95 1.555L8 8.414zM0 4.697v7.104l5.803-3.558zm.192 8.159 6.57-4.027L8 9.586l1.239-.757.367.225A4.49 4.49 0 0 0 8 12.5c0 .526.09 1.03.256 1.5H2a2 2 0 0 1-1.808-1.144M16 4.697v4.974A4.5 4.5 0 0 0 12.5 8a4.5 4.5 0 0 0-1.965.45l-.338-.207z"/>
                <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.354-5.354 1.25 1.25a.5.5 0 0 1-.708.708L13 12.207V14a.5.5 0 0 1-1 0v-1.717l-.28.305a.5.5 0 0 1-.737-.676l1.149-1.25a.5.5 0 0 1 .722-.016"/>
                </svg>
                <h2>Email: <%= email %> </h2>
            </div>
            
            <div style="display: flex; align-items:center;">
                <svg style="margin-right: 10px;" xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-chat-square-fill" viewBox="0 0 16 16">
                <path d="M2 0a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h2.5a1 1 0 0 1 .8.4l1.9 2.533a1 1 0 0 0 1.6 0l1.9-2.533a1 1 0 0 1 .8-.4H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2z"/>
                </svg>
                <h2>Name: <%= name %> </h2>
            </div>

            <div style="display: flex; align-items:center;">
                <svg style="margin-right: 10px;" xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-chat-square-fill" viewBox="0 0 16 16">
                <path d="M2 0a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h2.5a1 1 0 0 1 .8.4l1.9 2.533a1 1 0 0 0 1.6 0l1.9-2.533a1 1 0 0 1 .8-.4H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2z"/>
                </svg>
                <h2>Gender: <%= gender %> </h2>
            </div>

            <%-- <div style="display: flex; align-items:center;">
                <svg style="margin-right: 10px;" xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-check-square-fill" viewBox="0 0 16 16">
                <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm10.03 4.97a.75.75 0 0 1 .011 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.75.75 0 0 1 1.08-.022z"/>
                </svg>
                <%-- <% if (users.get(users.size() - 1).getTos() == null){ %>
                        <h2>You have not accepted the terms of service!</h2>
                <% }else { %>
                        <h2>You have succesfully created an account!</h2>
                <% } %> --%>
            <%-- </div> --%>

            <a href="index.jsp">Logout</a>
        </div>
    </body>
    <%-- <% } %> --%>
</html>
