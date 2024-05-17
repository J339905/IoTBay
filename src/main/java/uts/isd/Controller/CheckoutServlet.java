package uts.isd.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import uts.isd.model.Cart;

@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        
        if (cart == null || cart.getItems().isEmpty()) {
            request.setAttribute("error", "Your cart is empty!");
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
        } else {
            double total = cart.getTotalPrice();
            int quantity = cart.getTotalQuantity();

            request.setAttribute("totalPrice", total);
            request.setAttribute("totalQuantity", quantity);

            request.getRequestDispatcher("/checkout.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Here you would handle the payment processing
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart != null) {
            // Process payment here
            // On success:
            session.removeAttribute("cart"); // Clear the cart after successful checkout
            session.removeAttribute("selectedProductIds"); // Optionally clear selected product IDs

            // Redirect to a confirmation page or similar
            response.sendRedirect("/orderConfirmation.jsp");
        } else {
            // Handle the error or redirect
            response.sendRedirect("/errorPage.jsp");
        }
    }
}
