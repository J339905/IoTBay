package uts.isd.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.Product;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.ProductDAO;

@WebServlet("/createProduct")
public class CreateProductServlet extends HttpServlet {
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
        HttpSession session = request.getSession();

        // Remove any previous error messages
        session.removeAttribute("nameErr");
        session.removeAttribute("categoryErr");
        session.removeAttribute("descriptionErr");
        session.removeAttribute("priceErr");
        session.removeAttribute("stockErr");
        session.removeAttribute("nullErr");

        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");
        String stockStr = request.getParameter("stock");

        boolean hasError = false;

        if (name == null || name.trim().isEmpty() ||
            category == null || category.trim().isEmpty() ||
            description == null || description.trim().isEmpty() ||
            priceStr == null || priceStr.trim().isEmpty() ||
            stockStr == null || stockStr.trim().isEmpty()) {
            session.setAttribute("nullErr", "Please fill in all the fields given.");
            hasError = true;
        }

        double price = 0;
        int stock = 0;

        if (!hasError) {
            try {
                price = Double.parseDouble(priceStr);
                if (price <= 0) {
                    session.setAttribute("priceErr", "Price must be a positive number.");
                    hasError = true;
                }
            } catch (NumberFormatException e) {
                session.setAttribute("priceErr", "Invalid price format.");
                hasError = true;
            }

            try {
                stock = Integer.parseInt(stockStr);
                if (stock < 0) {
                    session.setAttribute("stockErr", "Stock must be a non-negative integer.");
                    hasError = true;
                }
            } catch (NumberFormatException e) {
                session.setAttribute("stockErr", "Invalid stock format.");
                hasError = true;
            }
        }

        if (hasError) {
            request.getRequestDispatcher("addProduct.jsp").include(request, response);
            return;
        }

        Product product = new Product(0, name, category, description, price, stock);
        try {
            productDAO.addProduct(product);
            response.sendRedirect("/listProductsAdmin");
        } catch (SQLException e) {
            throw new ServletException("Error creating product", e);
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
