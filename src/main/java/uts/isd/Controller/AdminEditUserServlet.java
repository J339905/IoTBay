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

public class AdminEditUserServlet extends HttpServlet {

    private DBConnector db;
    private logDAO logDao;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector(); // Initialize the DBConnector
            Connection conn = db.openConnection();
            logDao = new logDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        if (userId == null || userId.trim().isEmpty()) {
            response.sendRedirect("/admin/viewUsers.jsp"); // Redirect if no user ID is found
            return;
        }

        HttpSession session = request.getSession();
        UserDAO userDAO = (UserDAO) session.getAttribute("userDAO");
        if (userDAO == null) {
            response.sendRedirect("/admin.jsp"); // Redirect if UserDAO is not found in the session
            return;
        }

        try {
            User user = userDAO.findUserById(userId);
            if (user != null) {
                request.setAttribute("user", user); // Set the user in the request scope
                request.getRequestDispatcher("/admin/editUser.jsp").forward(request, response);
            } else {
                response.sendRedirect("/admin/viewUsers.jsp"); // Redirect if no user is found
            }
        } catch (SQLException e) {
            throw new ServletException("Database access error.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String userId = request.getParameter("userId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneStr = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String role = request.getParameter("role");
        String isActivated = request.getParameter("isActivated");

        HttpSession session = request.getSession();
        UserDAO userDAO = (UserDAO) session.getAttribute("userDAO");
        if (userDAO == null) {
            request.getRequestDispatcher("admin.jsp").forward(request, response);
            return;
        }

        User u = new User();
        try {
            u = userDAO.findUserById(userId);
            if (u != null) {
                request.setAttribute("user", u);
            }
        } catch (SQLException e) {
            throw new ServletException("Database access error.", e);
        }

        String emailRegex = "^.+@.+\\.com$";
        String phoneRegex = "^\\d+$";
        String nameRegex = "^[a-zA-Z\\s'-]+$";

        if (firstName == null || firstName.trim().isEmpty() || lastName == null || lastName.trim().isEmpty() ||
                email == null || email.trim().isEmpty() || 
                phoneStr == null || phoneStr.trim().isEmpty() ||
                gender == null || gender.trim().isEmpty() ||
                role == null || role.trim().isEmpty()) {
            session.setAttribute("nullErr", "Please fill in all the fields given.");
            request.getRequestDispatcher("admin/editUser.jsp").include(request, response);
            return;
        }

        if (!email.matches(emailRegex)) {
            session.setAttribute("emailErr", "Email format wrong, try again!");
            request.getRequestDispatcher("admin/editUser.jsp").include(request, response);
            return;
        }

        if (!firstName.matches(nameRegex) || !lastName.matches(nameRegex)) {
            session.setAttribute("nametypeErr", "Names must contain letters only");
            request.getRequestDispatcher("/admin/editUser.jsp").include(request, response);
            return;
        }

        if (!phoneStr.matches(phoneRegex)) {
            session.setAttribute("phoneErr", "Phone number must consist of numbers only");
            request.getRequestDispatcher("admin/v.jsp").include(request, response);
            return;
        }

        try {
            User user = userDAO.findUserById(userId);
            if (user == null) {
                request.getRequestDispatcher("/admin/editUser.jsp").forward(request, response);
                return;
            }

            User checkuser = userDAO.findExistingUser(email);
            if (checkuser != null && !email.equals(u.getEmail())) {
                session.setAttribute("userexistsErr", "This user already exists");
                request.getRequestDispatcher("/admin/editUser.jsp").include(request, response);
                return;
            }

            user.setEmail(email);
            user.setfirstName(firstName);
            user.setlastname(lastName);
            user.setPhone(Integer.parseInt(phoneStr));
            user.setGender(gender);
            user.setRole(role);
            if (isActivated.equals("true")) {
                user.setIsActivated(true);
            }
            else {
                user.setIsActivated(false);
            }

            userDAO.adminUpdateUser(user);
            logDao.createLog(Integer.valueOf(userId), java.time.LocalDateTime.now().toString(), "Updated");
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
