package uts.isd.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.OrderDAO;

// WebServlet annotation declares an URL pattern this servlet will respond to.
@WebServlet("/viewSavedOrders")
public class ViewSavedOrdersServlet extends HttpServlet {
    private DBConnector db;
    private OrderDAO orderDAO;

    // Servlet initialization method: establishes the database connection and initializes DAOs.
    @Override
    public void init() throws ServletException {
        try {
            db = new DBConnector(); // Attempts to create a new DBConnector instance.
            Connection conn = db.openConnection(); // Opens a database connection using the DBConnector.
            orderDAO = new OrderDAO(conn); // Initializes an instance of OrderDAO with the open connection.
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed", e); // Handle potential failures in DB connection setup.
        }
    }

    // Handles the GET request: shows saved orders for the logged-in user.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); // Retrieves the current session.
        User user = (User) session.getAttribute("user"); // Retrieves the user object from the session.

        if (user == null) {
            response.sendRedirect("login.jsp"); // Redirects to login page if no user is logged in.
            return;
        }

        try {
            // Retrieves a list of saved orders from the database for the logged-in user and sets it as a request attribute.
            request.setAttribute("savedOrders", orderDAO.getOrderListByUserId(user.getUserID()));
            request.getRequestDispatcher("savedOrders.jsp").forward(request, response); // Forwards the request to the savedOrders.jsp page.
        } catch (SQLException e) {
            throw new ServletException("Error retrieving saved orders", e); // Handle SQL exceptions during data retrieval.
        }
    }

    // Cleans up resources, primarily the database connection.
    @Override
    public void destroy() {
        try {
            if (db != null) {
                db.closeConnection(); // Closes the database connection when the servlet is destroyed.
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Logs errors if the connection closure fails.
        }
    }
}
