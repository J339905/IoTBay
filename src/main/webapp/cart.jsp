<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.Cart"%>
<%@page import="uts.isd.model.CartItem"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <link rel="stylesheet" href="/css/productlist.css">
</head>
<body>
    <header>
        <h1>Your Shopping Cart</h1>
    </header>

    <main>
        <form method="post" action="updateCart">
            <table>
                <thead>
                    <tr>
                        <th>Product ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Update</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty cart.items}">
                            <tr>
                                <td colspan="6" style="text-align:center;">Your cart is empty</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${cart.items}" var="item">
                                <tr>
                                    <td>${item.product.productid}</td>
                                    <td>${item.product.productname}</td>
                                    <td>${item.product.productprice}</td>
                                    <td class="quantity">
                                        <button type="button" class="btn-quantity" onclick="changeQuantity('${item.product.productid}', -1)">-</button>
                                        <input name="quantity_${item.product.productid}" value="${item.quantity}" size="3" readonly>
                                        <button type="button" class="btn-quantity" onclick="changeQuantity('${item.product.productid}', 1)">+</button>
                                    </td>
                                    <td><input type="submit" class="btn link-btn" value="Update"></td>
                                    <td><button type="button" class="btn btn-remove" onclick="removeItem('${item.product.productid}')">X</button></td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </form>
        <div class="button-container">
            <a href="productlist" class="btn link-btn">Continue Shopping</a>
            <c:if test="${not empty cart.items}">
                <form action="saveOrders" method="post" style="display: inline;">
                    <button type="submit" class="btn link-btn">Save Order</button>
                </form>
                <a href="updateCart?cancel=true" class="btn link-btn" style="background-color: #ff4444;">Cancel Cart</a>
                <a href="checkout" class="btn link-btn">Proceed to Checkout</a>
            </c:if>
        </div>
    </main>

    <script>
        function changeQuantity(productId, change) {
            var quantityInput = document.getElementsByName('quantity_' + productId)[0];
            var newQuantity = parseInt(quantityInput.value) + change;
            if (newQuantity >= 1) {
                quantityInput.value = newQuantity;
            }
        }

        function removeItem(productId) {
            var form = document.createElement('form');
            form.method = 'post';
            form.action = 'updateCart';

            var idInput = document.createElement('input');
            idInput.type = 'hidden';
            idInput.name = 'removeProductId';
            idInput.value = productId;

            form.appendChild(idInput);
            document.body.appendChild(form);
            form.submit();
        }
    </script>
</body>
</html>