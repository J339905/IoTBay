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

// Define the servlet and its mapping URL
@WebServlet(name = "OrderServlet", urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {

    private DBConnector db;
    private OrderDAO orderDAO;

    // Initializes the servlet, setting up the database connector and DAO
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector(); // Instantiate DBConnector to handle database connection
            Connection conn = db.openConnection(); // Open a database connection
            orderDAO = new OrderDAO(conn); // Instantiate the OrderDAO with the database connection
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    // Handles POST requests to create new orders
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdStr = request.getParameter("userId");
        String orderDateStr = request.getParameter("orderDate");
        String orderStatus = request.getParameter("orderStatus");
        String deliveryAddress = request.getParameter("deliveryAddress");
        String quantity = request.getParameter("quantity");

        // Parse user input to appropriate data types
        int userId = Integer.parseInt(userIdStr);
        LocalDateTime orderDate = LocalDateTime.parse(orderDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // Create a new order object
        Order newOrder = new Order(0, userId, orderDate, orderStatus, deliveryAddress, quantity);

        try {
            orderDAO.insertOrder(newOrder); // Insert the order into the database
            response.sendRedirect("order.jsp"); // Redirect to the order confirmation page
        } catch (SQLException e) {
            throw new ServletException("DB error while inserting order", e);
        }
    }

    // Handles GET requests to display all orders
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Order> listOrder = orderDAO.listAllOrders(); // Retrieve all orders from the database
            request.setAttribute("orderList", listOrder); // Set the list of orders as a request attribute
            RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
            dispatcher.forward(request, response); // Forward the request to the JSP page to display orders
        } catch (SQLException e) {
            throw new ServletException("DB error while listing orders", e);
        }
    }

    // Cleans up resources, such as database connections, before servlet destruction
    @Override
    public void destroy() {
        try {
            if (db != null) {
                db.closeConnection(); // Close the database connection
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
