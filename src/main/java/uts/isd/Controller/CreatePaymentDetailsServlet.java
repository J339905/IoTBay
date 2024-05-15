package main.java.uts.isd.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.Payment;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.PaymentDAO;
import uts.isd.model.dao.LogDAO;

public class CreatePaymentDetailsServlet extends HttpServlet {

    private DBConnector db;
    private PaymentDAO paymentDAO;
    private LogDAO logDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector();
            Connection conn = db.openConnection();
            paymentDAO = new PaymentDAO(conn);
            logDAO = new LogDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Retrieve parameters from the request
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        String paymentMethod = request.getParameter("paymentMethod");
        String paymentDate = request.getParameter("paymentDate");

        // Create a Payment object
        Payment payment = new Payment(orderId, amount, paymentMethod, paymentDate);

        try {
            // Add the payment to the database
            paymentDAO.addPayment(payment);

            // Log the activity
            // Replace userId with the actual user ID retrieved from the session
            int userId = 123; // Example user ID
            logDAO.createLog(userId, java.time.LocalDateTime.now().toString(), "Payment created");

            // Redirect to payment confirmation page
            response.sendRedirect("paymentConfirmation.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database error
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error during payment creation.");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            if (db != null) {
                db.closeConnection(); // Properly close your DB connection
            }
        } catch (SQLException e) {
            System.err.println("Failed to close database connection.");
        }
    }
}
