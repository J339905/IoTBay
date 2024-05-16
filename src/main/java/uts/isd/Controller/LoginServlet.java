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

    // Set up database connection and DAOs
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector();
            Connection conn = db.openConnection();
            userDAO = new UserDAO(conn);
            logDAO = new logDAO(conn);
            db = new DBConnector(); // Initialize the DBConnector in the init method
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    // This handles POST requests for users to be able to login
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // retrieving email and password from the request
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Check if either password or username is empty
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            session.setAttribute("nullErr", "Please fill in all the fields given.");
            request.getRequestDispatcher("login.jsp").include(request, response);
            return;
        }

        // find user in database by matching email and password, if user is found,
        // redirect the page based on whether the user is admin or customer
        try {
            User user = userDAO.findUser(email, password);
            if (user != null) {
                // session.setAttribute("user", user);
                // // Their Login activity should be logged into database
                // logDAO.createLog(user.getUserID(), java.time.LocalDateTime.now().toString(), "Login");
                // if (user.getRole().equals("Customer")) {
                //     response.sendRedirect("welcome.jsp");
                // } else {
                //     response.sendRedirect("admin.jsp");
                // }
                if (user.getIsActivated()) {
                    session.setAttribute("user", user);
                    logDAO.createLog(user.getUserID(), java.time.LocalDateTime.now().toString(), "Login");
                    if (user.getRole().equals("Customer")) {
                        response.sendRedirect("welcome.jsp");
                    }
                    else if (user.getRole().equals("Staff")) {
                        session.setAttribute("role", "Staff");
                        response.sendRedirect("admin.jsp");
                    } 
                    else {
                        session.setAttribute("role", "Admin");
                        response.sendRedirect("admin.jsp");
                    }
                }
                else {
                    response.sendRedirect("login.jsp");
                }
            } else {
                session.setAttribute("loginErr", "Invalid login details or inactive account");
                response.sendRedirect("login.jsp");
            }
        } catch (SQLException e) {
            session.setAttribute("loginErr", "Invalid email/password");
            response.sendRedirect("login.jsp");
        }
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
