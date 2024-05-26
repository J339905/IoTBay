package uts.isd.Controller;

import uts.isd.model.Payment;
import uts.isd.model.dao.PaymentDAO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ViewSavedPaymentsServlet extends HttpServlet {
    private PaymentDAO paymentDAO;

    public void init() {
        paymentDAO = new PaymentDAO((Connection) getServletContext().getAttribute("dbConnection"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Payment> paymentList = paymentDAO.getAllPayments();
            request.setAttribute("paymentList", paymentList);
            request.getRequestDispatcher("viewSavedPayments.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving payment details.", e);
        }
    }
}
