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

public class AdminCreateUserServlet extends HttpServlet {

    private DBConnector db; // Declare DBConnector
    private logDAO logDao;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector(); // Initialize the DBConnector in the init method
            Connection conn = db.openConnection();
            logDao = new logDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        session.removeAttribute("emailErr");
        session.removeAttribute("nametypeErr");
        session.removeAttribute("nullErr");
        session.removeAttribute("phoneErr");
        session.removeAttribute("passwordErr");
        session.removeAttribute("userexistsErr");

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phoneStr = request.getParameter("phone");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String role = "Customer";
        String isActivated = request.getParameter("isActivated");

        String emailRegex = "^.+@.+\\.com$";
        String phoneRegex = "^\\d+$";
        String nameRegex = "^[a-zA-Z\\s'-]+$";

        if (firstname == null || firstname.trim().isEmpty() || lastname == null || lastname.trim().isEmpty() ||
                email == null || email.trim().isEmpty() || phoneStr == null || phoneStr.trim().isEmpty() ||
                password == null || password.trim().isEmpty() || gender == null || gender.trim().isEmpty() ||
                role == null || role.trim().isEmpty()) {
            session.setAttribute("nullErr", "Please fill in all the fields given.");
            request.getRequestDispatcher("/admin/createUser.jsp").include(request, response);
            return;
        }

        if (!email.matches(emailRegex)) {
            session.setAttribute("emailErr", "Email format wrong, try again!");
            request.getRequestDispatcher("/admin/createUser.jsp").include(request, response);
            return;
        }

        if (!firstname.matches(nameRegex) || !lastname.matches(nameRegex)) {
            session.setAttribute("nametypeErr", "Names must contain letters only");
            request.getRequestDispatcher("/admin/createUser.jsp").include(request, response);
            return;
        }
        if (password.length() < 5) {
            session.setAttribute("passwordErr", "Password must have a length of at least 5 characters");
            request.getRequestDispatcher("/admin/createUser.jsp").include(request, response);
            return;
        }

        if (!phoneStr.matches(phoneRegex)) {
            session.setAttribute("phoneErr", "Phone number must consist of numbers only");
            request.getRequestDispatcher("/admin/createUser.jsp").include(request, response);
            return;
        }
        int phone = Integer.parseInt(phoneStr);

        try {
            UserDAO userDAO = (UserDAO) session.getAttribute("userDAO");

            if (userDAO == null) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "UserDAO not initialized.");
                return;
            }

            User checkuser = userDAO.findExistingUser(email);
            if (checkuser != null) {
                session.setAttribute("userexistsErr", "This user already exists");
                request.getRequestDispatcher("/admin/createUser.jsp").include(request, response);
                return;
            }
            
            boolean checkIsActivated = true;
            if (isActivated == "true") {
                checkIsActivated = true;
            }
            else {
                checkIsActivated = false;
            }
            int userId = userDAO.adminCreateUser(firstname, lastname, email, phone, password, gender, role, checkIsActivated);
            logDao.createLog(userId, java.time.LocalDateTime.now().toString(), "Registered");
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