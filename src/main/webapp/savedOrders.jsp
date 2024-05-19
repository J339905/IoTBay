<%@page contentType="text/html" pageEncoding="UTF-8"%> <!-- Sets the MIME type and character encoding for the response -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- Includes the JSTL core library to use its functionality in the JSP -->

<!DOCTYPE html> <!-- Declares that this document is an HTML5 document -->
<html lang="en">
<head>
    <meta charset="UTF-8"> <!-- Specifies the character set for the HTML document -->
    <title>Saved Orders</title> <!-- Sets the title of the document shown in the browser's title bar or tab -->
</head>
<body>
    <h1>Saved Orders</h1> <!-- Main heading of the page, indicating the content of the page -->

    <!-- Iterate over each 'cart' in 'savedCarts' passed to the page -->
    <c:forEach items="${savedCarts}" var="cart">
        <h2>Order from ${cart.savedDate}</h2> <!-- Subheading showing the saved date of each order -->
        <ul>
            <!-- Iterate over each 'item' in the 'items' collection of the current 'cart' -->
            <c:forEach items="${cart.items}" var="item">
                <li>${item.product.name} - Quantity: ${item.quantity}</li> <!-- List each item and quantity -->
            </c:forEach>
        </ul>
    </c:forEach>

</body>
</html>
