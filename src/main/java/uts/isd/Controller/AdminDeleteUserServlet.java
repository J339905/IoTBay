package uts.isd.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.UserDAO;

public class AdminDeleteUserServlet extends HttpServlet {

    private DBConnector db; // Declare DBConnector

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector(); // Initialize the DBConnector in the init method
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        if (userId == null || userId.trim().isEmpty()) {
            response.sendRedirect("/admin/viewUsers.jsp"); 
            return;
        }

        HttpSession session = request.getSession();
        UserDAO userDAO = (UserDAO) session.getAttribute("userDAO");
        if (userDAO == null) {
            response.sendRedirect("/admin.jsp"); 
            return;
        }

        try {

            userDAO.deleteUser(Integer.valueOf(userId)); // delete user by id
            response.sendRedirect("/admin.jsp"); // redirect to admin page
            
        } catch (SQLException e) {
            throw new ServletException("Database access error.", e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            if (db != null) {
                db.closeConnection(); // Properly close your DB connection
            }
        } catch (SQLException e) {
            System.err.println("Failed to close database connection.");
        }
    }
    
}
