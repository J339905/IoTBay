<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session.invalidate(); // Invalidate session
    response.sendRedirect("index.jsp"); // Redirect to login page after logout
%>