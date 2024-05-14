<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.Product"%>
<!DOCTYPE html>
<head>
    <title>Update Product</title>
</head>
<body>
    <h1>Update Product</h1>
    <form action="UpdateProductServlet" method="post">
        <label for="id">Product ID:</label>
        <input type="text" id="id" name="id" readonly value="${product.productid}"><br>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${product.productname}"><br>
        <label for="type">Type:</label>
        <input type="text" id="type" name="type" value="${product.productcategory}"><br>
        <label for="description">Description:</label>
        <input type="text" id="description" name="description" value="${product.productdescription}"><br>
        <label for="price">Price:</label>
        <input type="text" id="price" name="price" value="${product.productprice}"><br>
        <label for="stock">Stock:</label>
        <input type="text" id="stock" name="stock" value="${product.productstock}"><br>
        <button type="submit">Update Product</button>
    </form>
</body>
</html>
