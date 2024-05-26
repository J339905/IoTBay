package uts.isd.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.UserDAO;

public class ConnServlet extends HttpServlet implements ServletContextListener {

    private DBConnector db;
    private Connection conn;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            db = new DBConnector();
            conn = db.openConnection();
            event.getServletContext().setAttribute("dbConnection", conn);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        try {
            UserDAO userDAO = new UserDAO(conn);
            session.setAttribute("userDAO", userDAO);
        } catch (SQLException e) {
            System.out.print(e);
        }
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    @Override
    public void destroy() {
        try {
            if (db != null) {
                db.closeConnection();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
