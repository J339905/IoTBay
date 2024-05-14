<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Activity Logs</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-image: url('/css/Mountain-Background.jpeg');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            background-color: #f5f5f5;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
        .container {
            max-width: 800px;
            padding: 30px;
            background: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
        }
        .form-log {
            margin-bottom: 20px;
        }
        .alert {
            margin-bottom: 20px;
        }
        .table {
            width: 100%;
            margin-bottom: 20px;
            border-collapse: collapse;
        }
        .table th, .table td {
            padding: 8px;
            border-bottom: 1px solid #dee2e6;
        }
        .btn-go-back {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1>Your Activity Logs</h1>

        <form action="ViewActivityLogsServlet" method="post" class="form-log">
            <div class="form-group">
                <label for="date">Enter Date:</label>
                <input type="date" id="date" name="date" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary">Search Logs by Date</button>
        </form>

=        <c:if test="${not empty nologsErr}">
            <div class="alert alert-danger">${nologsErr}</div>
        </c:if>

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

        <a href="/welcome.jsp" class="btn btn-primary btn-go-back">Go back to welcome page</a>
    </div>
</body>
</html>
