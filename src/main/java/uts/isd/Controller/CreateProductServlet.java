package uts.isd.Controller;  // Correct package name should be all lowercase.

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

@WebServlet(name = "CreateProductServlet", urlPatterns = {"/CreateProductServlet"})
public class CreateProductServlet extends HttpServlet {
    
    private DBConnector db;
    private ProductDAO dao;

    @Override
    public void init() throws ServletException {
        try {
            db = new DBConnector();
            dao = new ProductDAO(db.openConnection());
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException("DB connection error", ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        
        Product product = new Product(0, name, type, description, price, stock);
        
        try {
            dao.addProduct(product);
            response.sendRedirect("ProductList.jsp"); // Redirect to the product list page
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error creating product: " + e.getMessage());
            request.getRequestDispatcher("CreateProduct.jsp").forward(request, response); // Return to the creation page if there's an error
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
