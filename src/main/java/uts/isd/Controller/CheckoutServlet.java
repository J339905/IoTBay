package uts.isd.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import uts.isd.model.Cart;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.PaymentDAO;
import uts.isd.model.Payment;

@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {
    private DBConnector db;
    private PaymentDAO paymentDAO;

    @Override
    public void init() throws ServletException {
        try {
            db = new DBConnector();
            paymentDAO = new PaymentDAO(db.openConnection());
        } catch (Exception e) {
            throw new ServletException("Database connection failed", e);
        }
    }

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
            request.setAttribute("currentDate", new Date());

            request.getRequestDispatcher("/checkout.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart != null) {
            String address = request.getParameter("address");
            String paymentMethod = request.getParameter("paymentMethod");
            String cardName = request.getParameter("cardName");
            String cardNumber = request.getParameter("cardNumber");
            String expiryDate = request.getParameter("expiryDate");
            String cvv = request.getParameter("cvv");
            double amount = Double.parseDouble(request.getParameter("amount"));
            Date paymentDate = new Date();  // Assuming paymentDate is the current date

            boolean paymentSuccess = processPayment(paymentMethod, cardNumber, expiryDate, cvv, amount);

            if (paymentSuccess) {
                saveOrderPaymentDetails(session.getId(), address, paymentMethod, cardName, cardNumber, expiryDate, cvv, amount, paymentDate);

                session.removeAttribute("cart");
                session.removeAttribute("selectedProductIds");

                response.sendRedirect("savedPaymentDetails.jsp");
            } else {
                request.setAttribute("error", "Payment failed. Please try again.");
                request.getRequestDispatcher("/checkout.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("/errorPage.jsp");
        }
    }

    private boolean processPayment(String paymentMethod, String cardNumber, String expiryDate, String cvv, double amount) {
        // Implement your payment processing logic here
        // Return true if payment is successful, false otherwise
        return true;
    }

    private void saveOrderPaymentDetails(String sessionId, String address, String paymentMethod, String cardName, String cardNumber, String expiryDate, String cvv, double amount, Date paymentDate) {
        try {
            Payment payment = new Payment(sessionId, address, paymentMethod, cardName, cardNumber, expiryDate, cvv, amount, paymentDate);
            paymentDAO.addPayment(payment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        try {
            if (db != null) {
                db.closeConnection();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
