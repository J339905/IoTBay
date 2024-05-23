package uts.isd.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import uts.isd.model.Payment;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.PaymentDAO;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
    private DBConnector db;
    private PaymentDAO paymentDAO;

    @Override
    public void init() throws ServletException {
        try {
            db = new DBConnector();
            Connection conn = db.openConnection();
            paymentDAO = new PaymentDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        try {
            List<Payment> payments = paymentDAO.listPaymentsByUserId(userId);
            request.setAttribute("paymentList", payments);
            request.getRequestDispatcher("paymentList.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving payments for user " + userId, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("create".equals(action)) {
            createPayment(request, response);
        } else if ("update".equals(action)) {
            updatePayment(request, response);
        } else if ("delete".equals(action)) {
            deletePayment(request, response);
        }
    }

    private void createPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderIdStr = request.getParameter("orderId");
        String amountStr = request.getParameter("amount");
        String paymentDateStr = request.getParameter("paymentDate");
        String paymentMethod = request.getParameter("paymentMethod");
        String creditCardDetails = request.getParameter("creditCardDetails");

        int orderId = Integer.parseInt(orderIdStr);
        double amount = Double.parseDouble(amountStr);
        LocalDateTime paymentDate = LocalDateTime.parse(paymentDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        Payment newPayment = new Payment(0, orderId, amount, paymentDate, paymentMethod, creditCardDetails);

        try {
            paymentDAO.insertPayment(newPayment);
            response.sendRedirect("payment");
        } catch (SQLException e) {
            throw new ServletException("DB error while inserting payment", e);
        }
    }

    private void updatePayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paymentIdStr = request.getParameter("paymentId");
        String orderIdStr = request.getParameter("orderId");
        String amountStr = request.getParameter("amount");
        String paymentDateStr = request.getParameter("paymentDate");
        String paymentMethod = request.getParameter("paymentMethod");
        String creditCardDetails = request.getParameter("creditCardDetails");

        int paymentId = Integer.parseInt(paymentIdStr);
        int orderId = Integer.parseInt(orderIdStr);
        double amount = Double.parseDouble(amountStr);
        LocalDateTime paymentDate = LocalDateTime.parse(paymentDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        Payment updatedPayment = new Payment(paymentId, orderId, amount, paymentDate, paymentMethod, creditCardDetails);

        try {
            paymentDAO.updatePayment(updatedPayment);
            response.sendRedirect("payment");
        } catch (SQLException e) {
            throw new ServletException("DB error while updating payment", e);
        }
    }

    private void deletePayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paymentIdStr = request.getParameter("paymentId");
        int paymentId = Integer.parseInt(paymentIdStr);

        try {
            paymentDAO.deletePayment(paymentId);
            response.sendRedirect("payment");
        } catch (SQLException e) {
            throw new ServletException("DB error while deleting payment", e);
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
