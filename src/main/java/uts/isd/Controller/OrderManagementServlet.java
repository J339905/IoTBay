package main.java.uts.isd.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Order;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.OrderDAO;

public class OrderManagementServlet extends HttpServlet {

    private DBConnector db;
    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector();
            Connection conn = db.openConnection();
            orderDAO = new OrderDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = session.getAttribute("userId") != null ? (int) session.getAttribute("userId") : -1; // Assuming -1 for anonymous
        String status = "Pending"; // Default status for new orders

        try {
            Order order = new Order();
            order.setUserId(userId);
            order.setOrderDate(new Date());
            order.setStatus(status);

            orderDAO.createOrder(order); // Assuming a simplified version of createOrder
            session.setAttribute("currentOrder", order);
            response.sendRedirect("orderDetails.jsp?orderId=" + order.getOrderId());
        } catch (SQLException e) {
            log("SQL Error: " + e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to create order. Please try again.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderIdStr = request.getParameter("orderId");
        if (orderIdStr != null) {
            try {
                int orderId = Integer.parseInt(orderIdStr);
                Order order = orderDAO.getOrderById(orderId);
                request.setAttribute("order", order);
                request.getRequestDispatcher("viewOrder.jsp").forward(request, response);
            } catch (SQLException e) {
                log("SQL Error: " + e.getMessage(), e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve order details.");
            } catch (NumberFormatException e) {
                log("Number Format Error: " + e.getMessage(), e);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID format.");
            }
        }
    }

    @Override
    public void destroy() {
        try {
            if (db != null) {
                db.closeConnection();
            }
        } catch (SQLException e) {
            log("Failed to close database connection: " + e.getMessage(), e);
        }
        super.destroy();
    }
}
