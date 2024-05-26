<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment History</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
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
        .container {
            background: white;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            margin-top: 40px;
        }
        .lead {
            font-size: 16px;
            color: #666;
        }
        .btn-custom {
            margin-top: 20px;
            width: 200px;
        }
        .alert-warning {
            color: #856404;
            background-color: #fff3cd;
            border-color: #ffeeba;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1>Payment History</h1>
        <form action="searchPaymentHistory" method="get">
            <div class="form-group">
                <label for="paymentId">Payment ID:</label>
                <input type="text" id="paymentId" name="paymentId" class="form-control">
            </div>
            <div class="form-group">
                <label for="date">Date:</label>
                <input type="date" id="date" name="date" class="form-control">
            </div>
            <button type="submit" class="btn btn-primary">Search</button>
        </form>
        <div class="mt-3">
            <h2>Results:</h2>
            <c:forEach var="payment" items="${payments}">
                <div class="payment-details">
                    <p>Payment ID: ${payment.paymentId}</p>
                    <p>Card Holder Name: ${payment.cardHolderName}</p>
                    <p>Credit Card Number: ${payment.creditCardNumber}</p>
                    <p>CVV: ${payment.cvv}</p>
                    <p>Expiry Date: ${payment.expiryDate}</p>
                    <p>Amount: ${payment.amount}</p>
                    <p>Date: ${payment.date}</p>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
