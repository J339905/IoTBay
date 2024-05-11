<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
            <a href="/viewactivitylogs.jsp" class="btn btn-primary">View Your Activit</a>
                </div>
            </c:if>
        </div>
    </div>
</body>
</html>
