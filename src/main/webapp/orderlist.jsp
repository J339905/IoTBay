<%@page contentType="text/html" pageEncoding="UTF-8"%> <!-- Set the content type and character encoding for the page -->
<%@page import="java.util.*, java.sql.*, uts.isd.model.Order, uts.isd.model.dao.DBConnector, uts.isd.model.dao.OrderDAO"%> <!-- Import necessary Java classes and packages -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- Import JSTL core library for using JSTL tags -->
<!DOCTYPE html> <!-- HTML5 doctype declaration -->
<html lang="en">
<head>
    <meta charset="UTF-8"> <!-- Character set declaration -->
    <title>Order List</title> <!-- Title of the page -->
    <link rel="stylesheet" href="/css/productlist.css"> <!-- Link to an external CSS file -->
    <style>
        .sort-buttons {
            margin: 20px; /* Margin around sort buttons */
            display: flex; /* Use flexbox layout */
            justify-content: center; /* Center buttons horizontally */
        }
        .button-container {
            display: flex; /* Use flexbox layout */
            justify-content: space-between; /* Space between elements */
            margin: 20px 0; /* Margin top and bottom */
            width: 90%; /* Width of the button container */
        }
        .button-container form {
            flex: 1; /* Flex grow to fill available space */
        }
    </style>
</head>
<body>
    <header>
        <h1>Order List</h1> <!-- Page heading -->
    </header>

    <main>
        <div class="sort-buttons">
            <!-- Forms to sort the order list by ID and date, and a search form -->
            <form action="listOrders" method="get">
                <input type="hidden" name="sortBy" value="OrderID"> <!-- Hidden input to hold sort field -->
                <input type="hidden" name="sortOrder" value="${currentSortBy eq 'OrderID' && currentSortOrder eq 'asc' ? 'desc' : 'asc'}"> <!-- Toggle sort order based on current sort state -->
                <button type="submit" class="btn">Sort by Order ID (${currentSortBy eq 'OrderID' ? currentSortOrder : 'asc'})</button> <!-- Sort button displays current sort order -->
            </form>
            <form action="listOrders" method="get">
                <input type="hidden" name="sortBy" value="Order_Date"> <!-- Hidden input to hold sort field -->
                <input type="hidden" name="sortOrder" value="${currentSortBy eq 'Order_Date' && currentSortOrder eq 'asc' ? 'desc' : 'asc'}"> <!-- Toggle sort order based on current sort state -->
                <button type="submit" class="btn">Sort by Date (${currentSortBy eq 'Order_Date' ? currentSortOrder : 'asc'})</button> <!-- Sort button displays current sort order -->
            </form>
            <form action="listOrders" method="get">
                <select name="searchType">
                    <option value="OrderID">Order ID</option> <!-- Option to select order ID for searching -->
                    <option value="Order_Date">Order Date</option> <!-- Option to select order date for searching -->
                </select>
                <input type="text" name="searchTerm" placeholder="Enter search term..."> <!-- Input for search term -->
                <button type="submit" class="btn">Search</button> <!-- Button to submit search -->
            </form>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Order Date</th>
                    <th>Order Status</th>
                    <th>Delivery Address</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                <!-- Loop through each order and display its properties -->
                <c:forEach var="order" items="${orderList}">
                    <tr>
                        <td>${order.orderId}</td>
                        <td>${order.orderDate}</td>
                        <td>${order.orderStatus}</td>
                        <td>${order.deliveryAddress}</td>
                        <td>${order.quantity}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="button-container">
            <a href="dashboard.jsp" class="btn link-btn">Return to Dashboard</a> <!-- Link to return to the dashboard -->
        </div>
    </main>
</body>
</html>
