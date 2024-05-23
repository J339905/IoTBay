<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Make a Payment</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('/css/Mountain-Background.jpeg');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .form-container {
            background: rgba(255, 255, 255, 0.9);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
            max-width: 400px;
            width: 100%;
        }
        .form-container form label,
        .form-container form input {
            width: 100%;
            margin-bottom: 10px;
        }
        .form-container form button {
            width: 100%;
            padding: 10px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
        }
        .form-container form button:hover {
            background-color: #0056b3;
        }
        .form-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Make a New Payment</h2>
        <form action="payment" method="post">
            <input type="hidden" name="action" value="create">
            <label for="orderId">Order ID:</label>
            <input type="text" id="orderId" name="orderId" required>
            
            <label for="amount">Amount:</label>
            <input type="text" id="amount" name="amount" required>
            
            <label for="paymentDate">Payment Date:</label>
            <input type="datetime-local" id="paymentDate" name="paymentDate" required>
            
            <label for="paymentMethod">Payment Method:</label>
            <input type="text" id="paymentMethod" name="paymentMethod" required>
            
            <label for="creditCardDetails">Credit Card Details:</label>
            <input type="text" id="creditCardDetails" name="creditCardDetails" required>
            
            <button type="submit">Submit Payment</button>
        </form>
    </div>
</body>
</html>
