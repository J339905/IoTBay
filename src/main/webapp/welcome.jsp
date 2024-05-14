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
        <h1>Welcome, ${user.getfirstName()}!</h1>
        <div class="mt-3">
            <a href="/dashboard.jsp" class="btn btn-primary">Go to Dashboard</a>
            <a href="/LogoutServlet" class="btn btn-danger">Logout</a>
            <a href="/viewregistrationdetails.jsp" class="btn btn-primary">View Registration Details</a>
            <a href="/viewactivitylogs.jsp" class="btn btn-primary">View Your Activity</a>
            <a href="/productlist.jsp" class="btn btn-primary">Product list</a>
            <a href="/order.jsp" class="btn btn-primary">Order</a>
        </div>
    </div>
</body>
</html>