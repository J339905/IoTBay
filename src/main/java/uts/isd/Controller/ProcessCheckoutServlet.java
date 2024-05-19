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

// WebServlet annotation defines this servlet's URL access pattern
@WebServlet("/processCheckout")
public class ProcessCheckoutServlet extends HttpServlet {
    private DBConnector db;
    private OrderDAO orderDAO;

    // Servlet initialization method
    @Override
    public void init() throws ServletException {
        try {
            db = new DBConnector();  // Create a new instance of the database connector
            Connection conn = db.openConnection();  // Open a connection to the database
            orderDAO = new OrderDAO(conn);  // Initialize the OrderDAO with the open connection
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed", e);  // Handle potential exceptions
        }
    }

    // Handles POST requests for processing the checkout
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();  // Retrieve the current session
        Cart cart = (Cart) session.getAttribute("cart");  // Retrieve the cart object from the session
        User user = (User) session.getAttribute("user");  // Retrieve the user object from the session

        String paymentMethod = request.getParameter("paymentMethod");  // Get the payment method from the request
        String deliveryAddress = request.getParameter("deliveryAddress");  // Get the delivery address from the request

        if (cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect("cart.jsp"); // Redirect to cart page if the cart is empty
            return;
        }

        try {
            // Process the order by inserting it into the database
            Integer userId = user != null ? user.getUserID() : null; // Use the user ID if logged in, otherwise null
            orderDAO.insertOrder(userId, cart, deliveryAddress, paymentMethod);  // Insert the order into the database
            
            // Clear the cart after the order is successfully placed
            cart.clearItems();  // Clear all items in the cart
            session.setAttribute("cart", cart);  // Update the cart attribute in the session

            // Clear selected product IDs from session to reset the state of the product list page
            session.removeAttribute("selectedProductIds");
    
            // Redirect to the order confirmation page
            response.sendRedirect("orderConfirmation.jsp");
        } catch (SQLException e) {
            throw new ServletException("Order processing failed", e);  // Handle SQL exceptions
        }
    }
    

    // Clean up resources such as the database connection when the servlet is destroyed
    @Override
    public void destroy() {
        try {
            if (db != null) {
                db.closeConnection();  // Close the database connection if it was opened
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
