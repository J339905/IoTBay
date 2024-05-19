package uts.isd.controller;

import uts.isd.model.Payment;
import uts.isd.model.User;
import uts.isd.model.dao.PaymentDAO;
import uts.isd.model.dao.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/ViewPaymentHistoryServlet")
public class ViewPaymentHistoryServlet extends HttpServlet {
    private PaymentDAO paymentDAO;

    public void init() throws ServletException {
        try {
            DBConnector dbConnector = new DBConnector();
            Connection conn = dbConnector.openConnection();
            paymentDAO = new PaymentDAO(conn);
        } catch (Exception e) {
            throw new ServletException("Unable to connect to database", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            List<Payment> payments = paymentDAO.getPaymentsByUserId(user.getUserID());
            request.setAttribute("payments", payments);
            request.getRequestDispatcher("paymentHistory.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("errorPage.jsp");
        }
    }
}
