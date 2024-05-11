<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Product List</title>
</head>
<body>
    <h1>Product List</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Category</th>
                <th>Description</th>
                <th>Price</th>
                <th>Stock</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${productList}" var="product">
                <tr>
                    <td><c:out value="${product.productId}"/></td>
                    <td><c:out value="${product.productName}"/></td>
                    <td><c:out value="${product.productCategory}"/></td>
                    <td><c:out value="${product.productDescription}"/></td>
                    <td><c:out value="${product.productPrice}"/></td>
                    <td><c:out value="${product.productStock}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
