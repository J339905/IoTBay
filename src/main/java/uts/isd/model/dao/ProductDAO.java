package uts.isd.model.dao;

import java.sql.*;
import java.util.ArrayList;
import uts.isd.model.Product;

public class ProductDAO {
    private Connection conn;

    public ProductDAO(Connection connection) {
        this.conn = connection;
    }

    // Method to fetch all products
    public ArrayList<Product> getAllProducts() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Product product = new Product(
                    rs.getInt("ProductID"),
                    rs.getString("ProductName"),
                    rs.getString("ProductCategory"),
                    rs.getString("ProductDescription"),
                    rs.getDouble("ProductPrice"),
                    rs.getInt("ProductStock")
                );
                products.add(product);
            }
        }
        return products;
    }
}
