

 
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
 
public class RegisterServlet extends HttpServlet {
 
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
<<<<<<< HEAD
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
 
    String email = request.getParameter("email");
    String firstname = request.getParameter("firstname");
    String lastname = request.getParameter("secondname");
    String password = request.getParameter("password");
    int phone = Integer.parseInt(request.getParameter("phone"));
    String gender = request.getParameter("gender");  // Retrieve gender from request
    System.out.println("Received gender: " + gender); // This should print the gender value or null
    String role = "Customer";
 
    // Print the gender to see what is being received from the form
    System.out.println("Gender from form: " + gender);
 
    try {
        UserDAO userDAO = (UserDAO) session.getAttribute("userDAO");
 
        if (userDAO == null) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "UserDAO not initialized.");
            return;
=======
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        session.removeAttribute("emailErr");

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phoneStr = request.getParameter("phone");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String tos = request.getParameter("tos");
        String role = "Customer";

        boolean hasError = false;
        String emailRegex = "^.+@.+\\.com$";
        String phoneRegex = "^\\d{10}+$";
        String nameRegex = "^[a-zA-Z\\s'-]+$";

        if (firstname == null || firstname.trim().isEmpty() || lastname == null || lastname.trim().isEmpty() ||
                email == null || email.trim().isEmpty() || phoneStr == null || phoneStr.trim().isEmpty() ||
                password == null || password.trim().isEmpty() || gender == null || gender.trim().isEmpty() ||
                role == null || role.trim().isEmpty()) {
            session.setAttribute("nullErr", "Please fill in all the fields given.");
            request.getRequestDispatcher("register.jsp").include(request, response);
            return;
        }

        if (!email.matches(emailRegex)) {
            session.setAttribute("emailErr", "Email format wrong, try again!");
            request.getRequestDispatcher("register.jsp").include(request, response);
            return;
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
            session.setAttribute("phoneErr", "Phone number must consist of 10 numbers");
            request.getRequestDispatcher("register.jsp").include(request, response);
            return;
        }
        int phone = Integer.parseInt(phoneStr);

        if (tos == null) {
            session.setAttribute("tosErr", "You must agree to the terms of service.");
            request.getRequestDispatcher("register.jsp").include(request, response);
            return;
        }
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

        if (hasError) {
            response.sendRedirect("register.jsp");
            return;
        }

        try {
            int userId = userDAO.createUser(firstname, lastname, email, phone, password, gender, role);
            User user = new User(userId, firstname, lastname, email, phone, password, gender, role);
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
>>>>>>> NasBranch-LogAccess
        }

        userDAO.createUser(firstname, lastname, email, phone, password, gender, role);
 
        User user = new User();
        user.setfirstName(firstname);
        user.setlastname(lastname);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setGender(gender);
        session.setAttribute("user", user);
 
        response.sendRedirect("welcome.jsp");
    } catch (Exception e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error.");
    }
<<<<<<< HEAD
}
 
 
=======

>>>>>>> NasBranch-LogAccess
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
