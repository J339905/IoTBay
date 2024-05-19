package uts.isd.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import uts.isd.model.Cart;

// Defines the servlet name and the URL pattern it responds to
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the session and the cart object from the session
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        
        // Check if the cart is null or empty, and redirect accordingly
        if (cart == null || cart.getItems().isEmpty()) {
            // Set an error attribute if the cart is empty
            request.setAttribute("error", "Your cart is empty!");
            // Forward the request back to the cart page
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
        } else {
            // Calculate total price and quantity from the cart
            double total = cart.getTotalPrice();
            int quantity = cart.getTotalQuantity();

            // Set attributes for total price and quantity to be displayed at checkout
            request.setAttribute("totalPrice", total);
            request.setAttribute("totalQuantity", quantity);

            // Forward the request to the checkout page
            request.getRequestDispatcher("/checkout.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieves the session and the cart object
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart != null) {
            // Placeholder for payment processing logic
            // On successful payment:
            session.removeAttribute("cart"); // Clear the cart after successful checkout
            session.removeAttribute("selectedProductIds"); // Optionally clear selected product IDs

            // Redirect to a confirmation page to indicate successful transaction
            response.sendRedirect("/orderConfirmation.jsp");
        } else {
            // Handle error or redirect to an error page if the cart is unexpectedly empty
            response.sendRedirect("/errorPage.jsp");
        }
    }
}
