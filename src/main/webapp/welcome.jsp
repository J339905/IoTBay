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
            <a href="CreateProduct.jsp" class="btn btn-success">Add New Product</a>
            <a href="UpdateProduct.jsp" class="btn btn-info">Manage Products</a>
            <a href="ProductList.jsp" class="btn btn-info">View Products</a>
        </div>
    </div>
</body>
</html>