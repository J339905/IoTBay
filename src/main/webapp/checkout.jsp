<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.Cart"%>
<%@page import="uts.isd.model.CartItem"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
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
        .summary {
            margin: 20px;
            padding: 20px;
            border: 1px solid #ddd;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .summary th, .summary td {
            padding: 10px;
            text-align: left;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
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
        }
        .link-btn:hover {
            background-color: #0056b3;
        }
        .input-field {
            width: 100%;
            padding: 8px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
    <h1>Checkout</h1>
    <div class="summary">
        <h2>Order Summary</h2>
        <table>
            <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
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
        <form action="processCheckout" method="post">
            <h2>Delivery Address</h2>
            <input type="text" name="deliveryAddress" class="input-field" placeholder="Enter delivery address" required>
            <h2>Payment Method</h2>
            <select name="paymentMethod">
                <option value="creditCard">Credit Card</option>
                <option value="paypal">PayPal</option>
                <option value="applepay">Apple Pay</option>
            </select>
            <input type="submit" class="btn" value="Complete Purchase">
        </form>
    </div>
    <a href="viewCart" class="link-btn">Back to Cart</a> <!-- Back to Cart Button -->
</body>
</html>
