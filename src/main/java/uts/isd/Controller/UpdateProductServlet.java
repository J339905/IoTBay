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

@WebServlet("/updateproduct")
public class UpdateProductServlet extends HttpServlet {
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
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = productDAO.getProductById(id);
            request.setAttribute("product", product);
            request.getRequestDispatcher("/admin/updateProduct.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID format.");
        } catch (SQLException e) {
            throw new ServletException("Error retrieving product details.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        StringBuilder errorMessage = new StringBuilder();
        
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name").trim();
        String category = request.getParameter("category");
        String description = request.getParameter("description").trim();
        String priceStr = request.getParameter("price").trim();
        String stockStr = request.getParameter("stock").trim();

        double price = 0;
        int stock = 0;
        boolean inputError = false;

        if (name.isEmpty()) {
            errorMessage.append("Name cannot be empty. ");
            inputError = true;
        }

        if (description.isEmpty()) {
            errorMessage.append("Description cannot be empty. ");
            inputError = true;
        }

        try {
            price = Double.parseDouble(priceStr);
            if (price < 0) errorMessage.append("Price cannot be negative. ");
        } catch (NumberFormatException e) {
            errorMessage.append("Price must be a valid number. ");
        }


        try {
            stock = Integer.parseInt(stockStr);
            if (stock < 0) errorMessage.append("Stock cannot be negative. ");
        } catch (NumberFormatException e) {
            errorMessage.append("Stock must be a valid number. ");
        }


        if (errorMessage.length() > 0) {
            session.setAttribute("updateProductError", errorMessage.toString());
            request.getRequestDispatcher("/admin/updateProduct.jsp").forward(request, response);
            return;
        }

        if (inputError) {
            request.setAttribute("errorMessage", errorMessage.toString());
            request.setAttribute("product", new Product(id, name, category, description, price, stock)); // Use submitted values
            request.getRequestDispatcher("/admin/updateProduct.jsp").forward(request, response);
            return;
        }

        Product product = new Product(id, name, category, description, price, stock);
        try {
            productDAO.updateProduct(product);
            session.removeAttribute("updateProductError");
            response.sendRedirect("/productlistadmin");
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error during product update.");
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
