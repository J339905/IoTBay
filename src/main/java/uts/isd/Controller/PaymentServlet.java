package main.java.uts.isd.Controller;

import uts.isd.model.Payment;
import uts.isd.model.dao.PaymentDAO;
import uts.isd.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PaymentServlet extends HttpServlet {
    private PaymentDAO paymentDAO;

    public void init() {
        paymentDAO = new PaymentDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paymentMethod = request.getParameter("paymentMethod");
        int amount = Integer.parseInt(request.getParameter("amount"));
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Payment payment = new Payment(0, orderId, amount, new Timestamp(System.currentTimeMillis()), paymentMethod);

        try {
            paymentDAO.addPayment(payment);
        } catch (SQLException e) {
            throw new ServletException("SQL error while adding payment", e);
        }

        response.sendRedirect("paymentHistory.jsp");
    }
}
