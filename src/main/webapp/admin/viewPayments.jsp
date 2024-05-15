<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="uts.isd.model.Payment"%>
<jsp:include page="/ListPaymentDetailsServlet" flush="true"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Payments</title>

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

        .payment-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .payment-table th, .payment-table td {
            border: 1px solid #bdc3c7;
            padding: 10px;
            text-align: left;
        }

        .payment-table th {
            background-color: #2c3e50;
            color: white;
        }

        .payment-table tbody tr:nth-child(even) {
            background-color: #f4f4f4;
        }

        .btn {
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            text-align: center;
        }

        .btn-primary {
            background-color: #3498db;
            color: white;
        }

        .btn-primary:hover {
            background-color: #2980b9;
        }

        .btn-secondary {
            background-color: #2ecc71;
            color: white;
        }

        .btn-secondary:hover {
            background-color: #27ae60;
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
        <h1>View All Payments</h1>
        <p>Below is a list of all payments:</p>
        <table class="payment-table">
            <thead>
                <tr>
                    <!-- Add your table headers here -->
                </tr>
            </thead>
            <tbody>
                <% 
                    ArrayList<Payment> payments = (ArrayList<Payment>) request.getAttribute("payments");
                    if (payments != null) {
                        for(Payment payment : payments) {
                %>
                            <tr>
                                <!-- Add your table row data here -->
                            </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
