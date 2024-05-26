package uts.isd.Controller;

import uts.isd.model.Payment;
import uts.isd.model.dao.PaymentDAO;
import uts.isd.model.dao.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ViewSavedPaymentsServlet", urlPatterns = {"/viewSavedPayments"})
public class ViewSavedPaymentsServlet extends HttpServlet {

    private PaymentDAO paymentDAO;

    public void init() {
        try {
            DBConnector dbConnector = new DBConnector();
            paymentDAO = new PaymentDAO(dbConnector.openConnection());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Payment> payments = paymentDAO.getAllPayments();
            request.setAttribute("payments", payments);
            request.getRequestDispatcher("savedPaymentDetails.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving payment details.", e);
        }
    }
}
