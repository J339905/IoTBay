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

public class RegisterServlet extends HttpServlet {

    private DBConnector db;
    private UserDAO userDAO;
    private logDAO logDao;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector();
            Connection conn = db.openConnection();
            userDAO = new UserDAO(conn);
            logDao = new logDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    // @Override
    // public void doPost(HttpServletRequest request, HttpServletResponse response)
    // throws ServletException, IOException {
    // HttpSession session = request.getSession();

    // String firstname = request.getParameter("firstname");
    // String lastname = request.getParameter("lastname");
    // String email = request.getParameter("email");
    // int phone = Integer.parseInt(request.getParameter("phone"));
    // String password = request.getParameter("password");
    // String gender = request.getParameter("gender");
    // String role = "Customer"; // Default role for new registrations
    // try {
    // int userId = userDAO.createUser(firstname, lastname, email, phone, password,
    // gender, role);
    // User user = new User(userId, firstname, lastname, email, phone, password,
    // gender, role);
    // user.setUserID(userId);
    // session.setAttribute("user", user);

    // // Log the activity
    // logDao.createLog(userId, java.time.LocalDateTime.now().toString(),
    // "Registered");

    // // Redirect to welcome page after registration
    // response.sendRedirect("welcome.jsp");
    // } catch (SQLException e) {
    // e.printStackTrace();
    // response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database
    // error during registration.");
    // } catch (Exception e) {
    // e.printStackTrace();
    // response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal
    // server error.");
    // }
    // }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Reset error message
        session.removeAttribute("emailErr");

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phoneStr = request.getParameter("phone"); // phone as String to check for numeric
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String tos = request.getParameter("tos"); // Check if terms of service is agreed
        String role = "Customer"; // Default role for new registrations

        boolean hasError = false;
        String emailRegex = "^.+@.+\\.com$";
        String phoneRegex = "^\\d+$";
        // String nameRegex = "^[^\d]*$";
        String nameRegex = "^[a-zA-Z\\s'-]+$";

        // Validate email
        // if (firstname == null || lastname == null || email == null || phoneStr ==
        // null || password == null
        // || gender == null || tos == null || role == null) {
        // session.setAttribute("nullErr", "please fill in all the fields given.");
        // request.getRequestDispatcher("register.jsp").include(request, response);
        // return; // Important to stop further processing if validation fails
        // }
        if (firstname == null || firstname.trim().isEmpty() || lastname == null || lastname.trim().isEmpty() ||
                email == null || email.trim().isEmpty() || phoneStr == null || phoneStr.trim().isEmpty() ||
                password == null || password.trim().isEmpty() || gender == null || gender.trim().isEmpty() ||
                role == null || role.trim().isEmpty()) {
            session.setAttribute("nullErr", "Please fill in all the fields given.");
            request.getRequestDispatcher("register.jsp").include(request, response);
            return; // Important to stop further processing if validation fails
        }

        if (!email.matches(emailRegex)) {
            session.setAttribute("emailErr", "Email format wrong, try again!");
            request.getRequestDispatcher("register.jsp").include(request, response);
            return; // Important to stop further processing if validation fails
        }

        if (!firstname.matches(nameRegex) || !lastname.matches(nameRegex)) {
            session.setAttribute("nametypeErr", "Names must contain letters only");
            request.getRequestDispatcher("/register.jsp").include(request, response);
            return;
        }
        if (password.length() < 6) {
            session.setAttribute("passwordErr", "Password must have a length of at least 5 characters");
            request.getRequestDispatcher("/register.jsp").include(request, response);
            return;
        }

        if (!phoneStr.matches(phoneRegex)) {
            session.setAttribute("phoneErr", "Phone number must consist of numbers");
            request.getRequestDispatcher("register.jsp").include(request, response);
            return; // Important to stop further processing if validation fails
        }
        int phone = Integer.parseInt(phoneStr); // Convert to integer

        // Validate email
        // if (email == null || !email.contains("@") || !email.endsWith(".com")) {
        // session.setAttribute("emailErr", "Email must contain '@' and end with
        // '.com'.");
        // hasError = true;
        // }

        // Validate phone number

        // Validate terms of service
        if (tos == null) {
            session.setAttribute("tosErr", "You must agree to the terms of service.");
            request.getRequestDispatcher("register.jsp").include(request, response);
            return;
            // hasError = true;
        }
        try {
            User checkuser = userDAO.findExistingUser(email);
            if (checkuser != null) {
                session.setAttribute("userexistsErr", "This user already exists");
                request.getRequestDispatcher("register.jsp").include(request, response);
                return;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (hasError) {
            response.sendRedirect("register.jsp"); // Redirect back to the registration form
            return; // Stop further processing
        }

        try {
            // No validation errors, proceed with user registration
            int userId = userDAO.createUser(firstname, lastname, email, phone, password, gender, role);
            User user = new User(userId, firstname, lastname, email, phone, password, gender, role);
            user.setUserID(userId);
            session.setAttribute("user", user);

            // Log the activity
            logDao.createLog(userId, java.time.LocalDateTime.now().toString(), "Registered");

            // Redirect to welcome page after successful registration
            response.sendRedirect("welcome.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error during registration.");
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
                db.closeConnection();
            }
        } catch (SQLException e) {
            System.err.println("Failed to close database connection.");
        }
    }
}
