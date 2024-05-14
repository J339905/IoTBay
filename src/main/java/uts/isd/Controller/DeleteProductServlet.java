package uts.isd.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.ProductDAO;

public class DeleteProductServlet extends HttpServlet {
    private DBConnector db;
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector();
            Connection conn = db.openConnection();
            productDAO = new ProductDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (role != null && role.equals("staff")) {
            int id = Integer.parseInt(request.getParameter("id"));

            try {
                productDAO.deleteProduct(id);
                response.sendRedirect("listProducts");
            } catch (SQLException e) {
                throw new ServletException("Error deleting product", e);
            }
        } else {
            response.sendRedirect("unauthorized.jsp");
        }
    }

    @Override
    public void destroy() {
        try {
            if (productDAO != null) {
                productDAO.close();
            }
            if (db != null) {
                db.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
