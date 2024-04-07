<%-- <%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "uts.isd.*"%>
 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <!-- Bootstrap CSS for responsive design -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Web fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h2>Login</h2>
        <form action="loginAction.jsp" method="post"> <!-- You'll need to create or specify the action page -->
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
    </div>
    <!-- Optional JavaScript; choose one of the two! -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html> --%>
<%-- <%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "uts.isd.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <!-- Bootstrap CSS for responsive design -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Web fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const emailInput = document.getElementById('email');
            const passwordInput = document.getElementById('password');
            const loginButton = document.getElementById('loginButton');

            function updateButtonState() {
                // Check if both inputs are not empty
                if(emailInput.value && passwordInput.value) {
                    loginButton.disabled = false; // Enable button if both fields are filled
                    loginButton.classList.remove('btn-secondary');
                    loginButton.classList.add('btn-primary');
                } else {
                    loginButton.disabled = true; // Disable button if either field is empty
                    loginButton.classList.remove('btn-primary');
                    loginButton.classList.add('btn-secondary');
                }
            }

            // Listen for input events on both fields to update the button's state
            emailInput.addEventListener('input', updateButtonState);
            passwordInput.addEventListener('input', updateButtonState);

            // Initialize the button state on page load
            updateButtonState();
        });
    </script>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h2>Login</h2>
        <form action="loginAction.jsp" method="post"> <!-- You'll need to create or specify the action page -->
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-secondary" id="loginButton" disabled>Login</button>
        </form>
    </div>
    <!-- Optional JavaScript; choose one of the two! -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html> --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <!-- Bootstrap CSS for responsive design -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Web fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f5f5f5;
        }
        .login-container {
            width: 100%;
            max-width: 400px;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        .form-group {
            margin-bottom: 15px;
        }
        .btn-secondary.disabled, .btn-secondary:disabled {
            color: #fff;
            background-color: #6c757d;
            border-color: #6c757d;
        }
    </style>
    <script>
        //document.addEventListener('DOMContentLoaded', function () {
        //    const emailInput = document.getElementById('email');
        //    const passwordInput = document.getElementById('password');
        //    const loginButton = document.getElementById('loginButton');

        //    function updateButtonState() {
        //        if(emailInput.value && passwordInput.value) {
        //            loginButton.disabled = false;
        //            loginButton.classList.remove('btn-secondary');
        //            loginButton.classList.add('btn-primary');
        //        } else {
        //            loginButton.disabled = true;
        //            loginButton.classList.remove('btn-primary');
        //            loginButton.classList.add('btn-secondary');
        //        }
        //    }

        //    emailInput.addEventListener('input', updateButtonState);
        //    passwordInput.addEventListener('input', updateButtonState);

        //    updateButtonState();
        //});

    
    </script>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="login-container">
            <h2 class="text-center">Login</h2>
            <form>
                <%

                User user = session.getAttribute("user");

                %>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="text" class="form-control" id="email" name="email" value="<%= user.getEmail() %>" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-secondary" id="loginButton" disabled>Login</button>
            </form>
        </div>
    </div>
    <!-- Optional JavaScript -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
