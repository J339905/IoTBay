package uts.isd.Controller;

import uts.isd.model.Payment;
import uts.isd.model.dao.PaymentDAO;
import uts.isd.model.dao.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

@WebServlet("/saveOrderPaymentDetails")
public class SaveOrderPaymentDetailsServlet extends HttpServlet {
    private DBConnector db;
    private PaymentDAO paymentDAO;

    @Override
    public void init() throws ServletException {
        try {
            db = new DBConnector();
            Connection conn = db.openConnection();
            paymentDAO = new PaymentDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String address = request.getParameter("deliveryAddress");
        String paymentMethod = request.getParameter("paymentMethod");
        String cardNumber = request.getParameter("cardNumber");
        String expiryDate = request.getParameter("expiryDate");
        String cvv = request.getParameter("cvv");
        double amount = Double.parseDouble(request.getParameter("amount"));
        Date paymentDate = java.sql.Date.valueOf(request.getParameter("paymentDate"));

        try {
            Payment payment = new Payment();
            payment.setAddress(address);
            payment.setPaymentMethod(paymentMethod);
            payment.setCardNumber(cardNumber);
            payment.setExpiryDate(expiryDate);
            payment.setCvv(cvv);
            payment.setAmount(amount);
            payment.setPaymentDate(paymentDate);

            paymentDAO.createPayment(payment);

            response.sendRedirect("savedPaymentDetails.jsp");
        } catch (SQLException e) {
            throw new ServletException("Error saving payment details", e);
        }
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
