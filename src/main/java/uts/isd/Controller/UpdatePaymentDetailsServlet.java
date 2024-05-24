package uts.isd.Controller;

import uts.isd.model.Payment;
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
import java.sql.SQLException;

@WebServlet("/viewUpdatePaymentDetails")
public class UpdatePaymentDetailsServlet extends HttpServlet {
    private DBConnector db;
    private PaymentDAO paymentDAO;

    @Override
    public void init() throws ServletException {
        try {
            db = new DBConnector();
            Connection conn = db.openConnection();
            paymentDAO = new PaymentDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int paymentID = Integer.parseInt(request.getParameter("paymentID"));
        try {
            Payment payment = paymentDAO.getPaymentById(paymentID);
            if (payment != null) {
                request.setAttribute("payment", payment);
                request.getRequestDispatcher("updatePaymentDetails.jsp").forward(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException e) {
            throw new ServletException("Error retrieving payment details", e);
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
