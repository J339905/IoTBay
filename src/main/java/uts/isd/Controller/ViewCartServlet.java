package uts.isd.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

import uts.isd.model.Cart;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.ProductDAO;

@WebServlet(name = "ViewCartServlet", urlPatterns = {"/viewCart"})
public class ViewCartServlet extends HttpServlet {
    private DBConnector db;
    private ProductDAO productDAO;

    // Initializes the servlet and its dependencies, namely the database connector and product DAO
    @Override
    public void init() throws ServletException {
        try {
            db = new DBConnector();
            productDAO = new ProductDAO(db.openConnection()); // Create a new ProductDAO with a connection from DBConnector
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("DBConnector initialization failed.", e); // Handle exceptions by throwing ServletException
        }
    }

    // Handles GET requests to display the cart
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); // Retrieve the current session
        System.out.println("Session ID in ViewCartServlet: " + session.getId()); // Log the session ID for debugging

        Cart cart = (Cart) session.getAttribute("cart"); // Retrieve the cart object from the session
        if (cart != null) {
            System.out.println("Cart items to display: " + cart.getItems().size()); // Log the number of items in the cart if it exists
        } else {
            System.out.println("No cart found in session."); // Log if no cart is found in the session
        }

        request.setAttribute("cart", cart); // Set the cart as a request attribute to be used in the JSP
        request.getRequestDispatcher("cart.jsp").forward(request, response); // Forward the request to the cart.jsp page
    }

    // Cleans up resources, such as database connections, when the servlet is destroyed
    @Override
    public void destroy() {
        try {
            if (db != null) {
                db.closeConnection(); // Close the database connection if it exists
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log and handle any SQL exceptions on closing the connection
        }
    }
}
