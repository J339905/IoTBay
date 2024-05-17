<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, java.sql.*, uts.isd.model.Order, uts.isd.model.dao.DBConnector, uts.isd.model.dao.OrderDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order List</title>
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
            margin-top: 20px;
            color: #333;
        }
        table {
            width: 90%;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
            padding: 12px;
            text-align: center;
        }
        td {
            padding: 12px;
            text-align: center;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #007BFF;
            border: none;
            border-radius: 4px;
            text-decoration: none;
            margin: 5px;
            transition: background-color 0.3s;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .sort-buttons {
            margin: 20px;
            display: flex;
            justify-content: center;
        }
    </style>
</head>
<body>
    <h1>Order List</h1>
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
    <a href="dashboard.jsp" class="btn">Return to Dashboard</a>
</body>
</html>
