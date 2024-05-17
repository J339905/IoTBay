<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, java.sql.*, uts.isd.model.Order, uts.isd.model.dao.DBConnector, uts.isd.model.dao.OrderDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order List</title>
    <link rel="stylesheet" href="/css/productlist.css">
    <style>
        .sort-buttons {
            margin: 20px;
            display: flex;
            justify-content: center;
        }
        .button-container {
            display: flex;
            justify-content: space-between;
            margin: 20px 0;
            width: 90%;
        }
        .button-container form {
            flex: 1;
        }


    </style>
</head>
<body>a
    <header>
        <h1>Order List</h1>
    </header>

    <main>
        <div class="sort-buttons">
            <form action="listOrders" method="get">
                <input type="hidden" name="sortBy" value="OrderID">
                <input type="hidden" name="sortOrder" value="${currentSortBy eq 'OrderID' && currentSortOrder eq 'asc' ? 'desc' : 'asc'}">
                <button type="submit" class="btn">Sort by Order ID (${currentSortBy eq 'OrderID' ? currentSortOrder : 'asc'})</button>
            </form>
            <form action="listOrders" method="get">
                <input type="hidden" name="sortBy" value="Order_Date">
                <input type="hidden" name="sortOrder" value="${currentSortBy eq 'Order_Date' && currentSortOrder eq 'asc' ? 'desc' : 'asc'}">
                <button type="submit" class="btn">Sort by Date (${currentSortBy eq 'Order_Date' ? currentSortOrder : 'asc'})</button>
            </form>
            <form action="listOrders" method="get">
                <select name="searchType">
                    <option value="OrderID">Order ID</option>
                    <option value="Order_Date">Order Date</option>
                </select>
                <input type="text" name="searchTerm" placeholder="Enter search term...">
                <button type="submit" class="btn">Search</button>
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
            <a href="dashboard.jsp" class="btn link-btn">Return to Dashboard</a>
        </div>
    </main>
</body>
</html>
