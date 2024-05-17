package uts.isd.Controller;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    // Set up database connection and DAOs
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

    // handles POST requests to register a new user
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        // This removes preivious error messages when new errors appear
        session.removeAttribute("emailErr");
        session.removeAttribute("nametypeErr");
        session.removeAttribute("nullErr");
        session.removeAttribute("phoneErr");
        session.removeAttribute("passwordErr");
        session.removeAttribute("tosErr");
        session.removeAttribute("userexistsErr");

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phoneStr = request.getParameter("phone");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String tos = request.getParameter("tos");
        String role = "Customer";

        // The utilisation of string regex expressions allows for efficient matchup in
        // text fields to ensure values inputted by users are valid
        String emailRegex = "^.+@.+\\.com$";
        String phoneRegex = "^\\d+$";
        String nameRegex = "^[a-zA-Z\\s'-]+$";

        // This sends an error message if a field is empty
        if (firstname == null || firstname.trim().isEmpty() || lastname == null || lastname.trim().isEmpty() ||
                email == null || email.trim().isEmpty() || phoneStr == null || phoneStr.trim().isEmpty() ||
                password == null || password.trim().isEmpty() || gender == null || gender.trim().isEmpty() ||
                role == null || role.trim().isEmpty()) {
            session.setAttribute("nullErr", "Please fill in all the fields given.");
            request.getRequestDispatcher("register.jsp").include(request, response);
            return;
        }
        // This sends an error message for invalid email format
        if (!email.matches(emailRegex)) {
            session.setAttribute("emailErr", "Email format wrong, try again!");
            request.getRequestDispatcher("register.jsp").include(request, response);
            return;
        }
        // This sends an error message for invalid name format
        if (!firstname.matches(nameRegex) || !lastname.matches(nameRegex)) {
            session.setAttribute("nametypeErr", "Names must contain letters only");
            request.getRequestDispatcher("/register.jsp").include(request, response);
            return;
        }
        // This sends an error message if password is not long enough
        if (password.length() < 5) {
            session.setAttribute("passwordErr", "Password must have a length of at least 5 characters");
            request.getRequestDispatcher("/register.jsp").include(request, response);
            return;
        }
        // This sends an error message if there is a nonnummeric value inputted
        if (!phoneStr.matches(phoneRegex)) {
            session.setAttribute("phoneErr", "Phone number must consist of numbers only");
            request.getRequestDispatcher("register.jsp").include(request, response);
            return;
        }
        int phone = Integer.parseInt(phoneStr);
        // error if user doesn't agree to terms and service
        if (tos == null) {
            session.setAttribute("tosErr", "You must agree to the terms of service.");
            request.getRequestDispatcher("register.jsp").include(request, response);
            return;
        }
        // This checks for if a user with given email already exists
        try {
            User checkuser = userDAO.findExistingUser(email);
            if (checkuser != null) {
                session.setAttribute("userexistsErr", "This user already exists");
                request.getRequestDispatcher("register.jsp").include(request, response);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // User is created here after a userId is given, and this activity is logged
        // into database and user is redirected into welcome page if successful
        try {
            int userId = userDAO.createUser(firstname, lastname, email, phone, password, gender, role);
            boolean isActivated = true;
            User user = new User(userId, firstname, lastname, email, phone, password, gender, role, isActivated);
            user.setUserID(userId);
            session.setAttribute("user", user);

            logDao.createLog(userId, java.time.LocalDateTime.now().toString(), "Registered");

            response.sendRedirect("welcome.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error during registration.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error.");
        }

        User user = new User();
        user.setfirstName(firstname);
        user.setlastname(lastname);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setGender(gender);
        session.setAttribute("user", user);

        response.sendRedirect("welcome.jsp");
    }

    // close DB connection
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