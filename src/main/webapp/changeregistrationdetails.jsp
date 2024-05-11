<%-- 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="uts.isd.model.User"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Registration Details</title>
</head>
<body>
    <h1>Update Your Registration Details</h1>
    <form action="ChangeRegistrationDetails" method="post">
    
        <input type="hidden" name="email" value="${user.email}" disabled>
        <label for="firstname">First Name:</label>
        <input type="text" id="firstname" name="firstname" value="${user.firstName}" required><br>
         <label for="lastname">Last Name:</label>  
        <input type="text" id="lastname" name="lastname" value="${user.lastname}" required><br>
        <label for="phone">Phone Number:</label>
        <input type="number" id="phone" name="phone" value="${user.phone}" required><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="${user.password}" required><br>
        <label for="gender">Gender:</label>
        <input type="text" id="gender" name="gender" value="${user.gender}" required><br>
        <%-- <label for="role">Role:</label>
        <input type="text" id="role" name="role" value="${user.role}" required><br>  --%>
        <%-- <button type="submit">Update Details</button>

    </form>
    <a href="profile.jsp">Back to Profile</a>
</body>
</html>  --%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="uts.isd.model.User"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Registration Details</title>
</head>
<body>
    <h1>Update Your Registration Details</h1>
    <form class="form-register" method="POST" action="/ChangeRegistrationDetailsServlet">
        <input type="hidden" name="email" value="${user.email}" disabled>
        <label for="firstname">First Name:</label>
        <input type="text" id="firstname" name="firstname" value="${user.getfirstName()}" required><br>
         <label for="lastname">Last Name:</label>  
        <input type="text" id="lastname" name="lastname" value="${user.getlastname()}" required><br>
        <label for="phone">Phone Number:</label>
        <input type="number" id="phone" name="phone" value="${user.getPhone()}" required><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="${user.getPassword()}" required><br>
        <%-- <label for="gender">Gender:</label> --%>
        <select id="gender" name="gender" required>
            <option value="Male" ${user.gender == 'Male' ? 'selected' : ''}>Male</option>
            <option value="Female" ${user.gender == 'Female' ? 'selected' : ''}>Female</option>
            <option value="Other" ${user.gender == 'Other' ? 'selected' : ''}>Other</option>
        </select><br>
        <%-- <input type="text" id="gender" name="gender" value="${user.getGender()}" required><br> --%>
        <%-- <label for="role">Role:</label>
        <input type="text" id="role" name="role" value="${user.role}" required><br>  --%>
        <button type="submit">Update Details</button>

    </form>
    <a href="profile.jsp">Back to Profile</a>
</body>
</html>
