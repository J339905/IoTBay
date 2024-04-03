<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background-image: url('css/feit.jpg');
            background-size: cover; 
            background-position: center;
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            margin: 0;
            color: #ffffff; 
        }

        .registration-form {
            background-color: rgba(255, 255, 255, 0.8); 
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
            max-width: 400px;
            width: 100%;
            backdrop-filter: blur(10px); 
        }

        .registration-form h1 {
            color: #333;
            margin-bottom: 24px;
            font-weight: 600; 
        }

        .form-control {
            margin-bottom: 20px;
            position: relative; 
        }

        .form-control label {
            display: block;
            margin-bottom: 8px;
            font-weight: 500; 
            color: #333;
        }

        .form-control input,
        .form-control select, 
        .form-control button,
        .form-control a {
            width: 100%;
            padding: 12px;
            border: 2px solid #ddd; 
            border-radius: 8px;
            box-sizing: border-box;
            transition: border-color
        }

        .form-control input:focus,
        .form-control select:focus { 
            border-color: #0056b3;
            outline: none;
        }

        .form-control button {
            background-color: #0056b3;
            color: #ffffff;
            border: none;
            cursor: pointer;
            margin-top: 10px;
            font-weight: 500;
            transition: background-color 
        }

        .form-control button:hover {
            background-color: #003d82;
        }

        .form-control a {
            display: inline-block;
            text-align: center;
            margin-top: 10px;
            color: #ffffff; 
            text-decoration: none;
            transition: color 0.3s; 
        }

        .form-control a:hover {
            color: #aad8ff; 
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
                <select id="gender" name="gender">
                    <option value="">Select Gender</option>
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
    </div>
</body>
</html>
