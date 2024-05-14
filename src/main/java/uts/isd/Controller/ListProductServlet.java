package uts.isd.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import uts.isd.model.Product;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.ProductDAO;

@WebServlet(name = "ListProductServlet", urlPatterns = {"/ListProductServlet"})
public class ListProductServlet extends HttpServlet {

    private DBConnector db;
    private ProductDAO dao;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            db = new DBConnector();
            Connection conn = db.openConnection();
            dao = new ProductDAO(conn);
            System.out.println("Database connection and ProductDAO initialization successful.");
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("DBConnector initialization failed.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            System.out.println("No session found.");
        } else {
            System.out.println("Session found.");
        }

        try {
            List<Product> productList = dao.getAllProducts();
            System.out.println("Fetched products: " + productList.size());
            request.setAttribute("productList", productList);
            request.getRequestDispatcher("/productlist.jsp").forward(request, response);
            System.out.println("Product list forwarded to JSP.");
        } catch (SQLException e) {
            System.err.println("Error retrieving products: " + e.getMessage());
            throw new ServletException("Error retrieving products", e);
        }
    }

    @Override
    public void destroy() {
        try {
            if (db != null) {
                db.closeConnection();
                System.out.println("Database connection closed successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
