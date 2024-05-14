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
    // THis handles Post requests for deleting a user
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // If user is logged in, their id is retrieved, which will be used for the
        // parameter for the deleteUser method to remove user from database. Session will also invalidate.
        if (user != null) {
            int userId = user.getUserID();
            try {
                DBConnector db = new DBConnector();
                UserDAO userDAO = new UserDAO(db.openConnection());
                userDAO.deleteUser(userId);
                db.closeConnection();

                session.invalidate();
                response.sendRedirect("index.jsp");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error during deletion.");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}