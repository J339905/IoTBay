package uts.isd.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;
import uts.isd.model.Order;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.OrderDAO;

// Servlet annotation to define the URL pattern this servlet will respond to
@WebServlet("/listOrders")
public class ListOrderServlet extends HttpServlet {
    private DBConnector db;
    private OrderDAO orderDAO;

    // Initializes the servlet/database connection
    @Override
    public void init() throws ServletException {
        try {
            db = new DBConnector(); 
            Connection conn = db.openConnection(); 
            orderDAO = new OrderDAO(conn); 
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    // Handles the GET request to list orders
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId"); // Retrieve the user ID from session
        if (userId == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if user ID is not found (user not logged in)
            return;
        }
        String currentSortBy = request.getParameter("sortBy") != null ? request.getParameter("sortBy") : "OrderID"; // Determine sort field
        String currentSortOrder = request.getParameter("sortOrder") != null ? request.getParameter("sortOrder") : "asc"; // Determine sort order

        try {
            List<Order> orders = orderDAO.listOrdersByUserId(userId, currentSortBy, currentSortOrder); // Fetch orders based on user ID, sort field, and order
            request.setAttribute("orderList", orders); // Set the orders list as a request attribute for the JSP to display
            request.getRequestDispatcher("orderlist.jsp").forward(request, response); // Forward to the order listing JSP
        } catch (SQLException e) {
            throw new ServletException("Error retrieving orders for user " + userId, e); // Handle SQL exceptions
        }
    }

    // Clean up resources before servlet is destroyed
    @Override
    public void destroy() {
        try {
            if (db != null) {
                db.closeConnection(); // Close the database connection
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
