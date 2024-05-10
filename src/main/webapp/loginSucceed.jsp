<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Account</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            padding: 20px;
            max-width: 400px;
            width: 100%;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        form {
            text-align: center;
        }
        button {
            padding: 10px 20px;
            border: none;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
 <%
    String name = (String) request.getAttribute("name");
    String email = (String) request.getAttribute("email");
    String gender = (String) request.getAttribute("gender");
%>
<body>
<div class="container">
    <h1>Welcome</h1>
    <h2>Name: <%= name %></h2>
    <h2>Email: <%= email %></h2>
    <h2>Gender: <%= gender %></h2>
    <form action="LogoutController" method="post">
        <input type="hidden" name="name" value="<%= name %>">
        <button type="submit">Logout</button>
    </form>
</div>
</body>
</html>
