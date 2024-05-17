package uts.isd.Controller;

import uts.isd.model.Payment;
import uts.isd.model.dao.PaymentDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class PaymentServlet extends HttpServlet {

    private PaymentDAO paymentDAO;

    @Override
    public void init() throws ServletException {
        try {
            paymentDAO = new PaymentDAO();
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String paymentMethod = request.getParameter("paymentMethod");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String paymentStatus = "Completed";
        Date paymentDate = new Date();

        Payment payment = new Payment();
        payment.setUserId(user.getUserID());
        payment.setPaymentMethod(paymentMethod);
        payment.setAmount(amount);
        payment.setPaymentStatus(paymentStatus);
        payment.setPaymentDate(paymentDate);

        try {
            paymentDAO.createPayment(payment);
            response.sendRedirect("orderConfirmation.jsp");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            ArrayList<Payment> payments = paymentDAO.getPaymentsByUserId(user.getUserID());
            request.setAttribute("payments", payments);
            request.getRequestDispatcher("paymentHistory.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

