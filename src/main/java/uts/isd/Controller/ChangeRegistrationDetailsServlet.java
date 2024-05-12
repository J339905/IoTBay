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
            String phoneStr = request.getParameter("phone");
            String password = request.getParameter("password");
            String gender = request.getParameter("gender");
            String role = "Customer";
            String status = "Current User";
            System.out.println(user.getEmail());
            String phoneRegex = "^\\d+$";
            String emailRegex = "^.+@.+\\.com$";
            String nameRegex = "^[a-zA-Z\\s'-]+$";
            if (firstname == null || firstname.trim().isEmpty() || lastname == null ||
                    lastname.trim().isEmpty()
                    || phoneStr == null || phoneStr.trim().isEmpty() ||
                    password == null || password.trim().isEmpty() || gender == null ||
                    gender.trim().isEmpty()
                    || role == null || role.trim().isEmpty()) {
                session.setAttribute("nullErr", "Please fill in all the fields given.");
                request.getRequestDispatcher("changeregistrationdetails.jsp").include(request,
                        response);
                return; // Important to stop further processing if validation fails
            }
            if (!firstname.matches(nameRegex) || !lastname.matches(nameRegex)) {
                session.setAttribute("nametypeErr", "Names must contain letters only");
                request.getRequestDispatcher("changeregistrationdetails.jsp").include(request, response);
                return;
            }
            if (!phoneStr.matches(phoneRegex)) {
                session.setAttribute("phoneErr", "Phone number must consist of numbers");
                request.getRequestDispatcher("changeregistrationdetails.jsp").include(request,
                        response);
                return; // Important to stop further processing if validation fails
            }
            int phone = Integer.parseInt(phoneStr);

            if (password.length() < 6) {
                session.setAttribute("passwordErr", "Password must have a length of at least 5 characters");
                request.getRequestDispatcher("/changeregistrationdetails.jsp").include(request, response);
                return;
            }
            try {
                User checkuser = userDAO.findExistingUser(user.getEmail());
                if (checkuser != null) {
                    session.setAttribute("userexistsErr", "This user already exists");
                    request.getRequestDispatcher("changeregistrationdetails.jsp").include(request, response);
                    return;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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
    // String samedetailsErr = (String) session.getAttribute("samedetailsErr");

    // protected void doPost(HttpServletRequest request, HttpServletResponse
    // response)
    // throws ServletException, IOException {
    // HttpSession session = request.getSession();
    // User currentuser = (User) session.getAttribute("user");
    // String firstname = currentuser.getfirstName();
    // String lastname = currentuser.getlastname();
    // int phoneStr = currentuser.getPhone();
    // String password = currentuser.getPassword();
    // String gender = currentuser.getGender();
    // System.out.println(firstname + " " + lastname + " " + phoneStr);
    // if (currentuser != null) {

    // String newfirstname = request.getParameter("firstname");
    // String newlastname = request.getParameter("lastname");
    // String newphoneStr = request.getParameter("phone");
    // String newpassword = request.getParameter("password");
    // String newgender = request.getParameter("gender");
    // String role = "Customer";
    // System.out.println(currentuser.getEmail());
    // String phoneRegex = "^\\d+$";
    // // if (firstname==newfirstname && lastname == newlastname && phoneStr ==
    // // Integer.parseInt(newphoneStr)
    // // && password == newpassword
    // // && gender == newgender) {
    // // session.setAttribute("phoneErr", "No changes have been made ");
    // //
    // request.getRequestDispatcher("changeregistrationdetails.jsp").include(request,
    // // response);
    // // return;
    // // }

    // if (newfirstname == null || newfirstname.trim().isEmpty() || newlastname ==
    // null
    // || newlastname.trim().isEmpty()
    // || newphoneStr == null || newphoneStr.trim().isEmpty() ||
    // newpassword == null || newpassword.trim().isEmpty() || newgender == null
    // || newgender.trim().isEmpty()) {
    // session.setAttribute("nullErr", "Please fill in all the fields given.");
    // request.getRequestDispatcher("changeregistrationdetails.jsp").include(request,
    // response);
    // return; // Important to stop further processing if validation fails
    // }
    // if (firstname.equals(newfirstname) && lastname.equals(newlastname)
    // && phoneStr == Integer.parseInt(newphoneStr)
    // && password.equals(newpassword)
    // && gender.equals(newgender)) {
    // session.setAttribute("samedetailsErr", "No changes have been made ");
    // request.getRequestDispatcher("changeregistrationdetails.jsp").include(request,
    // response);
    // return;
    // }

    // if (!newphoneStr.matches(phoneRegex)) {
    // session.setAttribute("phoneErr", "Phone number must consist of numbers");
    // request.getRequestDispatcher("changeregistrationdetails.jsp").include(request,
    // response);
    // return; // Important to stop further processing if validation fails
    // }
    // int newphone = Integer.parseInt(newphoneStr);
    // try {

    // currentuser = userDAO.updateUser(firstname, lastname, newphone, password,
    // gender, role,
    // currentuser.getEmail());
    // session.setAttribute("user", currentuser);
    // response.sendRedirect("welcome.jsp"); // Redirect to profile page or a
    // confirmation page
    // } catch (SQLException e) {
    // e.printStackTrace();
    // response.sendRedirect("error.jsp");
    // }
    // } else {
    // response.sendRedirect("login.jsp");
    // }
    // }

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
