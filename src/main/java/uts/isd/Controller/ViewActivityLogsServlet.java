
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
import uts.isd.model.dao.logDAO;

public class ViewActivityLogsServlet extends HttpServlet {
    private DBConnector db;
    private logDAO logDAO;

    // Initialize the servlet and set up database connection and DAO
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector();
            Connection conn = db.openConnection();
            logDAO = new logDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    // Redirect GET requests to the POST handler
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    // Handle POST requests to view activity logs

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        session.removeAttribute("nologsErr");

        User user = (User) session.getAttribute("user");
        String date = request.getParameter("date");
        // This checks if user is logged in, and date is not null, the logs of that
        // specific date should be retrieved
        // otherwise, an error message will appear saying there are no logs
        if (user != null) {
            try {
                ArrayList<Logs> logs;
                if (date != null && !date.isEmpty()) {
                    logs = logDAO.fetchSpecificUserLogsByDate(user.getUserID(), date);
                    if (logs.size() == 0) {

                        session.setAttribute("nologsErr", "No activity logs found for the selected date.");
                        request.getRequestDispatcher("viewactivitylogs.jsp").include(request, response);
                        return;
                    } else {
                        request.setAttribute("activitylogs", logs);
                    }
                } else {
                    logs = logDAO.fetchSpecificUserLogs(user.getUserID());
                    request.setAttribute("activitylogs", logs);
                }
            } catch (SQLException e) {
                request.setAttribute("error", "Error retrieving activity logs: " + e.getMessage());
                e.printStackTrace();
            }
            request.getRequestDispatcher("viewactivitylogs.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
    //close database connection
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
