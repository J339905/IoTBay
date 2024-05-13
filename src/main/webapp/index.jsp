<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/index.css">
</head>
<body onload="startTime()" class="mountain-background">


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
        <a href="/login.jsp" class="btn btn-primary mt-3">Login to your Account</a>
    </div>
    <script src="script.js"></script>
    <jsp:include page="/ConnServlet" flush="true"/>
</body>
</html>