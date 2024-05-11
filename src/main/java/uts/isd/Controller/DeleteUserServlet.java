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

// public class DeleteUserServlet extends HttpServlet {
//     private DBConnector db;
//     private UserDAO userDAO;
//     private Connection conn;

//     @Override
//     public void init() throws ServletException {
//         super.init();
//         try {
//             db = new DBConnector();
//             Connection conn = db.openConnection();
//             userDAO = new UserDAO(conn);
//         } catch (ClassNotFoundException | SQLException e) {
//             throw new ServletException("DBConnector initialization failed.", e);
//         }
//     }

//     // @Override
//     // protected void doPost(HttpServletRequest request, HttpServletResponse
//     // response)
//     // throws ServletException, IOException {
//     // HttpSession session = request.getSession();
//     // User user = (User) session.getAttribute("user");

//     // // Check if user is logged in
//     // if (user != null) {
//     // // Log the logout activity
//     // int userId = user.getUserID();
//     // System.out.println(userId);
//     // try {
//     // userDAO.deleteUser(userId);
//     // System.out.println("Delete activity successfully.");
//     // } catch (SQLException e) {
//     // System.err.println("Error logging logout activity: " + e.getMessage());
//     // }

//     // // Invalidate session to log out user
//     // session.invalidate();
//     // System.out.println("User Deleted out successfully.");
//     // } else {
//     // System.out.println("No user session found.");
//     // }

//     // // Redirect to login page after logout
//     // response.sendRedirect("login.jsp");
//     // }
//     @Override
//     protected void doGet(HttpServletRequest request, HttpServletResponse response)
//             throws ServletException, IOException {
//         HttpSession session = request.getSession();
//         String userIdStr = request.getParameter("userId");
//         try {
//             int userId = Integer.parseInt(userIdStr);
//             userDAO.deleteUser(userId); // Attempt to delete the user
//             session.setAttribute("message", "User deleted successfully.");
//         } catch (NumberFormatException e) {
//             session.setAttribute("error", "Invalid user ID.");
//         } catch (SQLException e) {
//             session.setAttribute("error", "Error deleting user: " + e.getMessage());
//         }
//         response.sendRedirect("login.jsp"); // Redirect to a management page
//     }

//     @Override
//     public void destroy() {
//         try {
//             if (db != null) {
//                 db.closeConnection();
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
// }
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            int userId = user.getUserID(); // Get the logged-in user's ID from the session
            try {
                DBConnector db = new DBConnector();
                UserDAO userDAO = new UserDAO(db.openConnection());
                userDAO.deleteUser(userId);
                db.closeConnection();

                session.invalidate(); // Invalidate session to log out the user after deletion
                response.sendRedirect("index.jsp"); // Redirect to a confirmation page
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error during deletion.");
            }
        } else {
            response.sendRedirect("login.jsp"); // Redirect to login page if no user is found in session
        }
    }
}