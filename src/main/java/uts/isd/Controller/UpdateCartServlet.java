package uts.isd.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import uts.isd.model.Cart;
import uts.isd.model.CartItem;


@WebServlet(name = "UpdateCartServlet", urlPatterns = {"/updateCart"})
public class UpdateCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            for (CartItem item : cart.getItems()) {
                String itemKey = "quantity_" + item.getProduct().getProductid();
                int newQuantity = Integer.parseInt(request.getParameter(itemKey));
                if (newQuantity > 0) {
                    item.setQuantity(newQuantity);
                } else {
                    cart.removeItem(item);
                }
            }
        }
        response.sendRedirect("viewCart"); // Redirect to the cart view to see the updates
    }
}
