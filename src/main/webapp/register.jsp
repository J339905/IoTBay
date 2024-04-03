<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            margin: 0;
        }

        .registration-form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }

        .registration-form h1 {
            color: #333;
            margin-bottom: 20px;
        }

        .form-control {
            margin-bottom: 20px;
        }

        .form-control label {
            display: block;
            margin-bottom: 5px;
        }

        .form-control input,
        .form-control button,
        .form-control a {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .form-control button {
            background-color: #0056b3;
            color: #ffffff;
            border: none;
            cursor: pointer;
            margin-top: 10px;
        }

        .form-control a {
            display: inline-block;
            text-align: center;
            margin-top: 10px;
            color: #0056b3;
            text-decoration: none;
        }

        .form-control input[type="checkbox"] {
            width: auto;
            margin-top: 0;
        }

        .form-control label[for="tos"] {
            display: inline;
            margin-left: 10px;
        }
    </style>
</head>
<body>
    <div class="registration-form">
        <%
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String tos = request.getParameter("tos");
            String submitted = request.getParameter("submitted");
        %>
        <% if (submitted != null) { %>
            <h1>Welcome</h1>
            <p>Email: <%= email %></p>
            <p>Name: <%= name %></p>
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
                    <input name="gender" id="gender" placeholder="Your gender">
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
    </div>
</body>
</html>


