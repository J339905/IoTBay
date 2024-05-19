package uts.isd.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import uts.isd.model.Product;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.ProductDAO;

@WebServlet("/productlistadmin")
public class ListProductAdminServlet extends HttpServlet {
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
        String name = request.getParameter("name"); // Retrieve name filter from request
        String category = request.getParameter("category"); // Retrieve category filter from request
        try {
            List<Product> products;
            // Search for products by name and/or category, or retrieve all products if no filters are provided
            if (name != null || category != null) {
                products = productDAO.searchProducts(name != null ? name : "", category != null ? category : "");
            } else {
                products = productDAO.getAllProducts();
            }
            request.setAttribute("products", products); // Set products attribute for the request
            request.getRequestDispatcher("/admin/productlist_admin.jsp").forward(request, response); // Forward request to JSP
        } catch (SQLException e) {
            throw new ServletException("Error retrieving products", e);
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
