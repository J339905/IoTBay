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
 
public class LoginServlet extends HttpServlet {
 
    private DBConnector db;
@Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector();
            Connection conn = db.openConnection();
            userDAO = new UserDAO(conn);
            logDAO = new logDAO(conn);
            db = new DBConnector(); // Initialize the DBConnector in the init method
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userDAO.findUser(email, password);
            if (user != null) {
                session.setAttribute("user", user);
                logDAO.createLog(user.getUserID(), java.time.LocalDateTime.now().toString(), "Login");

                response.sendRedirect("welcome.jsp");
            } else {
                session.setAttribute("loginErr", "Invalid login details or inactive account");
                response.sendRedirect("login.jsp");
            }
        }
        else{
            session.setAttribute("loginErr", "Invalid email/password");
            response.sendRedirect("login.jsp");
        }
    }

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
