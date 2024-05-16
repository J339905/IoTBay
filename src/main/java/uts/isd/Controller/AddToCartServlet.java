package uts.isd.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;  // Add this import for HttpSession

import uts.isd.model.Cart;
import uts.isd.model.CartItem;
import uts.isd.model.Product;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.ProductDAO;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
    private DBConnector dbConnector;
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        try {
            dbConnector = new DBConnector(); // Ensure DBConnector can throw ClassNotFoundException
            Connection conn = dbConnector.openConnection();
            productDAO = new ProductDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            System.out.println("Session ID in AddToCartServlet: " + session.getId());
    
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
                System.out.println("New cart created.");
            } else {
                System.out.println("Cart found with items: " + cart.getItems().size());
            }
    
            for (String key : request.getParameterMap().keySet()) {
                if (key.startsWith("selectedProduct_")) {
                    int productId = Integer.parseInt(request.getParameter(key));
                    Product product = productDAO.getProductById(productId);
                    if (product != null) {
                        CartItem newItem = new CartItem(product, 1);
                        cart.addItem(newItem);
                        System.out.println("Added product to cart: " + product.getProductname());
                    }
                }
            }
    
            response.sendRedirect("viewCart");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred.");
        }
    }
    

    @Override
    public void destroy() {
        try {
            dbConnector.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
