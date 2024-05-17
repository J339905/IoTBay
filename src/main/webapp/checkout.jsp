<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.Cart"%>
<%@page import="uts.isd.model.CartItem"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <link rel="stylesheet" href="<c:url value='/css/payment.css'/>">
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
        <form action="PaymentServlet" method="post">
            <h2>Delivery Address</h2>
            <input type="text" name="deliveryAddress" class="input-field" placeholder="Enter delivery address" required>
            <h2>Payment Method</h2>
            <select name="paymentMethod" class="input-field">
                <option value="creditCard">Credit Card</option>
                <option value="paypal">PayPal</option>
                <option value="applepay">Apple Pay</option>
            </select>
            <h2>Amount</h2>
            <input type="text" name="amount" class="input-field" value="${cart.totalPrice}" readonly>
            <input type="submit" class="btn" value="Complete Purchase">
        </form>
    </div>
    <a href="viewCart" class="link-btn">Back to Cart</a> <!-- Back to Cart Button -->
</body>
</html>
