<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order Management</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }
        .form-order {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: auto;
        }
    </style>
</head>
<body class="text-center">
    <form class="form-order" action="OrderManagementServlet" method="post">
        <h1 class="h3 mb-3 font-weight-normal">Manage Your Orders</h1>
        <input type="number" name="productId" class="form-control" placeholder="Product ID" required autofocus>
        <input type="number" name="quantity" class="form-control" placeholder="Quantity" required>
        <input type="submit" class="btn btn-lg btn-primary btn-block" value="Place Order">
    </form>

    <hr>

    <%-- Displaying Order Details --%>
    <% Order order = (Order) request.getAttribute("order"); %>
    <% if(order != null) { %>
        <div>
            <h2>Order Details</h2>
            <p>Order ID: <%= order.getOrderId() %></p>
            <p>Date: <%= order.getOrderDate().toString() %></p>
            <p>Status: <%= order.getStatus() %></p>
            <a href="UpdateOrderServlet?orderId=<%= order.getOrderId() %>" class="btn btn-info">Update Order</a>
            <a href="CancelOrderServlet?orderId=<%= order.getOrderId() %>" class="btn btn-danger">Cancel Order</a>
        </div>
    <% } else { %>
        <p>No orders found.</p>
    <% } %>

    <div class="mt-3">
        <a href="dashboard.jsp">Back to Dashboard</a>
    </div>

    <p class="mt-5 mb-3 text-muted">&copy; 2023</p>
</body>
</html>
