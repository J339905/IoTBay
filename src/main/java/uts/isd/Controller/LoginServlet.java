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
 
public class LoginServlet  extends HttpServlet {
 
    private DBConnector db;
    private UserDAO userDAO;

    
@Override
public void init() throws ServletException {
    super.init();
    try {
        db = new DBConnector(); // Initialize the DBConnector
        Connection conn = db.openConnection(); // Open a connection
        userDAO = new UserDAO(conn); // Initialize UserDAO with the connection
    } catch (ClassNotFoundException | SQLException e) {
        throw new ServletException("DBConnector initialization failed.", e);
    }
}
 
    @Override
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
 
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    UserDAO userDAO = (UserDAO) session.getAttribute("userDAO");

    System.out.println("email: " + email);
    System.out.println("pw: " + password);
 
    try{

        // if (userDAO == null) {
        //     // response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "UserDAO not initialized.");
        //     // return;
        //     Connection conn = db.openConnection();
        //     userDAO = new UserDAO(conn);
        //     session.setAttribute("userDAO", userDAO);
        // }

        User user =  userDAO.findUser(email, password);
        if(user != null){
            session.setAttribute("user", user);
            if (user.getRole().equals("Customer")) {
                response.sendRedirect("welcome.jsp");
            }
            else {
                response.sendRedirect("admin.jsp");
            }
        }
        else{
            session.setAttribute("loginErr", "Invalid email/password");
            response.sendRedirect("login.jsp");
 
        }
    }catch(Exception e){
        e.printStackTrace();
        session.setAttribute("loginErr", "Internal Error");
        response.sendRedirect("login.jsp");
    }
       
}
}
 