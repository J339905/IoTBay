<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Payments</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .content {
            max-width: 800px;
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
            gap: 10px;
            margin-top: 20px;
        }

        .payment-form label, .payment-form input, .payment-form button {
            font-size: 16px;
        }

        .payment-form input {
            padding: 10px;
            border: 1px solid #bdc3c7;
            border-radius: 5px;
        }

        .btn {
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            text-align: center;
        }

        .btn-search {
            background-color: #3498db;
            color: white;
        }

        .btn-search:hover {
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

    <div class="content">
        <h1>Search Payments</h1>
        <p>Search for payments by criteria below:</p>

        <!-- Payment Search Form -->
        <form action="/SearchPaymentServlet" method="get" class="payment-form">
            <!-- Add your input fields here -->
            <button type="submit" class="btn btn-search">Search</button>
        </form>
    </div>

</body>
</html>
