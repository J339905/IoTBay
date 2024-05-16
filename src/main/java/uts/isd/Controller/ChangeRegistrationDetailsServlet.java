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

public class ChangeRegistrationDetailsServlet extends HttpServlet {

    private DBConnector db;
    private UserDAO userDAO;

    // Set up database connection and DAOs
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector();
            Connection conn = db.openConnection();
            userDAO = new UserDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    // This handles Get requests to display current user details
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // if user is logged in, their latest details are retrieved to be displayed,
        // otherwise they wull be redirected to login page
        if (user != null) {
            try {
                user = userDAO.findUser(user.getEmail(), user.getPassword());
                request.setAttribute("user", user);
                request.getRequestDispatcher("/changeregistrationdetails.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    // This handles POST requeststo allow user details to be updated
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // This removes any previous error messages if new error messages appear
        session.removeAttribute("nametypeErr");
        session.removeAttribute("nullErr");
        session.removeAttribute("phoneErr");
        session.removeAttribute("passwordErr");

        // If user is logged in their details should be retrieved
        if (user != null) {

            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String phoneStr = request.getParameter("phone");
            String password = request.getParameter("password");
            String gender = request.getParameter("gender");
            String role = "Customer";

            // Regex expressions for validations
            String nameRegex = "^[a-zA-Z\\s'-]+$";
            String phoneRegex = "^\\d+$";

            // Provide error message if any field is empty
            if (firstname == null || firstname.trim().isEmpty() || lastname == null ||
                    lastname.trim().isEmpty()
                    || phoneStr == null || phoneStr.trim().isEmpty() ||
                    password == null || password.trim().isEmpty() || gender == null ||
                    gender.trim().isEmpty()
                    || role == null || role.trim().isEmpty()) {
                session.setAttribute("nullErr", "Please fill in all the fields given.");
                request.getRequestDispatcher("changeregistrationdetails.jsp").include(request,
                        response);
                return;
            }
            // provide error message if phone number consists of values other than numbers
            if (!phoneStr.matches(phoneRegex)) {
                session.setAttribute("phoneErr", "Phone number must consist of numbers only");
                request.getRequestDispatcher("changeregistrationdetails.jsp").include(request,
                        response);
                return;
            }
            int phone = Integer.parseInt(phoneStr);

            // If user name doesn't contain letters only, an error message will be provided
            if (!firstname.matches(nameRegex) || !lastname.matches(nameRegex)) {
                session.setAttribute("nametypeErr", "Names must contain letters only");
                request.getRequestDispatcher("changeregistrationdetails.jsp").include(request, response);
                return;
            }
            // If password length is too short, error message will be provided
            if (password.length() < 6) {
                session.setAttribute("passwordErr", "Password must have a length of at least 5 characters");
                request.getRequestDispatcher("changeregistrationdetails.jsp").include(request, response);
                return;
            }

            // User is updated by calling updaeuser method, and redirecting the user back to
            // the welcome page with their new details
            try {

                user = userDAO.updateUser(firstname, lastname, phone, password, gender, role,
                        user.getEmail());
                session.setAttribute("user", user);
                response.sendRedirect("welcome.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    // close DB connection
    @Override
    public void destroy() {
        try {
            if (db != null) {
                db.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}