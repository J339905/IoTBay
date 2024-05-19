<%@page contentType="text/html" pageEncoding="UTF-8"%> <!-- Setting the content type and character encoding -->
<%@page import="uts.isd.model.Cart"%> <!-- Importing the Cart class -->
<%@page import="uts.isd.model.CartItem"%> <!-- Importing the CartItem class -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- Importing JSTL for use in the JSP -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"> <!-- Character set declaration -->
    <title>Your Cart</title> <!-- Title of the page -->
    <link rel="stylesheet" href="/css/productlist.css"> <!-- Link to the stylesheet -->
</head>
<body>
    <header>
        <h1>Your Shopping Cart</h1> <!-- Page header -->
    </header>

    <main>
        <form method="post" action="updateCart"> <!-- Form for updating cart items -->
            <table>
                <thead> <!-- Table headers -->
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
                        <c:when test="${empty cart.items}"> <!-- Condition to check if the cart is empty -->
                            <tr>
                                <td colspan="6" style="text-align:center;">Your cart is empty</td> <!-- Display message if cart is empty -->
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${cart.items}" var="item"> <!-- Loop through each item in the cart -->
                                <tr>
                                    <td>${item.product.productid}</td>
                                    <td>${item.product.productname}</td>
                                    <td>${item.product.productprice}</td>
                                    <td class="quantity">
                                        <button type="button" class="btn-quantity" onclick="changeQuantity('${item.product.productid}', -1)">-</button> <!-- Button to decrease quantity -->
                                        <input name="quantity_${item.product.productid}" value="${item.quantity}" size="3" readonly> <!-- Quantity input -->
                                        <button type="button" class="btn-quantity" onclick="changeQuantity('${item.product.productid}', 1)">+</button> <!-- Button to increase quantity -->
                                    </td>
                                    <td><input type="submit" class="btn link-btn" value="Update"></td> <!-- Update button -->
                                    <td><button type="button" class="btn btn-remove" onclick="removeItem('${item.product.productid}')">X</button></td> <!-- Remove button -->
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </form>
        <div class="button-container">
            <a href="productlist" class="btn link-btn">Continue Shopping</a> <!-- Link to continue shopping -->
            <c:if test="${not empty cart.items}"> <!-- Condition to show buttons only if cart is not empty -->
                <form action="saveOrders" method="post" style="display: inline;">
                    <button type="submit" class="btn link-btn">Save Order</button> <!-- Button to save the order -->
                </form>
                <a href="updateCart?cancel=true" class="btn link-btn" style="background-color: #ff4444;">Cancel Cart</a> <!-- Link to cancel the cart -->
                <a href="checkout" class="btn link-btn">Proceed to Checkout</a> <!-- Link to proceed to checkout -->
            </c:if>
        </div>
    </main>

    <script>
        function changeQuantity(productId, change) { // JavaScript function to change the quantity
            var quantityInput = document.getElementsByName('quantity_' + productId)[0]; // Get the quantity input field
            var newQuantity = parseInt(quantityInput.value) + change; // Calculate the new quantity
            if (newQuantity >= 1) { // Ensure the quantity doesn't go below 1
                quantityInput.value = newQuantity; // Set the new quantity
            }
        }

        function removeItem(productId) { // JavaScript function to remove an item
            var form = document.createElement('form'); // Create a new form element
            form.method = 'post'; // Set method to post
            form.action = 'updateCart'; // Set action to the updateCart servlet

            var idInput = document.createElement('input'); // Create a new input element
            idInput.type = 'hidden'; // Set the type to hidden
            idInput.name = 'removeProductId'; // Set the name
            idInput.value = productId; // Set the value to the product ID

            form.appendChild(idInput); // Add the input to the form
            document.body.appendChild(form); // Add the form to the document
            form.submit(); // Submit the form
        }
    </script>
</body>
</html>
