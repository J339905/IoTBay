package uts.isd.Controller;

import java.io.IOException;
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

@WebServlet(name = "ListProductServlet", urlPatterns = {"/ListProductServlet"})
public class ListProductServlet extends HttpServlet {
    
    private DBConnector db;
    private ProductDAO dao;

    @Override
    public void init() {
        db = new DBConnector();
        try {
            dao = new ProductDAO(db.openConnection());
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ArrayList<Product> productList = dao.getAllProducts();
            request.setAttribute("productList", productList);
            request.getRequestDispatcher("/ProductList.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void destroy() {
        try {
            db.closeConnection();
        } catch (SQLException e) {
            System.err.println("Failed to close database connection.");
        }
    }
}
