<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Make a Payment</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body { 
            padding-top: 40px; 
            padding-bottom: 40px; 
            background-image: url('/css/Mountain-Background.jpeg'); 
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            background-color: #f5f5f5; 
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
      
        .form-payment {
            width: 100%;
            max-width: 330px;
            padding: 10px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .form-control {
            position: relative;
            box-sizing: border-box;
            height: auto;
            padding: 10px;
            font-size: 16px;
            margin-bottom: 15px; /* Added space for visual comfort */
        }
        .form-control:focus {
            z-index: 2;
        }
        .form-payment button {
            margin-top: 10px;
        }
        .back-home {
            display: block;
            width: 100%;
            margin-top: 20px; /
        }
    </style>
</head>
<body>
    <form class="form-payment" method="POST" action="/PaymentServlet?action=create" novalidate>
        <h1 class="h3 mb-3 font-weight-normal">Make a Payment</h1>
        <input type="text" name="orderID" class="form-control" placeholder="Order ID" required autofocus>
        <input type="text" name="amount" class="form-control" placeholder="Amount" required>
        <select class="form-control" name="paymentMethod" required>
            <option value="">Select Payment Method</option>
            <option value="creditCard">Credit Card</option>
            <option value="paypal">PayPal</option>
            <option value="applepay">Apple Pay</option>
        </select>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit Payment</button>
        <a href="dashboard.jsp" class="btn btn-lg btn-secondary btn-block back-home">Back to Dashboard</a>
    </form>
</body>
</html>
