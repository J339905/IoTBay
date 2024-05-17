<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <link rel="stylesheet" href="/css/productlist.css">
</head>
<body>
    <header>
        <h1>Product List</h1>
        <form action="listProducts" method="get" class="search-form">
            <input type="text" name="name" placeholder="Search by name..." value="${param.name}">
            <select name="category">
                <option value="">All Categories</option>
                <option value="Actuator" ${"Actuator".equals(param.category) ? "selected" : ""}>Actuator</option>
                <option value="Gateway" ${"Gateway".equals(param.category) ? "selected" : ""}>Gateway</option>
                <option value="Sensor" ${"Sensor".equals(param.category) ? "selected" : ""}>Sensor</option>
                <option value="Other" ${"Other".equals(param.category) ? "selected" : ""}>Other</option>
            </select>
            <button type="submit">Search</button>
        </form>
    </header>

    <main>
        <form action="addToCart" method="post">
            <table>
                <thead>
                    <tr>
                        <th>Product ID</th>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Stock</th>
                        <th>Select</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty products}">
                            <tr>
                                <td colspan="7" style="text-align:center;">Product not found</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="product" items="${products}">
                                <tr>
                                    <td>${product.productid}</td>
                                    <td>${product.productname}</td>
                                    <td>${product.productcategory}</td>
                                    <td>${product.productdescription}</td>
                                    <td>${product.productprice}</td>
                                    <td>${product.productstock}</td>
                                    <td class="select-column">
                                        <input type="checkbox" name="selectedProduct_${product.productid}"
                                               value="${product.productid}" ${product.productstock == 0 ? 'disabled' : ''} ${sessionScope.selectedProductIds.contains(product.productid) ? 'disabled' : ''}>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
            <div class="button-container">
                <input type="submit" class="btn order-button" value="Order Selected Products">
                <a href="dashboard.jsp" class="btn dashboard-button">Return to Dashboard</a>
            </div>
        </form>
    </main>
</body>
</html>
