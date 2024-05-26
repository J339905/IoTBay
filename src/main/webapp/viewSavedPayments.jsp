<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="uts.isd.model.Payment"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Saved Payment Details</title>
    <link rel="stylesheet" href="/css/productlist.css">
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
            max-width: 1000px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ddd;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        .btn, .link-btn {
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
        .btn:hover, .link-btn:hover {
            background-color: #0056b3;
        }
        .button-container {
            display: flex;
            justify-content: center;
            margin: 20px 0;
            width: 100%;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Saved Payment Details</h2>
        <table>
            <thead>
                <tr>
                    <th>Payment ID</th>
                    <th>Card Holder Name</th>
                    <th>Credit Card Number</th>
                    <th>CVV</th>
                    <th>Expiry Date</th>
                    <th>Amount</th>
                    <th>Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${paymentList}" var="payment">
                    <tr>
                        <td>${payment.paymentId}</td>
                        <td>${payment.cardHolderName}</td>
                        <td>${payment.creditCardNumber}</td>
                        <td>${payment.cvv}</td>
                        <td>${payment.expiryDate}</td>
                        <td>${payment.amount}</td>
                        <td>${payment.date}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="button-container">
            <a href="viewCart" class="link-btn">Back to Cart</a>
        </div>
    </div>
</body>
</html>
