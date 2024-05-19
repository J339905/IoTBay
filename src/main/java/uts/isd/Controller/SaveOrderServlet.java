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
import uts.isd.model.Cart;
import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.OrderDAO;

// Define servlet mapping via WebServlet annotation
@WebServlet("/saveOrders")
public class SaveOrderServlet extends HttpServlet {
    private DBConnector db;
    private OrderDAO orderDAO;

    // Initialize servlet and database connection
    @Override
    public void init() throws ServletException {
        try {
            db = new DBConnector(); // Create new DBConnector instance
            Connection conn = db.openConnection(); // Open database connection
            orderDAO = new OrderDAO(conn); // Create OrderDAO with database connection
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed", e); // Handle initialization failures
        }
    }

    // Handle POST requests for saving orders
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); // Retrieve current HTTP session
        User user = (User) session.getAttribute("user"); // Get the user object from session
        Cart cart = (Cart) session.getAttribute("cart"); // Get the cart object from session

        // Log user and cart status
        System.out.println("User: " + (user != null ? "Logged In" : "Not Logged In"));
        System.out.println("Cart: " + (cart != null && !cart.getItems().isEmpty() ? "Has Items" : "Empty or Null"));

        // Check if user is logged in and cart is not empty
        if (user == null || cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect("cart.jsp"); // Redirect to cart page if user or cart conditions fail
            return;
        }

        try {
            orderDAO.saveCart(user.getUserID(), cart); // Save cart details in the database
            session.setAttribute("cart", new Cart()); // Reset the session cart
            response.sendRedirect("savedOrders.jsp"); // Redirect to the saved orders page
        } catch (SQLException e) {
            throw new ServletException("Failed to save order", e); // Handle SQL exceptions
        }
    }

    // Handle GET requests; usually used for redirection or simple data retrieval
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect user to the view saved orders page or similar page
        response.sendRedirect("/viewSavedOrders"); // Update the redirect link as needed
    }

    // Clean up resources, such as database connections
    @Override
    public void destroy() {
        try {
            if (db != null) {
                db.closeConnection(); // Ensure the database connection is closed on servlet destruction
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
