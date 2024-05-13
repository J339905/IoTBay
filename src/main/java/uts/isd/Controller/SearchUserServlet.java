package uts.isd.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.UserDAO;

public class SearchUserServlet extends HttpServlet {
    
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
        HttpSession session = request.getSession();

        String firstname = request.getParameter("firstName");
        String lastname = request.getParameter("lastName");
        String phone = request.getParameter("phoneNumber");

        ArrayList<User> users = new ArrayList<User>();
        try {
            UserDAO userDAO = (UserDAO) session.getAttribute("userDAO");
    
            if (userDAO == null) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "UserDAO not initialized.");
                return;
            }
            users = userDAO.findUsersByNameNPhone(firstname, lastname, phone);
            request.setAttribute("users", users);
        } catch(SQLException e) {
            e.printStackTrace();
        } 

        request.getRequestDispatcher("/admin/searchResult.jsp").forward(request, response);
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
