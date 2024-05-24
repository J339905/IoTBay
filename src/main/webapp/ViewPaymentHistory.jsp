<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="uts.isd.model.Payment"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Payment History</title>
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
        .summary-container {
            width: 100%;
            max-width: 800px;
            padding: 20px;
            box-sizing: border-box;
        }
        .summary {
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ddd;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 100%;
        }
        .summary th, .summary td {
            padding: 10px;
            text-align: left;
        }
        .input-field {
            width: 100%;
            padding: 8px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
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
        .link-btn {
            padding: 10px 20px;
            background-color: #007BFF;
            color: #fff;
            border: none;
            border-radius: 4px;
            text-decoration: none;
            display: inline-block;
            margin: 5px;
            text-align: center;
            width: 100%;
        }
        .link-btn:hover {
            background-color: #0056b3;
        }
        .button-container {
            display: flex;
            justify-content: space-between;
            margin: 20px 0;
            width: 100%;
        }
    </style>
</head>
<body>
    <header>
        <h1>View Payment History</h1>
    </header>

    <main class="summary-container">
        <div class="summary">
            <h2>Payment History</h2>
            <form action="searchPaymentHistory" method="post">
                <div>
                    <label for="paymentId">Payment ID:</label>
                    <input type="text" name="paymentId" class="input-field">
                </div>
                <div>
                    <label for="paymentDate">Payment Date:</label>
                    <input type="date" name="paymentDate" class="input-field">
                </div>
                <div class="button-container">
                    <input type="submit" class="btn" value="Search">
                </div>
            </form>
            <c:forEach var="payment" items="${payments}">
                <div>
                    <p>Payment ID: ${payment.paymentID}</p>
                    <p>Order ID: ${payment.orderID}</p>
                    <p>Amount: ${payment.amount}</p>
                    <p>Payment Date: ${payment.paymentDate}</p>
                    <p>Payment Method: ${payment.paymentMethod}</p>
                </div>
                <hr>
            </c:forEach>
        </div>
        <div class="button-container">
            <a href="viewCart" class="link-btn">Back to Cart</a>
        </div>
    </main>
</body>
</html>
