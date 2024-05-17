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


@WebServlet("/saveOrders")
public class SaveOrderServlet extends HttpServlet {
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
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");
    
        // Logging to check user and cart status
        System.out.println("User: " + (user != null ? "Logged In" : "Not Logged In"));
        System.out.println("Cart: " + (cart != null && !cart.getItems().isEmpty() ? "Has Items" : "Empty or Null"));
    
        if (user == null || cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }
    
        try {
            orderDAO.saveCart(user.getUserID(), cart);
            session.setAttribute("cart", new Cart());
            response.sendRedirect("savedOrders.jsp");
        } catch (SQLException e) {
            throw new ServletException("Failed to save order", e);
        }
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect user to the cart page or any other appropriate page
        
        response.sendRedirect("/viewSavedOrders"); // Change "cart.jsp" to the appropriate redirect target
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
