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
    <style>
        body {
            background-color: #f5f5f5;
            background-image: url('/css/Mountain-Background.jpeg'); 
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
        }
        .form-index {
            width: 100%;
            max-width: 330px;
            padding: 10px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            margin: 0 auto; /* Center horizontally */
            margin-top: 40px; /* Add space from top */
            padding: 20px;
        }
        .container {
            text-align: center; /* Center align all content inside container */
        }
    </style>
</head>
<body onload="startTime()">
    <div class="container">
        <form class="form-index">
            <h1 id="greeting" class="greeting"></h1>
            <p> we hope you enjoy your time on our website!</p>
            <a href="/register.jsp" class="btn btn-accent mt-3">Register an Account</a>
            <a href="/login.jsp" class="btn btn-accent mt-3">Login to your Account</a>
            <a href="/anonymoususerwelcomepage.jsp" class="btn btn-accent mt-3">Continue Anyway</a>
        </form>
    </div>
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
            document.getElementById('greeting').innerHTML = greeting + ' Welcome to the IOTBAY Home Page';
        }
    </script>
    <script src="script.js"></script>
    <jsp:include page="/ConnServlet" flush="true"/>
</body>
</html>
