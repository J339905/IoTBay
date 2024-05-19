package uts.isd.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.ProductDAO;

@WebServlet("/deleteproduct")
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id")); // Retrieve product ID from request
            productDAO.deleteProduct(id); // Delete product by ID
            response.sendRedirect("/productlistadmin");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID.");
        } catch (SQLException e) {
            throw new ServletException("Error deleting product", e);
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
