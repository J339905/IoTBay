<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create New Payment</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .content {
            max-width: 600px;
            margin: 50px auto;
            background: white;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        .content h1 {
            text-align: center;
            color: #34495e;
        }

        .content p {
            text-align: center;
            color: #7f8c8d;
        }

        .payment-form {
            display: flex;
            flex-direction: column;
        }

        .payment-form label {
            margin: 10px 0 5px 0;
            color: #34495e;
        }

        .payment-form input,
        .payment-form select {
            padding: 10px;
            border: 1px solid #bdc3c7;
            border-radius: 5px;
            margin-bottom: 10px;
        }

        .btn {
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .btn-primary {
            background-color: #3498db;
            color: white;
        }

        .btn-primary:hover {
            background-color: #2980b9;
        }

    </style>
</head>
<body>

    <!-- Navigation Bar -->
    <!-- Add your navigation bar here -->
    <nav>
        <ul>
            <li><a href="/admin.jsp">Admin Page</a></li>
            <li><a href="/admin/createPayment.jsp">Create Payment</a></li>
            <li><a href="/admin/viewPayments.jsp">View Payments</a></li>
            <li><a href="/admin/searchPayments.jsp">Search Payments</a></li>
            <li><a href="/listProductsAdmin">View Products</a></li>
            <li><a href="/admin/addProduct.jsp">Add Products</a></li>
            <li><a href="/admin/updateProduct.jsp">Update Products</a></li>
            <li><a href="/admin/logout.jsp">Logout</a></li>
        </ul>
    </nav>
    <!-- Page Content -->
    <div class="content">
        <h1>Create a New Payment</h1>
        <p>Fill out the form below to add a new payment.</p>

        <!-- Payment Creation Form -->
        <form action="/CreatePaymentDetailsServlet" method="post" class="payment-form">
            <!-- Add your input fields here -->
            <button type="submit" class="btn btn-primary">Create Payment</button>
        </form>
    </div>

</body>
</html>
