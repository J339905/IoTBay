package uts.isd.Controller; 

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uts.isd.model.Product;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.ProductDAO;

@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/UpdateProductServlet"})
public class UpdateProductServlet extends HttpServlet {
    
    private DBConnector db;
    private ProductDAO dao;

    @Override
    public void init() throws ServletException {
        try {
            db = new DBConnector();
            dao = new ProductDAO(db.openConnection());
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException("Initialization of DBConnector failed.", ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        
        Product product = new Product(id, name, type, description, price, stock);
        
        try {
            dao.updateProduct(product);
            response.sendRedirect("ProductList.jsp"); // Redirect to the product list page
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error updating product: " + e.getMessage());
            request.getRequestDispatcher("UpdateProduct.jsp").forward(request, response); // Stay on the update page if there's an error
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