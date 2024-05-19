<%@page contentType="text/html" pageEncoding="UTF-8"%> <!-- Setting the content type and character encoding for the page -->
<!DOCTYPE html> <!-- Declaration for HTML5 -->
<html lang="en">
<head>
    <meta charset="UTF-8"> <!-- Character set declaration -->
    <title>Order Confirmation</title> <!-- Title of the page -->
    <style>
        body {
            font-family: Arial, sans-serif; /* Set the font for the page */
            background-color: #f4f4f4; /* Background color of the page */
            margin: 0; /* Reset margin for the body */
            padding: 20px; /* Padding around the content */
            display: flex; /* Using flexbox for layout */
            flex-direction: column; /* Stack children vertically */
            align-items: center; /* Align children in the center horizontally */
        }
        h1 {
            color: #333; /* Color for the heading */
            margin-top: 20px; /* Top margin for the heading */
        }
        p {
            font-size: 16px; /* Font size for paragraph */
            color: #666; /* Color for the text */
        }
        a {
            display: inline-block; /* Display link as inline-block for sizing properties */
            padding: 10px 20px; /* Padding inside the link */
            font-size: 16px; /* Font size for the link text */
            color: #fff; /* Text color for the link */
            background-color: #007BFF; /* Background color for the link */
            border: none; /* No border for the link */
            border-radius: 4px; /* Rounded corners for the link */
            text-decoration: none; /* Remove underline from the link */
            transition: background-color 0.3s; /* Smooth transition for background color change */
            margin-top: 20px; /* Top margin for the link */
        }
        a:hover {
            background-color: #0056b3; /* Background color on hover for the link */
        }
    </style>
</head>
<body>
    <h1>Thank You!</h1> <!-- Main heading of the page -->
    <p>Your order has been successfully placed.</p> <!-- Confirmation message -->
    <a href="dashboard.jsp">Return to Dashboard</a> <!-- Link back to the dashboard -->
</body>
</html>
