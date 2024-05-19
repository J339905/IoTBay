<%@page contentType="text/html" pageEncoding="UTF-8"%> <!-- Setting the content type and character encoding -->
<%@page import="uts.isd.model.Cart"%> <!-- Importing the Cart class -->
<%@page import="uts.isd.model.CartItem"%> <!-- Importing the CartItem class -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- Importing JSTL for use in the JSP -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"> <!-- Character set declaration -->
    <title>Checkout</title> <!-- Title of the page -->
    <link rel="stylesheet" href="/css/productlist.css"> <!-- Link to the stylesheet -->
    <style>
        body {
            font-family: Arial, sans-serif; <!-- Setting the font for the page -->
            background-color: #f4f4f4; <!-- Background color of the page -->
            margin: 0; padding: 0; <!-- Margin and padding for the body -->
            display: flex; <!-- Using flexbox for layout -->
            justify-content: center; <!-- Centering content horizontally -->
            align-items: center; <!-- Centering content vertically -->
            height: 100vh; <!-- Full viewport height -->
        }
        .summary-container {
            width: 90%; <!-- Width of the summary container -->
            max-width: 800px; <!-- Maximum width of the summary container -->
        }
        .summary {
            margin: 20px auto; <!-- Margin for the summary box -->
            padding: 20px; <!-- Padding inside the summary box -->
            border: 1px solid #ddd; <!-- Border around the summary box -->
            background-color: #fff; <!-- Background color of the summary box -->
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); <!-- Shadow for the summary box -->
            width: 100%; <!-- Full width of the container -->
        }
        .summary th, .summary td {
            padding: 10px; <!-- Padding for table cells -->
            text-align: left; <!-- Text alignment in table cells -->
        }
        .input-field {
            width: 100%; <!-- Full width for input fields -->
            padding: 8px; <!-- Padding inside input fields -->
            margin: 10px 0; <!-- Margin around input fields -->
            border: 1px solid #ccc; <!-- Border color for input fields -->
            border-radius: 4px; <!-- Border radius for input fields -->
            box-sizing: border-box; <!-- Box sizing to include padding in width/height -->
        }
        .btn {
            padding: 10px 20px; <!-- Padding inside buttons -->
            color: #fff; <!-- Text color for buttons -->
            background-color: #007BFF; <!-- Background color for buttons -->
            border: none; <!-- No border for buttons -->
            border-radius: 4px; <!-- Border radius for buttons -->
            cursor: pointer; <!-- Cursor as pointer to indicate clickable -->
            text-decoration: none; <!-- No text decoration for links styled as buttons -->
            display: block; <!-- Display block to fill the width -->
            width: 200px; <!-- Fixed width for buttons -->
            text-align: center; <!-- Center text inside buttons -->
            margin-top: 20px; <!-- Margin on top of buttons -->
        }
        .btn:hover {
            background-color: #0056b3; <!-- Background color on hover for buttons -->
        }
        .link-btn {
            padding: 10px 20px; <!-- Padding inside link buttons -->
            background-color: #007BFF; <!-- Background color for link buttons -->
            color: #fff; <!-- Text color for link buttons -->
            border: none; <!-- No border for link buttons -->
            border-radius: 4px; <!-- Border radius for link buttons -->
            text-decoration: none; <!-- No text decoration for links -->
            display: inline-block; <!-- Display as inline-block for layout -->
            margin: 5px; <!-- Margin around link buttons -->
            text-align: center; <!-- Center text inside link buttons -->
            width: 100%; <!-- Full width for link buttons -->
        }
        .link-btn:hover {
            background-color: #0056b3; <!-- Background color on hover for link buttons -->
        }
        .button-container {
            display: flex; <!-- Using flexbox for button container layout -->
            justify-content: center; <!-- Centering content horizontally -->
            margin: 20px 0; <!-- Margin around button container -->
            width: 100%; <!-- Full width for button container -->
        }
    </style>
</head>
<body>
    <header>
        <h1>Checkout</h1> <!-- Page header -->
    </header>

    <main class="summary-container">
        <div class="summary">
            <h2>Order Summary</h2> <!-- Section title -->
            <table> <!-- Table for order summary -->
                <thead> <!-- Table header -->
                    <tr> <!-- Table row -->
                        <th>Product Name</th> <!-- Column header -->
                        <th>Quantity</th> <!-- Column header -->
                        <th>Unit Price</th> <!-- Column header -->
                    </tr>
                </thead>
                <tbody> <!-- Table body -->
                    <c:forEach items="${cart.items}" var="item"> <!-- Iterating over cart items -->
                        <tr> <!-- Table row for each item -->
                            <td>${item.product.productname}</td> <!-- Display product name -->
                            <td>${item.quantity}</td> <!-- Display quantity -->
                            <td>$${item.product.productprice}</td> <!-- Display unit price -->
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <p>Total Price: $${cart.totalPrice}</p> <!-- Display total price -->
            <p>Total Quantity: ${cart.totalQuantity}</p> <!-- Display total quantity -->
            <form action="processCheckout" method="post"> <!-- Form for checkout -->
                <h2>Delivery Address</h2> <!-- Section for delivery address -->
                <input type="text" name="deliveryAddress" class="input-field" placeholder="Enter delivery address" required> <!-- Input for address -->
                <h2>Payment Method</h2> <!-- Section for payment method -->
                <select name="paymentMethod" class="input-field"> <!-- Selection for payment method -->
                    <option value="creditCard">Credit Card</option> <!-- Option for credit card -->
                    <option value="paypal">PayPal</option> <!-- Option for PayPal -->
                    <option value="applepay">Apple Pay</option> <!-- Option for Apple Pay -->
                </select>
                <input type of "submit" class="btn" value="Complete Purchase"> <!-- Submit button -->
            </form>
        </div>
        <div class="button-container">
            <a href="viewCart" class="link-btn">Back to Cart</a> <!-- Link back to cart -->
        </div>
    </main>
</body>
</html>
