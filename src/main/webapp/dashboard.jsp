<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
            background-image: url('/css/Mountain-Background.jpeg'); 
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
        }
        .container {
            max-width: 500px;
            padding: 15px;
            margin: auto;
            background: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
        }
        .alert {
            margin-top: 20px;
        }
        .btn {
            margin: 10px 0;
            width: 100%;
        }
    </style>
</head>
<body>
    <div class="container mt-5 text-center">
        <h1>Welcome to your dashboard, ${user.getfirstName()}!</h1>
        <div class="mt-4">
            <a href="/productlist" class="btn btn-primary">View Products</a>
            <a href="/listOrders" class="btn btn-info">View Your Orders</a>
            <a href="/welcome.jsp" class="btn btn-danger">Return to home</a>
        </div>
    </div>
</body>
</html>
