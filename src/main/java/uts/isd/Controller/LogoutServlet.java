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
import uts.isd.model.dao.UserDAO;
import uts.isd.model.dao.logDAO;

public class LogoutServlet extends HttpServlet {

    private DBConnector db;
    private UserDAO userDAO;
    private logDAO logDAO;
    private LoginServlet loginServlet;


    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector();  // Initialize the DBConnector.
            Connection conn = db.openConnection();  // Open a connection
            userDAO = new UserDAO(conn);  // Initialize UserDAO
            logDAO = new logDAO(conn);  // Initialize logDAO
            loginServlet = new LoginServlet();
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        // Check if user is logged in
        if (user != null) {
            // Log the logout activity
            String currentTime = java.time.LocalDateTime.now().toString();
            int userId = user.getUserID();
            try {
                logDAO.createLog(userId, currentTime, "Logout");
                System.out.println("Logout activity logged successfully.");
            } catch (SQLException e) {
                System.err.println("Error logging logout activity: " + e.getMessage());
            }
            
            // Invalidate session to log out user
            session.invalidate();
            System.out.println("User logged out successfully.");
        } else {
            System.out.println("No user session found.");
        }

        // Redirect to login page after logout
        response.sendRedirect("login.jsp");
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
