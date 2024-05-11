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

public class ChangeRegistrationDetailsServlet extends HttpServlet {

    private DBConnector db;
    private UserDAO userDAO;
    private logDAO logDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector(); // Initialize the DBConnector.
            Connection conn = db.openConnection(); // Open a connection
            userDAO = new UserDAO(conn); // Initialize UserDAO
            logDAO = new logDAO(conn); // Initialize logDAO
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

        if (user != null) {
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            int phone = Integer.parseInt(request.getParameter("phone"));
            String password = request.getParameter("password");
            String gender = request.getParameter("gender");
            String role = "Customer";
            String status = "Current User";
            System.out.println(user.getEmail());
            try {

                user = userDAO.updateUser(firstname, lastname, phone, password, gender, role, user.getEmail());
                session.setAttribute("user", user);
                response.sendRedirect("welcome.jsp"); // Redirect to profile page or a confirmation page
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
