package uts.isd.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import uts.isd.model.Order;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.OrderDAO;

@WebServlet(name = "OrderServlet", urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {

    private DBConnector db;
    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector(); // Ensure this class can open and close connections
            Connection conn = db.openConnection();
            orderDAO = new OrderDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdStr = request.getParameter("userId");
        String orderDateStr = request.getParameter("orderDate");
        String orderStatus = request.getParameter("orderStatus");
        String deliveryAddress = request.getParameter("deliveryAddress");
        String quantity = request.getParameter("quantity");

        int userId = Integer.parseInt(userIdStr);
        LocalDateTime orderDate = LocalDateTime.parse(orderDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        Order newOrder = new Order(0, userId, orderDate, orderStatus, deliveryAddress, quantity);

        try {
            orderDAO.insertOrder(newOrder);
            response.sendRedirect("order.jsp"); // Redirect to the order confirmation page
        } catch (SQLException e) {
            throw new ServletException("DB error while inserting order", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Order> listOrder = orderDAO.listAllOrders();
            request.setAttribute("orderList", listOrder);
            RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("DB error while listing orders", e);
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
