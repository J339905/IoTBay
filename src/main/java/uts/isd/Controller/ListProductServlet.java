package uts.isd.Controller; 

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uts.isd.model.Product;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.ProductDAO;
import uts.isd.model.dao.UserDAO;
import uts.isd.model.dao.logDAO;

@WebServlet(name = "ListProductServlet", urlPatterns = {"/ListProductServlet"})
public class ListProductServlet extends HttpServlet {
    
    private DBConnector db;
    private ProductDAO dao;  // Use 'dao' instead of 'productDao' to match initialization
    private UserDAO userDAO;
    private logDAO logDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector();  // Initialize the DBConnector.
            Connection conn = db.openConnection();  // Open a connection
            dao = new ProductDAO(conn);  // Initialize ProductDAO correctly
            userDAO = new UserDAO(conn);  // Initialize UserDAO
            logDAO = new logDAO(conn);  // Initialize logDAO
            // Removed the line that incorrectly tries to instantiate LoginServlet
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Fetch the list of products from the database using the correctly named DAO
            ArrayList<Product> productList = dao.getAllProducts();
            // Set the product list as a request attribute
            request.setAttribute("productList", productList);
            // Forward the request to the JSP page
            request.getRequestDispatcher("/productlist.jsp").forward(request, response);
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
