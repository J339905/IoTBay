package uts.isd.Controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.ProductDAO;

@WebServlet(name = "DeleteProductServlet", urlPatterns = {"/DeleteProductServlet"})
public class DeleteProductServlet extends HttpServlet {
    
    private DBConnector db;
    private ProductDAO dao;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector();
            dao = new ProductDAO(db.openConnection());
        } catch (ClassNotFoundException | SQLException ex) {
            throw new ServletException("DB connection error", ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        try {
            dao.deleteProduct(id);
            response.sendRedirect("ProductList.jsp"); // Redirect to the product list page
        } catch (SQLException e) {
            // Log error
            e.printStackTrace();
            // Optionally add error messages to session or request
            request.getSession().setAttribute("error", "Unable to delete product.");
            response.sendRedirect("ProductList.jsp"); // Redirect to the list page if there's an error
        }
    }

    @Override
    public void destroy() {
        try {
            if (db != null) {
                db.closeConnection();
            }
        } catch (SQLException e) {
            System.err.println("Failed to close database connection.");
        }
    }
}
