


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

public class LoginServlet extends HttpServlet {

    private DBConnector db;
    private UserDAO userDAO;
    private logDAO logDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector();  // Initialize the DBConnector.
            Connection conn = db.openConnection();  // Open a connection
            userDAO = new UserDAO(conn);  // Initialize UserDAO
            logDAO = new logDAO(conn);  // Initialize logDAO
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            int userId = userDAO.retrieveUserId(email, password, true); // Get UserID from UserDAO
            if (userId != -1) { // Check if UserID is valid
                User user = userDAO.findUser(email, password); // Get User details using UserID
                session.setAttribute("user", user); // Set user attribute in session

                // Debug statement to trace the UserID

                // Log the login activity
                String currentTime = java.time.LocalDateTime.now().toString();
                logDAO.createLog(userId, currentTime, "Login"); // Use UserID for logging

                // response.sendRedirect("welcome.jsp");  // Navigate to welcome page
                response.sendRedirect("welcome.jsp");
            } else {
                session.setAttribute("loginErr", "Invalid email/password");
                response.sendRedirect("login.jsp");  // Stay on login page with error
            }
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("loginErr", "Database connection problem.");
            response.sendRedirect("login.jsp");
        }
    }
    public User getLoggedInUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (User) session.getAttribute("user");
        }
        return null;
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

