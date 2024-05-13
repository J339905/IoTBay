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

public class AdminCreateUserServlet extends HttpServlet {

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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String password = request.getParameter("password");
        int phone = Integer.parseInt(request.getParameter("phone"));
        String gender = request.getParameter("gender"); // Retrieve gender from request
        String role = request.getParameter("role");
        String status = "Current User";

        try {
            UserDAO userDAO = (UserDAO) session.getAttribute("userDAO");

            if (userDAO == null) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "UserDAO not initialized.");
                return;
            }

            userDAO.adminCreateUser(firstname, lastname, email, phone, password, gender, role);
            response.sendRedirect("/admin.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error.");
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