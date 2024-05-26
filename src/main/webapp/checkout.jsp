<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.Cart"%>
<%@page import="uts.isd.model.CartItem"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- Import JSTL tag library for server-side functionality -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <link rel="stylesheet" href="/css/productlist.css"> <!-- Link to external CSS for styling -->
    <style>
        /* Styling for the page layout, buttons, and input fields */
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
            width: 90%;
            max-width: 800px;
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
            justify-content: space-between;
            margin: 20px 0;
            width: 100%;
        }
        .button-container a, .button-container button {
            flex: 1;
            margin: 0 5px;
        }
    </style>
</head>
<body>
    <header>
        <h1>Checkout</h1> <!-- Header section for page title -->
    </header>

    <main class="summary-container">
        <div class="summary">
            <h2>Order Summary</h2> <!-- Order summary section -->
            <table> <!-- Table to display cart items dynamically -->
                <thead>
                    <tr>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Unit Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${cart.items}" var="item"> <!-- Loop through cart items -->
                        <tr>
                            <td>${item.product.productname}</td>
                            <td>${item.quantity}</td>
                            <td>$${item.product.productprice}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <p>Total Price: $${cart.totalPrice}</p> <!-- Display total price -->
            <p>Total Quantity: ${cart.totalQuantity}</p> <!-- Display total quantity -->
            <form action="createPayment" method="post"> <!-- Form for checkout details -->
                <input type="hidden" name="orderID" value="${orderID}">
                <input type="hidden" name="amount" value="${cart.totalPrice}">
                <h2>Delivery Address</h2>
                <input type="text" name="deliveryAddress" class="input-field" placeholder="Enter delivery address" required>
                <h2>Payment Method</h2>
                <select name="paymentMethod" class="input-field" required> <!-- Payment method selection -->
                    <option value="Credit Card">Credit Card</option>
                    <option value="PayPal">PayPal</option>
                    <option value="Apple Pay">Apple Pay</option>
                </select>
                <h2>Card Holder Name</h2>
                <input type="text" name="cardHolderName" class="input-field" placeholder="Enter card holder name" required>
                <h2>Credit Card Number</h2>
                <input type="text" name="cardNumber" class="input-field" placeholder="Enter 16-digit card number" required pattern="\d{16}">
                <h2>CVV</h2>
                <input type="text" name="cvv" class="input-field" placeholder="Enter CVV" required pattern="\d{3}">
                <h2>Expiry Date</h2>
                <input type="text" name="expiryDate" class="input-field" placeholder="MM/YY" required pattern="\d{2}/\d{2}">
                <h2>Amount</h2>
                <input type="text" name="amount" class="input-field" value="${cart.totalPrice}" readonly>
                <h2>Payment Date</h2>
                <input type="text" name="paymentDate" class="input-field" placeholder="dd/mm/yyyy" required>
                <div class="button-container">
                    <button type="submit" class="btn" onclick="this.form.action='paymentSuccess.jsp'; this.form.submit();">Complete Purchase</button>
                    <a href="viewSavedPayments" class="btn link-btn">Save Order Payment Details</a>
                </div>
            </form>
        </div>
        <div class="button-container">
            <a href="viewCart" class="link-btn">Back to Cart</a> <!-- Link back to cart page -->
        </div>
    </main>
</body>
</html>
