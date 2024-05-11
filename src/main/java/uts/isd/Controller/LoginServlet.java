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
            db = new DBConnector(); // Initialize the DBConnector.
            Connection conn = db.openConnection(); // Open a connection
            userDAO = new UserDAO(conn); // Initialize UserDAO
            logDAO = new logDAO(conn); // Initialize logDAO
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userDAO.findUser(email, password); // Attempt to find user
            if (user != null) { // Check if user exists and is active
                session.setAttribute("user", user); // Set user attribute in session
                logDAO.createLog(user.getUserID(), java.time.LocalDateTime.now().toString(), "Login"); // Log login
                                                                                                       // activity
                response.sendRedirect("welcome.jsp"); // Redirect to welcome page
            } else {
                session.setAttribute("loginErr", "Invalid login details or inactive account");
                response.sendRedirect("login.jsp"); // Redirect back to login page with error
            }
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("loginErr", "Database connection problem.");
            response.sendRedirect("login.jsp");
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
