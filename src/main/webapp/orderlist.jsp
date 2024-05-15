<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, java.sql.*, uts.isd.model.Order, uts.isd.model.dao.DBConnector, uts.isd.model.dao.OrderDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
    </style>
</head>
<body>
    <h1>Order List</h1>
    <%
        List<Order> orderList = null;
        DBConnector db = new DBConnector();
        try {
            Connection conn = db.openConnection();
            OrderDAO orderDAO = new OrderDAO(conn);
            orderList = orderDAO.listAllOrders();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.closeConnection(); // Ensure you close the connection
        }
    %>
<form action="listOrders" method="get">
    <input type="hidden" name="sortBy" value="OrderID">
    <input type="hidden" name="sortOrder" value="${currentSortOrder eq 'asc' && currentSortBy eq 'OrderID' ? 'desc' : 'asc'}">
    <button type="submit">Sort by Order ID (${currentSortBy eq 'OrderID' ? currentSortOrder : 'asc'})</button>
</form>
<form action="listOrders" method="get">
    <input type="hidden" name="sortBy" value="Order_Date">
    <input type="hidden" name="sortOrder" value="${currentSortOrder eq 'asc' && currentSortBy eq 'Order_Date' ? 'desc' : 'asc'}">
    <button type="submit">Sort by Date (${currentSortBy eq 'Order_Date' ? currentSortOrder : 'asc'})</button>
</form>
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
</body>
</html>
