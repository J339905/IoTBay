<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Product List</title>
    <link rel="stylesheet" href="css/productlist.css">
</head>
<body>
    <div class="container">
        <h1>Product List</h1>
        <c:if test="${not empty productList}">
            <table>
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
                            <td><c:out value="${product.productid}"/></td>
                            <td><c:out value="${product.productname}"/></td>
                            <td><c:out value="${product.productcategory}"/></td>
                            <td><c:out value="${product.productdescription}"/></td>
                            <td><c:out value="${product.productprice}"/></td>
                            <td><c:out value="${product.productstock}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty productList}">
            <p>No products found.</p>
        </c:if>
    </div>
</body>
</html>
