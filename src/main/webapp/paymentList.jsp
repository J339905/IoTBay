<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Payment List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('/css/Mountain-Background.jpeg');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            display: flex;
            flex-direction: column;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
        .content-container {
            background: rgba(255, 255, 255, 0.9);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
            width: 90%;
            max-width: 1200px;
            margin-top: 20px;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        .update-delete-buttons {
            display: flex;
            gap: 10px;
        }
        .update-delete-buttons form {
            margin: 0;
        }
    </style>
</head>
<body>
    <div class="content-container">
        <h1>Your Payments</h1>
        <table>
            <thead>
                <tr>
                    <th>Payment ID</th>
                    <th>Order ID</th>
                    <th>Amount</th>
                    <th>Payment Date</th>
                    <th>Payment Method</th>
                    <th>Credit Card Details</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="payment" items="${paymentList}">
                    <tr>
                        <td>${payment.paymentId}</td>
                        <td>${payment.orderId}</td>
                        <td>${payment.amount}</td>
                        <td>${payment.paymentDate}</td>
                        <td>${payment.paymentMethod}</td>
                        <td>${payment.creditCardDetails}</td>
                        <td class="update-delete-buttons">
                            <form action="payment" method="post">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="paymentId" value="${payment.paymentId}">
                                <button type="submit">Update</button>
                            </form>
                            <form action="payment" method="post">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="paymentId" value="${payment.paymentId}">
                                <button type="submit">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <a href="dashboard.jsp" class="btn btn-primary">Return to Dashboard</a>
</body>
</html>
