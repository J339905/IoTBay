package uts.isd.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        try {
            db = new DBConnector();
            Connection conn = db.openConnection();
            orderDAO = new OrderDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
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
    
        String searchType = request.getParameter("searchType");
        String searchTerm = request.getParameter("searchTerm");
        String currentSortBy = request.getParameter("sortBy") != null ? request.getParameter("sortBy") : "OrderID";
        String currentSortOrder = request.getParameter("sortOrder") != null ? request.getParameter("sortOrder") : "asc";
    
        try {
            List<Order> orders;
            if (searchType != null && !searchTerm.isEmpty()) {
                orders = orderDAO.searchOrdersByUserId(userId, searchType, searchTerm, currentSortBy, currentSortOrder);
            } else {
                orders = orderDAO.listOrdersByUserId(userId, currentSortBy, currentSortOrder);
            }
            request.setAttribute("orderList", orders);
            request.getRequestDispatcher("orderlist.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving orders for user " + userId, e);
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
