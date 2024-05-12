package uts.isd.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.UserDAO;

public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            int userId = user.getUserID(); // Get the logged-in user's ID from the session
            try {
                DBConnector db = new DBConnector();
                UserDAO userDAO = new UserDAO(db.openConnection());
                userDAO.deleteUser(userId);
                db.closeConnection();

                session.invalidate(); // Invalidate session to log out the user after deletion
                response.sendRedirect("index.jsp"); // Redirect to a confirmation page
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error during deletion.");
            }
        } else {
            response.sendRedirect("login.jsp"); // Redirect to login page if no user is found in session
        }
    }
}