<%@page import="java.util.ArrayList"%>
<%@page import="uts.isd.model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product List</title>
</head>
<body>
    <h1>Product List</h1>
    <table border="1">
        <tr>
            <th>Product ID</th>
            <th>Product Name</th>
            <th>Category</th>
            <th>Description</th>
            <th>Price</th>
            <th>Stock</th>
        </tr>
        <%
            ArrayList<Product> productList = (ArrayList<Product>) session.getAttribute("productList");
            if (productList != null && !productList.isEmpty()) {
                for (Product product : productList) {
        %>
        <tr>
            <td><%= product.getProductid() %></td>
            <td><%= product.getProductname() %></td>
            <td><%= product.getProductcategory() %></td>
            <td><%= product.getProductdescription() %></td>
            <td><%= product.getProductprice() %></td>
            <td><%= product.getProductstock() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="6">No products available.</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
