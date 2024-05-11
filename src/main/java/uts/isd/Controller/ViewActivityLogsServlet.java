
package uts.isd.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.Logs;
import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.UserDAO;
import uts.isd.model.dao.logDAO;

public class ViewActivityLogsServlet extends HttpServlet {
    private DBConnector db;
    private UserDAO userDAO;
    private logDAO logDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector();
            Connection conn = db.openConnection();
            userDAO = new UserDAO(conn);
            logDAO = new logDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward GET requests to doPost to handle in one method
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        String date = request.getParameter("date");

        if (user != null) {
            try {
                ArrayList<Logs> logs;
                if (date != null && !date.isEmpty()) {
                    System.out.println(date);
                    logs = logDAO.fetchSpecificUserLogsByDate(user.getUserID(), date); // Fetch logs for specific date
                    if (logs.size() == 0) {
                        request.setAttribute("message", "No activity logs found for the selected date.");
                    } else {
                        request.setAttribute("activitylogs", logs);
                    }
                } else {
                    logs = logDAO.fetchSpecificUserLogs(user.getUserID());
                    request.setAttribute("activitylogs", logs); // Fallback to all logs if no date is specified
                }
            } catch (SQLException e) {
                request.setAttribute("error", "Error retrieving activity logs: " + e.getMessage());
                e.printStackTrace();
            }
            request.getRequestDispatcher("viewactivitylogs.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp"); // Redirect to login page if no user is found in session
        }
    }

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
