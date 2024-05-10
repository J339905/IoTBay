<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1> ${user.getfirstName()}!, the following are your details:</h1>
        <p>You are now logged in as ${user.getRole()}.</p>
        <p>Your email: ${user.getEmail()}.</p>
        <p>Your first name: ${user.getfirstName()}. </p>
        <p>Your last name: ${user.getlastname()}. </p>
        <p>Your gender: ${user.getGender()}.</p>
        <p>Your phone number: ${user.getPhone()}.</p>
        <p>Your gender: ${user.getGender()}.</p>
         <p>Your id: ${user.getUserID()}.</p>
         <p>Your password: ${user.getPassword()}. (Don't tell anyone)</p>
         <p>Do you want to change any of your details? click 'Change Registration'</p>
        <div class="mt-3">
            <a href="/welcome.jsp" class="btn btn-primary">Go back to welcome page</a>
            <a href="/changeregistrationdetails.jsp" class="btn btn-primary">Change Registration</a>

            <%-- <a href="/logout.jsp" class="btn btn-danger">Logout</a> --%>
        </div>
    </div>
</body>
</html>
