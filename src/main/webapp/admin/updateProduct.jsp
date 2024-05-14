<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
<title>Update Product</title>
</head>
<body>
    <h1>Update Product</h1>
    <form action="/updateProduct" method="post">
        <input type="hidden" id="id" name="id" value="${product.productid}">
        
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${product.productname}"><br>
        
        <label for="category">Category:</label>
        <input type="text" id="category" name="category" value="${product.productcategory}"><br>
        
        <label for="description">Description:</label>
        <input type="text" id="description" name="description" value="${product.productdescription}"><br>
        
        <label for="price">Price:</label>
        <input type="text" id="price" name="price" value="${product.productprice}"><br>
        
        <label for="stock">Stock:</label>
        <input type="text" id="stock" name="stock" value="${product.productstock}"><br>
        
        <input type="submit" value="Update">
    </form>
</body>
</html>
