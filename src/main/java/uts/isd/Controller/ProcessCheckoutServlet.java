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

@WebServlet("/processCheckout")
public class ProcessCheckoutServlet extends HttpServlet {
    private DBConnector db;
    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        try {
            db = new DBConnector();
            Connection conn = db.openConnection();
            orderDAO = new OrderDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
    
        String paymentMethod = request.getParameter("paymentMethod");
        String deliveryAddress = request.getParameter("deliveryAddress");
    
        if (cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect("cart.jsp"); // Redirect to cart page if cart is empty
            return;
        }
    
        try {
            // Insert order into the database
            Integer userId = user != null ? user.getUserID() : null; // Use null if user is not logged in
            orderDAO.insertOrder(userId, cart, deliveryAddress, paymentMethod);
            
            // Clear the cart after order is placed
            cart.clearItems();
            session.setAttribute("cart", cart);
    
            // Redirect to a confirmation page
            response.sendRedirect("orderConfirmation.jsp");
        } catch (SQLException e) {
            throw new ServletException("Order processing failed", e);
        }
    }
    

    @Override
    public void destroy() {
        try {
            if (db != null) {
                db.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
