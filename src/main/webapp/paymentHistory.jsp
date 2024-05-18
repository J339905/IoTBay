<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Payment History</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        table {
            width: 90%;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .btn {
            padding: 10px 20px;
            color: #fff;
            background-color: #007BFF;
            border: none;
            border-radius: 4px;
            text-decoration: none;
            margin-top: 20px;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Payment History</h1>
    <table>
        <thead>
            <tr>
                <th>Payment ID</th>
                <th>Order ID</th>
                <th>Amount</th>
                <th>Payment Method</th>
                <th>Payment Date</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${payments}" var="payment">
                <tr>
                    <td>${payment.paymentID}</td>
                    <td>${payment.orderID}</td>
                    <td>${payment.amount}</td>
                    <td>${payment.paymentMethod}</td>
                    <td>${payment.paymentDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="dashboard.jsp" class="btn">Return to Dashboard</a>
</body>
</html>
