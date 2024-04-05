<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
    <!-- Bootstrap CSS for responsive design -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Web fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        .mountain-background {
            background-image: url('https://i.ibb.co/KyH7ckG/A-serene-and-beautiful-mountain-landscape-at-sunri.png');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            height: 100vh;
            margin: 0;
            padding: 0;
        }
        /* Additional custom styles can go here */
    </style>
</head>
<body onload="startTime()" class="mountain-background">
    <!-- Navigation bar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">My Website</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/register.jsp">Register</a>
            </li>
            <!-- Link to login.jsp added below -->
            <li class="nav-item">
                <a class="nav-link" href="/login.jsp">Login</a>
            </li>
        </ul>
    </div>
</nav>

    <div class="container mt-5">
        <h1 id="greeting"></h1>
        <script>
            function startTime() {
                const today = new Date();
                let h = today.getHours();
                let greeting;
                if (h < 12) {
                    greeting = 'Good Morning!';
                } else if (h < 18) {
                    greeting = 'Good Afternoon!';
                } else {
                    greeting = 'Good Evening!';
                }
                document.getElementById('greeting').innerHTML = greeting + ' Welcome to Our Home Page';
            }
        </script>
        <a href="/register.jsp" class="btn btn-primary mt-3">Register an Account</a>
    </div>

    <!-- Optional JavaScript; choose one of the two! -->
    <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>

