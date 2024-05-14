
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
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
            background: white;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            margin-top: 40px;
        }
        .lead {
            font-size: 16px;
            color: #666;
        }
        .btn-custom {
            margin-top: 20px;
            width: 200px;
        }
        .alert-warning {
            color: #856404;
            background-color: #fff3cd;
            border-color: #ffeeba;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1>Welcome, ${user.getfirstName()}!</h1>
        <p class="lead">Here are your details:</p>
        <p class="lead">Logged in as: <strong>${user.getRole()}</strong></p>
        <p class="lead">User ID: <strong>${user.getUserID()}</strong></p>
        <p class="lead">Email: <strong>${user.getEmail()}</strong></p>
        <p class="lead">First Name: <strong>${user.getfirstName()}</strong></p>
        <p class="lead">Last Name: <strong>${user.getlastname()}</strong></p>
        <p class="lead">Gender: <strong>${user.getGender()}</strong></p>
        <p class="lead">Phone Number: <strong>${user.getPhone()}</strong></p>
        <p class="lead">Password: <strong>${user.getPassword()}</strong></p>
        <div class="alert alert-warning">
            <strong>Important:</strong> For security reasons, do not share your password? (otherwise gg)
        </div>
        <p class="lead">Want to update your details? Click below.</p>
        <div class="mt-3">
            <a href="/welcome.jsp" class="btn btn-secondary btn-custom">Go Back to Welcome</a>
            <a href="/changeregistrationdetails.jsp" class="btn btn-primary btn-custom">Change Registration</a>
        </div>
    </div>
</body>
</html>
