<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Details</title>
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
        <h1>Payment Details</h1>
        <p class="lead">Here are your payment details:</p>
        <p class="lead">Card Holder Name: <strong>${payment.cardHolderName}</strong></p>
        <p class="lead">Credit Card Number: <strong>${payment.creditCardNumber}</strong></p>
        <p class="lead">CVV: <strong>${payment.cvv}</strong></p>
        <p class="lead">Expiry Date: <strong>${payment.expiryDate}</strong></p>
        <p class="lead">Amount: <strong>${payment.amount}</strong></p>
        <p class="lead">Date: <strong>${payment.date}</strong></p>
        <div class="alert alert-warning">
            <strong>Important:</strong> Do not share your credit card details.
        </div>
        <div class="mt-3">
            <a href="/viewPaymentHistory.jsp" class="btn btn-secondary btn-custom">Back to Payment History</a>
        </div>
    </div>
</body>
</html>
