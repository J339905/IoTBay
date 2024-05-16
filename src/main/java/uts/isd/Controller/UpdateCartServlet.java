package uts.isd.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Set;

import uts.isd.model.Cart;
import uts.isd.model.CartItem;

@WebServlet(name = "UpdateCartServlet", urlPatterns = {"/updateCart"})
public class UpdateCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (request.getParameter("removeProductId") != null) {
            int productIdToRemove = Integer.parseInt(request.getParameter("removeProductId"));
            cart.removeItem(productIdToRemove);

            Set<Integer> selectedProductIds = (Set<Integer>) session.getAttribute("selectedProductIds");
            if (selectedProductIds != null) {
                selectedProductIds.remove(productIdToRemove);
            }
        } else if ("true".equals(request.getParameter("cancel"))) {
            if (cart != null) {
                cart.clearItems();
            }
            session.removeAttribute("selectedProductIds");
        } else {
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
        response.sendRedirect("viewCart");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle requests via GET if necessary, for example for the cancel operation
        if ("true".equals(request.getParameter("cancel"))) {
            doPost(request, response);
        } else {
            response.sendRedirect("viewCart");
        }
    }
}
