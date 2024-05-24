<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.Payment"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Payment Details</title>
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
        .form-container {
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ddd;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 100%;
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
    </style>
</head>
<body>
    <header>
        <h1>Update Payment Details</h1>
    </header>
    <main class="container">
        <div class="form-container">
            <form action="saveUpdatedPaymentDetails" method="post">
                <input type="hidden" name="paymentID" value="${payment.paymentID}">
                <div>
                    <label>Address:</label>
                    <input type="text" name="address" class="input-field" value="${payment.address}" required>
                </div>
                <div>
                    <label>Payment Method:</label>
                    <select name="paymentMethod" class="input-field" required>
                        <option value="creditCard" ${payment.paymentMethod == 'creditCard' ? 'selected' : ''}>Credit Card</option>
                        <option value="paypal" ${payment.paymentMethod == 'paypal' ? 'selected' : ''}>PayPal</option>
                        <option value="applePay" ${payment.paymentMethod == 'applePay' ? 'selected' : ''}>Apple Pay</option>
                    </select>
                </div>
                <div>
                    <label>Card Number:</label>
                    <input type="text" name="cardNumber" class="input-field" value="${payment.cardNumber}" required>
                </div>
                <div>
                    <label>Expiry Date:</label>
                    <input type="text" name="expiryDate" class="input-field" value="${payment.expiryDate}" required>
                </div>
                <div>
                    <label>CVV:</label>
                    <input type="text" name="cvv" class="input-field" value="${payment.cvv}" required>
                </div>
                <div>
                    <label>Amount:</label>
                    <input type="text" name="amount" class="input-field" value="${payment.amount}" required>
                </div>
                <div>
                    <label>Payment Date:</label>
                    <input type="date" name="paymentDate" class="input-field" value="${payment.paymentDate}" required>
                </div>
                <div class="button-container">
                    <input type="submit" class="btn" value="Save Changes">
                </div>
            </form>
        </div>
        <div class="button-container">
            <a href="savedPaymentDetails.jsp" class="btn">Back to Saved Payments</a>
        </div>
    </main>
</body>
</html>
