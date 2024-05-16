package uts.isd.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.logDAO;

public class LogoutServlet extends HttpServlet {

    private DBConnector db;
    private logDAO logDAO;

    // Set up database connection and DAOs
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector();
            Connection conn = db.openConnection();
            logDAO = new logDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    // This handles Get request to logout a user
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // If user is logged in, then the date/time as well as the user id is retrieved,

        if (user != null) {
            String currentTime = java.time.LocalDateTime.now().toString();
            int userId = user.getUserID();
            try {
                // logout activity is also logged into database
                logDAO.createLog(userId, currentTime, "Logout");
                System.out.println("Logout activity logged successfully.");
            } catch (SQLException e) {
                System.err.println("Error logging logout activity: " + e.getMessage());
            }
            // while the session ends to log the user out and redirect them to the home page
            session.invalidate();
            System.out.println("User logged out successfully.");
        } else {
            System.out.println("No user session found.");
        }

        response.sendRedirect("login.jsp");
    }

    // close DB connection
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