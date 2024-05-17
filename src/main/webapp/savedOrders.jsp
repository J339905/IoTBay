<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Saved Orders</title>
</head>
<body>
    <h1>Saved Orders</h1>
    <c:forEach items="${savedCarts}" var="cart">
        <h2>Order from ${cart.savedDate}</h2>
        <ul>
            <c:forEach items="${cart.items}" var="item">
                <li>${item.product.name} - Quantity: ${item.quantity}</li>
            </c:forEach>
        </ul>
    </c:forEach>
</body>
</html>
