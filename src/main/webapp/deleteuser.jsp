<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete Account</title>
</head>
<body>
    <h1>Delete Your Account</h1>
    <p>Are you sure you want to delete your account? This action cannot be undone.</p>

    <form action="DeleteUserServlet" method="post">
        <button type="submit">Delete My Account</button>
    </form>

    <a href="profile.jsp">Cancel and return to Profile</a>
</body>
</html>
