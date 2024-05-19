package uts.isd.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.Cart;
import uts.isd.model.CartItem;
import uts.isd.model.Product;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.ProductDAO;

// Declares the servlet and mapping
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/addToCart"})
public class AddToCartServlet extends HttpServlet {
    private DBConnector dbConnector;
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        // Initializes the database connection and DAO
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

            // Retrieve or initialize the set of selected product IDs
            Set<Integer> selectedProductIds = (Set<Integer>) session.getAttribute("selectedProductIds");
            if (selectedProductIds == null) {
                selectedProductIds = new HashSet<>();
                session.setAttribute("selectedProductIds", selectedProductIds);
                System.out.println("New product ID set created.");
            }

            // Retrieve or create a cart object in session
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
                System.out.println("New cart created.");
            } else {
                System.out.println("Cart found with items: " + cart.getItems().size());
            }

            boolean productAdded = false;
            for (String key : request.getParameterMap().keySet()) {
                if (key.startsWith("selectedProduct_")) {
                    int productId = Integer.parseInt(request.getParameter(key));
                    // Check if product has already been added to the cart
                    if (!selectedProductIds.contains(productId)) {
                        Product product = productDAO.getProductById(productId);
                        if (product != null) {
                            CartItem newItem = new CartItem(product, 1); // Assume quantity is 1, can be adjusted
                            cart.addItem(newItem);
                            selectedProductIds.add(productId); // Add to set of selected IDs
                            System.out.println("Added product to cart: " + product.getProductname());
                            productAdded = true;
                        }
                    } else {
                        System.out.println("Product ID " + productId + " is already in the cart.");
                    }
                }
            }

            if (productAdded) {
                System.out.println("Redirecting to view cart.");
                response.sendRedirect("viewCart");
            } else {
                System.out.println("No new products added. Redirecting to product list.");
                response.sendRedirect("/viewCart"); // Corrected the redirect link for consistency
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred.");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        // Clean up any resources such as database connections here
        if (dbConnector != null) {
            try {
                dbConnector.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
