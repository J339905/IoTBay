package main.java.uts.isd.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.sql.SQLException;
import uts.isd.model.Order;
import uts.isd.model.dao.OrderDAO;

@WebServlet(name = "OrderServlet", urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {

    private OrderDAO orderDAO;

    public void init() {
        // The JDBC details should be securely configured and retrieved, perhaps from environment variables or a config file
        String jdbcURL = "jdbc:mysql://localhost:3306/"; // Replace with your database URL
        String jdbcUsername = "root"; // Replace with your database username
        String jdbcPassword = "12345678"; // Replace with your database password
        orderDAO = new OrderDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String userIdStr = request.getParameter("userId");
        String orderDateStr = request.getParameter("orderDate");
        String orderStatus = request.getParameter("orderStatus");
        String deliveryAddress = request.getParameter("deliveryAddress");
        String quantity = request.getParameter("quantity");

        // Validate and convert data as necessary
        int userId = Integer.parseInt(userIdStr);
        LocalDateTime orderDate = LocalDateTime.parse(orderDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        Order newOrder = new Order(0, userId, orderDate, orderStatus, deliveryAddress, quantity);

        try {
            orderDAO.connect();
            orderDAO.insertOrder(newOrder);
            orderDAO.disconnect();
            response.sendRedirect("order.jsp"); // Redirect back to the order page or to a confirmation page
        } catch (SQLException e) {
            throw new ServletException("DB error while inserting order", e);
        }
    }

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        orderDAO.connect();
        List<Order> listOrder = orderDAO.listAllOrders();
        System.out.println("Fetched " + listOrder.size() + " orders.");  // Debugging output
        request.setAttribute("orderList", listOrder);
        orderDAO.disconnect();
        RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
        dispatcher.forward(request, response);
    } catch (SQLException e) {
        e.printStackTrace(); // To see if there's any SQL issue
        throw new ServletException("DB error while listing orders", e);
    }
}

}
