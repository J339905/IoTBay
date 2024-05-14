package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import uts.isd.model.Product;

public class ProductDAO {
    private PreparedStatement readst;
    private String readQuery = "SELECT ProductID, ProductName, ProductCategory, ProductDescription, ProductPrice, ProductStock FROM Product";
    private Connection conn;

    public ProductDAO(Connection connection) throws SQLException {
        this.conn = connection;
        conn.setAutoCommit(true);
        readst = conn.prepareStatement(readQuery);
        System.out.println("ProductDAO initialized with query: " + readQuery);
    }

    public List<Product> getAllProducts() throws SQLException {
        ResultSet rs = null;
        List<Product> products = new ArrayList<>();
        System.out.println("Executing query to fetch all products.");

        try {
            rs = readst.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                String productCategory = rs.getString("ProductCategory");
                String productDescription = rs.getString("ProductDescription");
                double productPrice = rs.getDouble("ProductPrice");
                int productStock = rs.getInt("ProductStock");

                Product p = new Product(productId, productName, productCategory, productDescription, productPrice, productStock);
                products.add(p);
                System.out.println("Fetched product: " + productName);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching products: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
                System.out.println("ResultSet closed.");
            }
        }

        return products;
    }
}
