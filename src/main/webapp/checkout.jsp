<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.Cart"%>
<%@page import="uts.isd.model.CartItem"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
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
            width: calc(50% - 10px);
            text-align: center;
            margin-top: 20px;
            box-sizing: border-box;
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
            width: calc(50% - 10px);
            box-sizing: border-box;
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
        .button-container a, .button-container input, .button-container button {
            flex: 1;
            margin: 5px;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
    <header>
        <h1>Checkout</h1>
    </header>

    <main class="summary-container">
        <div class="summary">
            <h2>Order Summary</h2>
            <table>
                <thead>
                    <tr>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Unit Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${cart.items}" var="item">
                        <tr>
                            <td>${item.product.productname}</td>
                            <td>${item.quantity}</td>
                            <td>$${item.product.productprice}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <p>Total Price: $${cart.totalPrice}</p>
            <p>Total Quantity: ${cart.totalQuantity}</p>
            <form action="saveOrderPaymentDetails" method="post">
                <h2>Delivery Address</h2>
                <input type="text" name="deliveryAddress" class="input-field" placeholder="Enter delivery address" required>
                <h2>Payment Method</h2>
                <select name="paymentMethod" class="input-field" onchange="toggleCreditCardDetails(this.value)">
                    <option value="creditCard">Credit Card</option>
                    <option value="paypal">PayPal</option>
                    <option value="applepay">Apple Pay</option>
                </select>
                <div id="creditCardDetails">
                    <h2>Credit Card Details</h2>
                    <input type="text" name="cardName" class="input-field" placeholder="Cardholder Name" required>
                    <input type="text" name="cardNumber" class="input-field" placeholder="Card Number (16 digits)" pattern="\d{16}" required>
                    <input type="text" name="expiryDate" class="input-field" placeholder="Expiry Date (MM/YY)" pattern="\d{2}/\d{2}" required>
                    <input type="text" name="cvv" class="input-field" placeholder="CVV (3 digits)" pattern="\d{3}" required>
                </div>
                <h2>Amount</h2>
                <input type="text" name="amount" class="input-field" placeholder="Amount" value="${cart.totalPrice}" required>
                <h2>Payment Date</h2>
                <input type="date" name="paymentDate" class="input-field" value="${currentDate}" required>
                <div class="button-container">
                    <input type="submit" class="btn" value="Complete Purchase">
                    <button type="button" class="btn" onclick="saveOrderPaymentDetails()">Save Order Payment Details</button>
                </div>
            </form>
        </div>
        <div class="button-container">
            <a href="viewCart" class="link-btn">Back to Cart</a>
        </div>
    </main>

    <script>
        function toggleCreditCardDetails(paymentMethod) {
            var creditCardDetails = document.getElementById('creditCardDetails');
            if (paymentMethod === 'creditCard') {
                creditCardDetails.style.display = 'block';
            } else {
                creditCardDetails.style.display = 'none';
            }
        }

        function saveOrderPaymentDetails() {
            // This function will submit the form to a different servlet to save the order payment details
            var form = document.querySelector('form');
            form.action = 'saveOrderPaymentDetails';
            form.submit();
        }

        // Initialize the credit card details section
        toggleCreditCardDetails(document.querySelector('select[name="paymentMethod"]').value);
    </script>
</body>
</html>
