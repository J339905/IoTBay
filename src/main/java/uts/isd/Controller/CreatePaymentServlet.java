package uts.isd.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uts.isd.model.Payment;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.PaymentDAO;

@WebServlet(name = "CreatePaymentServlet", urlPatterns = {"/createPayment"})
public class CreatePaymentServlet extends HttpServlet {

    private DBConnector db;
    private PaymentDAO paymentDAO;

    @Override
    public void init() throws ServletException {
        try {
            db = new DBConnector();
            Connection conn = db.openConnection();
            paymentDAO = new PaymentDAO(conn);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new ServletException("DB Connection error.", ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cardHolderName = request.getParameter("cardHolderName");
        String creditCardNumber = request.getParameter("creditCardNumber");
        int cvv = Integer.parseInt(request.getParameter("cvv")); // Ensure cvv is parsed as int
        String expiryDate = request.getParameter("expiryDate");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String date = request.getParameter("date");

        Payment payment = new Payment(cardHolderName, creditCardNumber, cvv, expiryDate, amount, date);

        try {
            paymentDAO.createPayment(payment);
            response.sendRedirect("savedPaymentDetails.jsp");
        } catch (SQLException ex) {
            throw new ServletException("Error creating payment record.", ex);
        }
    }

    @Override
    public void destroy() {
        try {
            if (db != null) {
                db.closeConnection();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreatePaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
