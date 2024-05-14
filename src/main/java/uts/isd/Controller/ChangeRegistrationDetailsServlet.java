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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            try {
                user = userDAO.findUser(user.getEmail(), user.getPassword());
                System.out.println(user.getEmail());
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        session.removeAttribute("nametypeErr");
        session.removeAttribute("nullErr");
        session.removeAttribute("phoneErr");
        session.removeAttribute("passwordErr");

        if (user != null) {

            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String phoneStr = request.getParameter("phone");
            String password = request.getParameter("password");
            String gender = request.getParameter("gender");
            String role = "Customer";
            System.out.println(user.getEmail());
            String nameRegex = "^[a-zA-Z\\s'-]+$";
            String phoneRegex = "^\\d+$";

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
            if (!phoneStr.matches(phoneRegex)) {
                session.setAttribute("phoneErr", "Phone number must consist of numbers only");
                request.getRequestDispatcher("changeregistrationdetails.jsp").include(request,
                        response);
                return;
            }
            int phone = Integer.parseInt(phoneStr);

            if (!firstname.matches(nameRegex) || !lastname.matches(nameRegex)) {
                session.setAttribute("nametypeErr", "Names must contain letters only");
                request.getRequestDispatcher("changeregistrationdetails.jsp").include(request, response);
                return;
            }

            if (password.length() < 6) {
                session.setAttribute("passwordErr", "Password must have a length of at least 5 characters");
                request.getRequestDispatcher("changeregistrationdetails.jsp").include(request, response);
                return;
            }
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
