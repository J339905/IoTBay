package uts.isd.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Set;

import uts.isd.model.Cart;
import uts.isd.model.CartItem;

// Servlet mapping defined to handle requests to "/updateCart"
@WebServlet(name = "UpdateCartServlet", urlPatterns = {"/updateCart"})
public class UpdateCartServlet extends HttpServlet {
    
    // Handles POST requests to update items in the cart
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();  // Obtain the session object to manage session data
        Cart cart = (Cart) session.getAttribute("cart");  // Retrieve the cart object from the session

        // Checks if the request is to remove a product from the cart
        if (request.getParameter("removeProductId") != null) {
            int productIdToRemove = Integer.parseInt(request.getParameter("removeProductId"));  // Parse the product ID to remove
            cart.removeItem(productIdToRemove);  // Remove the product from the cart

            // Retrieve the set of selected product IDs from the session
            Set<Integer> selectedProductIds = (Set<Integer>) session.getAttribute("selectedProductIds");
            if (selectedProductIds != null) {
                selectedProductIds.remove(productIdToRemove);  // Remove the product ID from the set if it exists
            }
        } else if ("true".equals(request.getParameter("cancel"))) {
            // If the cancel parameter is present, clear the cart
            if (cart != null) {
                cart.clearItems();
            }
            session.removeAttribute("selectedProductIds");  // Remove the set of selected product IDs from the session
        } else {
            // Update quantities for each cart item based on user input
            for (CartItem item : cart.getItems()) {
                String itemKey = "quantity_" + item.getProduct().getProductid();  // Form the key for item quantity parameter
                int newQuantity = Integer.parseInt(request.getParameter(itemKey));  // Parse the new quantity from the request
                if (newQuantity > 0) {
                    item.setQuantity(newQuantity);  // Set the new quantity for the cart item
                } else {
                    cart.removeItem(item.getProduct().getProductid());  // Remove the item if quantity is set to 0 or negative
                }
            }
        }
        response.sendRedirect("viewCart");  // Redirect to the cart view page
    }

    // Handles GET requests, which might also manage cart updates or redirect to cart view
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // If the cancel operation is initiated via a GET request, call doPost to handle it
        if ("true".equals(request.getParameter("cancel"))) {
            doPost(request, response);
        } else {
            // Otherwise, redirect to the cart view page
            response.sendRedirect("viewCart");
        }
    }
}
