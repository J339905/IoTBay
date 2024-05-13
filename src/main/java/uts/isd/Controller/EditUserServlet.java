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

public class EditUserServlet extends HttpServlet {

    private DBConnector db;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector(); // Initialize the DBConnector
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        if (userId == null || userId.trim().isEmpty()) {
            response.sendRedirect("viewUsers.jsp"); // Redirect if no user ID is found
            return;
        }

        HttpSession session = request.getSession();
        UserDAO userDAO = (UserDAO) session.getAttribute("userDAO");
        if (userDAO == null) {
            response.sendRedirect("admin.jsp"); // Redirect if UserDAO is not found in the session
            return;
        }

        try {
            User user = userDAO.findUserById(userId);
            if (user != null) {
                System.out.println("id: " + userId);
                request.setAttribute("user", user); // Set the user in the request scope
                request.getRequestDispatcher("/admin/editUser.jsp").forward(request, response);
            } else {
                response.sendRedirect("viewUsers.jsp"); // Redirect if no user is found
            }
        } catch (SQLException e) {
            throw new ServletException("Database access error.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String role = request.getParameter("role");

        System.out.println("id: " + userId);

        HttpSession session = request.getSession();
        UserDAO userDAO = (UserDAO) session.getAttribute("userDAO");
        if (userDAO == null) {
            request.getRequestDispatcher("admin.jsp").forward(request, response);
            return;
        }

        try {
            User user = userDAO.findUserById(userId);
            if (user == null) {
                request.getRequestDispatcher("/admin/viewUsers.jsp").forward(request, response);
                return;
            }

            user.setfirstName(firstName);
            user.setlastname(lastName);
            user.setPhone(Integer.parseInt(phone));
            user.setGender(gender);
            user.setRole(role);

            userDAO.adminUpdateUser(user);
            session.setAttribute("message", "User updated successfully.");
            response.sendRedirect("/admin.jsp"); 
        } catch (NumberFormatException | SQLException e) {
            request.setAttribute("error", "Error updating user: " + e.getMessage());
            request.getRequestDispatcher("/admin/editUser.jsp?userId=" + userId).forward(request, response);
        }
    }


    @Override
    public void destroy() {
        super.destroy();
        try {
            if (db != null) {
                db.closeConnection();
            }
        } catch (SQLException e) {
            System.err.println("Failed to close database connection.");
        }
    }
}
