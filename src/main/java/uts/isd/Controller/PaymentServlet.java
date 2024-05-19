package uts.isd.controller;

import uts.isd.model.Payment;
import uts.isd.model.dao.PaymentDAO;
import uts.isd.model.dao.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;

@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {
    private PaymentDAO paymentDAO;
    private DBConnector db;

    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector();
            Connection conn = db.openConnection();
            paymentDAO = new PaymentDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action != null && action.equals("viewHistory")) {
                viewPaymentHistory(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "create":
                    createPayment(request, response);
                    break;
                case "update":
                    updatePayment(request, response);
                    break;
                case "delete":
                    deletePayment(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void createPayment(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        String paymentMethod = request.getParameter("paymentMethod");
        String cardNumber = request.getParameter("cardNumber");
        String expiryDate = request.getParameter("expiryDate");
        String cvv = request.getParameter("cvv");
        Date paymentDate = Date.valueOf(request.getParameter("paymentDate"));
    
        Payment payment = new Payment();
        payment.setOrderID(orderID);
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);
        payment.setCardNumber(cardNumber);
        payment.setExpiryDate(expiryDate);
        payment.setCvv(cvv);
        payment.setPaymentDate(paymentDate);
    
        paymentDAO.createPayment(payment);
        response.sendRedirect("paymentSuccess.jsp");
    }    

    private void updatePayment(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int paymentID = Integer.parseInt(request.getParameter("paymentID"));
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        String paymentMethod = request.getParameter("paymentMethod");
        Date paymentDate = new Date();

        Payment payment = new Payment(paymentID, orderID, amount, paymentDate, paymentMethod);
        paymentDAO.updatePayment(payment);
        response.sendRedirect("paymentSuccess.jsp");
    }

    private void deletePayment(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int paymentID = Integer.parseInt(request.getParameter("paymentID"));
        paymentDAO.deletePayment(paymentID);
        response.sendRedirect("paymentSuccess.jsp");
    }

    private void viewPaymentHistory(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Payment> payments = paymentDAO.getAllPayments();
        request.setAttribute("payments", payments);
        request.getRequestDispatcher("paymentHistory.jsp").forward(request, response);
    }

    public void destroy() {
        try {
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
