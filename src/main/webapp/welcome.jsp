<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Welcome</title>
        <!-- Bootstrap CSS for responsive design -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <!-- Web fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

.btn-custom, .btn-home, .btn-account, .btn-logout {
    color: #000; /* Dark text color for contrast */
    background-color: <%= request.getParameter("favcol") %>; /* This green color will be used as the background */
    border: none; /* No border */
    padding: 30px 70px;
    text-decoration: none;
    border-radius: 20px; /* Rounded corners */
    font-weight: bold; /* Make the text bold */
    margin: 5px;
    transition: background-color 0.3s ease; /* Smooth transition for hover effect */
}

.button-container {
    position: absolute;
    bottom: 300px; /* Increase or decrease this value to move the buttons up or down */
    left: 50%;
    transform: translateX(-50%);
    text-align: center; /* Center-align the buttons */
}


.btn-custom:hover, .btn-home:hover, .btn-account:hover, .btn-logout:hover {
    background-color: #ffff; /* Darker green color on hover */
}

.material-symbols-outlined {
    font-size: 75px; /* Adjust this value as needed to make the icon bigger */
    align-self: center; /* Center the icon vertically within the button */
}

        </style>
    </head>
    <body class="mountain-background" bgcolor="<%= request.getParameter("favcol") %>" >
        <%
            String favcol = request.getParameter("favcol");
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String tos = request.getParameter("tos");
        %>
        <div style="margin-top: 100px; color: <%= favcol %>;">
            <h1>Welcome</h1>
            <!-- Email Section -->
            <div style="display: flex; align-items:center;">
                <!-- SVG and Email Display -->
                  <svg style="margin-right: 10px;" xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-envelope-arrow-up-fill" viewBox="0 0 16 16">
                <path d="M.05 3.555A2 2 0 0 1 2 2h12a2 2 0 0 1 1.95 1.555L8 8.414zM0 4.697v7.104l5.803-3.558zm.192 8.159 6.57-4.027L8 9.586l1.239-.757.367.225A4.49 4.49 0 0 0 8 12.5c0 .526.09 1.03.256 1.5H2a2 2 0 0 1-1.808-1.144M16 4.697v4.974A4.5 4.5 0 0 0 12.5 8a4.5 4.5 0 0 0-1.965.45l-.338-.207z"/>
                <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.354-5.354 1.25 1.25a.5.5 0 0 1-.708.708L13 12.207V14a.5.5 0 0 1-1 0v-1.717l-.28.305a.5.5 0 0 1-.737-.676l1.149-1.25a.5.5 0 0 1 .722-.016"/>
                </svg>
                <h2>Email: <%= email %></h2>
            </div>
            <!-- Name Section -->
            <div style="display: flex; align-items:center;">
                <!-- SVG and Name Display -->
                                <svg style="margin-right: 10px;" xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-chat-square-fill" viewBox="0 0 16 16">
                <path d="M2 0a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h2.5a1 1 0 0 1 .8.4l1.9 2.533a1 1 0 0 0 1.6 0l1.9-2.533a1 1 0 0 1 .8-.4H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2z"/>
                </svg>
                <h2>Name: <%= name %></h2>
            </div>
            <!-- TOS Section -->
            <div style="display: flex; align-items:center;">
                <!-- SVG for TOS Status -->
                               <svg style="margin-right: 10px;" xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-check-square-fill" viewBox="0 0 16 16">
                <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm10.03 4.97a.75.75 0 0 1 .011 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.75.75 0 0 1 1.08-.022z"/>
                </svg>
                <% if (tos == null){ %>
                        <h2>You have not accepted the terms of service!</h2>
                        <!-- Register Button for when TOS is not accepted -->
                        </div>
                        <div>
                <% } else { %>
                        <h2>You have successfully created an account!</h2>
                        </div>
                        <div>
                <% } %>
            </div>
        <div class="button-container">
            <% if (tos == null){ %>
                <a href="index.jsp" class="btn btn-home"><span class="material-symbols-outlined" style="display: flex; flex-direction: column;">
home
</span> Home </a>
                <a href="register.jsp" class="btn btn-custom">Register</a>
            <% } else { %>
                <a href="index.jsp" class="btn btn-home">Home <span class="material-symbols-outlined" style="display: flex; flex-direction: column;">
home
</span></a>
                <a href="account.jsp" class="btn btn-account">Account</a>
                <a href="logout.jsp" class="btn btn-logout">Logout</a>
            <% } %>
        </div>

        </div>
    </body>
</html>

<%-- // test --%>

<%--


have a home button, logout button, account button
ge tstuff from jack git
--%>