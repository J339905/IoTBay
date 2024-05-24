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
            max-width: 800px;
        }
        .table {
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ddd;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 100%;
        }
        .table th, .table td {
            padding: 10px;
            text-align: left;
        }
        .btn {
            padding: 10px 20px;
            color: #fff;
            background-color: #007BFF;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: block;
            width: 200px;
            text-align: center;
            margin-top: 20px;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <header>
        <h1>Saved Payment Details</h1>
    </header>
    <main class="container">
        <table class="table">
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
                        <td>${payment.paymentID}</td>
                        <td>${payment.address}</td>
                        <td>${payment.paymentMethod}</td>
                        <td>${payment.cardNumber}</td>
                        <td>${payment.expiryDate}</td>
                        <td>${payment.cvv}</td>
                        <td>${payment.amount}</td>
                        <td>${payment.paymentDate}</td>
                        <td>
                            <form action="viewUpdatePaymentDetails" method="post">
                                <input type="hidden" name="paymentID" value="${payment.paymentID}">
                                <button type="submit" class="btn">Update</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="button-container">
            <a href="viewCart" class="btn">Back to Cart</a>
        </div>
    </main>
</body>
</html>
