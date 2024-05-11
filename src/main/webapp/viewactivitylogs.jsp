
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Activity Logs</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1>Your Activity Logs</h1>

        <!-- Form for date input -->
        <form action="ViewActivityLogsServlet" method="post" class="mb-4">
            <div class="form-group">
                <label for="date">Enter Date:</label>
                <input type="date" id="date" name="date" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary">Search Logs by Date</button>
        </form>

        <!-- Display error messages if any -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <!-- Display message if no logs are found -->
        <c:if test="${not empty message}">
            <div class="alert alert-info">${message}</div>
        </c:if>

        <!-- Table to display logs if available -->
        <c:if test="${not empty activitylogs}">
            <table class="table">
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>Activity Time</th>
                        <th>Activity Type</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${activitylogs}" var="log">
                        <tr>
                            <td>${log.userID}</td>
                            <td>${log.activityTime}</td>
                            <td>${log.activityType}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <a href="/welcome.jsp" class="btn btn-primary">Go back to welcome page</a>
    </div>
</body>
</html>
