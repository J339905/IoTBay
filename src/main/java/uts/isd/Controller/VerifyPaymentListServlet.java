package uts.isd.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.Payment;
import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.PaymentDAO;

public class VerifyPaymentListServlet extends HttpServlet {
    private DBConnector db;
    private PaymentDAO paymentDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector();
            Connection conn = db.openConnection();
            paymentDAO = new PaymentDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        session.removeAttribute("nopaymentsErr");
        
        User user = (User) session.getAttribute("user");
        String date = request.getParameter("date");

        if (user != null) {
            try {
                ArrayList<Payment> payments;
                if (date != null && !date.isEmpty()) {
                    payments = paymentDAO.fetchUserPaymentsByDate(user.getUserID(), date);
                    if (payments.size() == 0) {
                        session.setAttribute("nopaymentsErr", "No payment records found for the selected date.");
                        request.getRequestDispatcher("verifypaymentlist.jsp").include(request, response);
                        return;
                    } else {
                        request.setAttribute("payments", payments);
                    }
                } else {
                    payments = paymentDAO.fetchUserPayments(user.getUserID());
                    request.setAttribute("payments", payments);
                }
            } catch (SQLException e) {
                request.setAttribute("error", "Error retrieving payment records: " + e.getMessage());
                e.printStackTrace();
            }
            request.getRequestDispatcher("verifypaymentlist.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            if (db != null) {
                db.closeConnection();
            }
        } catch (SQLException e) {
            System.err.println("Failed to close database connection.");
        }
    }
}
