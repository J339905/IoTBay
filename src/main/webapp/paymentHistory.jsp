<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Payment History</title>
    <link rel="stylesheet" href="<c:url value='/css/payment.css'/>">
</head>
<body>
    <h1>Payment History</h1>
    <div class="table-container">
        <table>
            <thead>
                <tr>
                    <th>Payment ID</th>
                    <th>Payment Method</th>
                    <th>Amount</th>
                    <th>Payment Status</th>
                    <th>Payment Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${payments}" var="payment">
                    <tr>
                        <td>${payment.paymentId}</td>
                        <td>${payment.paymentMethod}</td>
                        <td>${payment.amount}</td>
                        <td>${payment.paymentStatus}</td>
                        <td>${payment.paymentDate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <a href="dashboard.jsp" class="btn">Return to Dashboard</a>
</body>
</html>
