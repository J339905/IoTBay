<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Saved Payment Details</title>
    <link rel="stylesheet" href="/css/productlist.css"> <!-- Link to external CSS for styling -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            width: 90%;
            max-width: 1200px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #007BFF;
            color: white;
        }
        .link-btn {
            padding: 10px 20px;
            color: #fff;
            background-color: #007BFF;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            text-align: center;
        }
        .link-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Saved Payment Details</h1>
        <table>
            <thead>
                <tr>
                    <th>Payment ID</th>
                    <th>Address</th>
                    <th>Payment Method</th>
                    <th>Card Number</th>
                    <th>Expiry Date</th>
                    <th>CVV</th>
                    <th>Amount</th>
                    <th>Payment Date</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="payment" items="${payments}">
                    <tr>
                        <td>${payment.paymentId}</td>
                        <td>${payment.address}</td>
                        <td>${payment.paymentMethod}</td>
                        <td>${payment.cardNumber}</td>
                        <td>${payment.expiryDate}</td>
                        <td>${payment.cvv}</td>
                        <td>${payment.amount}</td>
                        <td>${payment.paymentDate}</td>
                        <td><a href="updatePayment.jsp?paymentId=${payment.paymentId}" class="link-btn">Update</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="viewCart" class="link-btn">Back to Cart</a>
    </div>
</body>
</html>
