package uts.isd.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uts.isd.model.Payment;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.PaymentDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "SearchPaymentHistoryServlet", urlPatterns = {"/searchPaymentHistory"})
public class SearchPaymentHistoryServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paymentIdStr = request.getParameter("paymentId");
        String date = request.getParameter("date");

        try {
            List<Payment> payments;

            if (paymentIdStr != null && !paymentIdStr.isEmpty()) {
                int paymentId = Integer.parseInt(paymentIdStr);
                payments = paymentDAO.searchPaymentsById(paymentId);
            } else if (date != null && !date.isEmpty()) {
                payments = paymentDAO.searchPaymentsByDate(date);
            } else {
                payments = paymentDAO.getAllPayments();
            }

            request.setAttribute("payments", payments);
            request.getRequestDispatcher("viewPaymentHistory.jsp").forward(request, response);
        } catch (SQLException | NumberFormatException ex) {
            throw new ServletException("Error accessing payment history.", ex);
        }
    }

    @Override
    public void destroy() {
        try {
            if (db != null) {
                db.closeConnection();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SearchPaymentHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

