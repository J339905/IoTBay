<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.Cart"%>
<%@page import="uts.isd.model.CartItem"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        h1 {
            color: #333;
            margin-top: 20px;
        }
        table {
            width: 90%;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            justify-content: center;

        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: center;
            justify-content: center;

        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .btn, .btn-remove, .btn-quantity {
            padding: 5px 10px;
            margin: 0 5px;
            font-size: 16px;
            color: #007BFF;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn:hover, .btn-remove:hover, .btn-quantity:hover {
            background-color: #f2f2f2;
        }
        .btn-quantity {
            border-radius: 50%;
            width: 30px;
            height: 30px;
            display: inline-flex;
            align-items: center;
            justify-content: center;
        }
        .btn-remove {
            color: #ff0000;
        }
        th.quantity, td.quantity {
            width: 160px;
        }
        .link-btn {
            color: #fff;
            background-color: #007BFF;
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 4px;
        }
        .link-btn:hover {
            background-color: #0056b3;
        }
        .button-container {
            display: flex;
            justify-content: center;
        }
    </style>
</head>
<body>
    <h1>Your Shopping Cart</h1>
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
            </tbody>
        </table>
    </form>
<div class="button-container">
    <a href="/listProducts" class="btn link-btn">Continue Shopping</a>
    <!-- Check if there are items in the cart before showing the save option -->
    <c:if test="${not empty cart.items}">
<form action="/saveOrders" method="post">
    <button type="submit" class="btn link-btn">Save Order</button>
</form>

        <a href="updateCart?cancel=true" class="btn link-btn" style="background-color: #ff4444;">Cancel Cart</a>
        <a href="/checkout" class="btn link-btn">Proceed to Checkout</a>
    </c:if>
</div>


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
