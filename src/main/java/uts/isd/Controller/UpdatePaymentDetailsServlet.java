package uts.isd.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uts.isd.model.Payment;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.PaymentDAO;

@WebServlet("/updatePayment")
public class UpdatePaymentDetailsServlet extends HttpServlet {
    private DBConnector db;
    private PaymentDAO paymentDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector();
            Connection conn = db.openConnection();
            paymentDAO = new PaymentDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int paymentId = Integer.parseInt(request.getParameter("paymentId"));
            double amount = Double.parseDouble(request.getParameter("amount"));
            String paymentMethod = request.getParameter("paymentMethod");
            String paymentDate = request.getParameter("paymentDate");

            Payment payment = new Payment(paymentId, 0, amount, paymentMethod, paymentDate); // Assuming 0 for orderId, change accordingly

            paymentDAO.updatePayment(payment);
            response.sendRedirect("/paymentHistory"); // Redirect to payment history page
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid payment ID or amount.");
        } catch (SQLException e) {
            throw new ServletException("Error updating payment details", e);
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
