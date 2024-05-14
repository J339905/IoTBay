<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.Order"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order Page</title>
</head>
<body>
    <h1>Order List</h1>
    <table border="1">
        <tr>
            <th>Order ID</th>
            <th>Order Date</th>
            <th>Order Status</th>
            <th>Delivery Address</th>
            <th>Quantity</th>
        </tr>
        <%-- Ensure that 'orderList' is provided in the request scope --%>
        <% 
            List<Order> orderList = (List<Order>) request.getAttribute("orderList");
            if (orderList != null) {
                for (Order order : orderList) {
        %>
        <tr>
            <td><%= order.getOrderId() %></td>
            <td><%= order.getOrderDate() %></td>
            <td><%= order.getOrderStatus() %></td>
            <td><%= order.getDeliveryAddress() %></td>
            <td><%= order.getQuantity() %></td>
        </tr>
        <% 
                }
            }
        %>
    </table>
</body>
</html>
