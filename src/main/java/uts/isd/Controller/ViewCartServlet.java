package uts.isd.Controller;

import javax.servlet.*;
import javax.servlet.http.*;

import uts.isd.model.Cart;
import uts.isd.model.CartItem;


import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uts.isd.model.Product;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.ProductDAO;

@WebServlet(name = "ViewCartServlet", urlPatterns = {"/viewCart"})
public class ViewCartServlet extends HttpServlet {
    private DBConnector db;
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        try {
            db = new DBConnector();
            productDAO = new ProductDAO(db.openConnection());
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    System.out.println("Session ID in ViewCartServlet: " + session.getId());

    Cart cart = (Cart) session.getAttribute("cart");
    if (cart != null) {
        System.out.println("Cart items to display: " + cart.getItems().size());
    } else {
        System.out.println("No cart found in session.");
    }

    request.setAttribute("cart", cart);
    request.getRequestDispatcher("cart.jsp").forward(request, response);
}



    @Override
    public void destroy() {
        try {
            if (db != null) {
                db.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log and handle the error properly
        }
    }
}
