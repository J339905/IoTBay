<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete Account</title>
    <style>
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-image: url('/css/Mountain-Background.jpeg');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            background-color: #f5f5f5;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
        .container {
            width: 100%;
            max-width: 400px;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            text-align: center;
        }
        h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }
        p {
            font-size: 16px;
            margin-bottom: 30px;
        }
        .form-delete button {
            display: block;
            width: 100%;
            padding: 10px 0;
            font-size: 16px;
            background-color: #dc3545;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .form-delete button:hover {
            background-color: #c82333;
        }
        .back-home {
            display: block;
            width: 100%;
            padding: 10px 0;
            font-size: 16px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            margin-top: 20px;
        }
        .back-home:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Delete Your Account</h1>
        <p>Are you sure you want to delete your account? This action cannot be undone.</p>

        <form class="form-delete" method="POST" action="/DeleteUserServlet">
            <button type="submit">Delete My Account</button>
        </form>

        <a href="welcome.jsp" class="back-home">Cancel and return to Profile</a>
    </div>
</body>
</html>
