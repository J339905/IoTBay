package uts.isd.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


import uts.isd.model.Order;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.OrderDAO;

@WebServlet("/listOrders")
public class ListOrderServlet extends HttpServlet {
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
            e.printStackTrace();  // Log to console or a file
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Order> orders = orderDAO.listAllOrders();
            System.out.println("Orders fetched: " + orders.size());  // Add this line to check the size of the orders list
            request.setAttribute("orderList", orders);  // Ensure this matches the name used in JSP
            request.getRequestDispatcher("orderlist.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving orders", e);
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
