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
 <%
        String nullErr = (String) session.getAttribute("nullErr");
        String phoneErr = (String) session.getAttribute("phoneErr");
        String samedetailsErr = (String) session.getAttribute("samedetailsErr");
         
        String nametypeErr = (String) session.getAttribute("nametypeErr");
        String passwordErr = (String) session.getAttribute("passwordErr");
        String userexistsErr = (String) session.getAttribute("userexistsErr");
        
    %>
    <h1>Update Your Registration Details</h1>
    
    <form class="form-register" method="POST" action="/ChangeRegistrationDetailsServlet" novalidate>
        <input type="hidden" name="email" value="${user.email}" disabled>
        <% if (nullErr != null) { %>
        <div class="alert alert-danger"><%= nullErr %></div>
    <% } %>
        <label for="firstname">First Name:</label>
        <input type="text" id="firstname" name="firstname" value="${user.getfirstName()}" required><br>
         <label for="lastname">Last Name:</label>  
        <input type="text" id="lastname" name="lastname" value="${user.getlastname()}" required><br>
        <% if (nametypeErr != null) { %>
        <div class="alert alert-danger"><%= nametypeErr %></div>
    <% } %>
        <label for="phone">Phone Number:</label>
        <input type="text" id="phone" name="phone" value="${user.getPhone()}" required><br>
        <% if(phoneErr != null) { %>
        <div class="alert alert-danger"><%= phoneErr %></div>
    <% } %>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="${user.getPassword()}" required><br>
        <%-- <label for="gender">Gender:</label> --%>
        <% if (passwordErr != null) { %>
        <div class="alert alert-danger"><%= passwordErr %></div>
    <% } %>
        <select id="gender" name="gender" required>
            <option value="Male" ${user.gender == 'Male' ? 'selected' : ''}>Male</option>
            <option value="Female" ${user.gender == 'Female' ? 'selected' : ''}>Female</option>
            <option value="Other" ${user.gender == 'Other' ? 'selected' : ''}>Other</option>
        </select><br>
                <% if(samedetailsErr != null) { %>
        <div class="alert alert-danger"><%= samedetailsErr %></div>
    <% } %>
        <%-- <input type="text" id="gender" name="gender" value="${user.getGender()}" required><br> --%>
        <%-- <label for="role">Role:</label>
        <input type="text" id="role" name="role" value="${user.role}" required><br>  --%>
        <button type="submit">Update Details</button>

    </form>
    <a href="welcome.jsp">Back to Profile</a>
</body>
</html>
