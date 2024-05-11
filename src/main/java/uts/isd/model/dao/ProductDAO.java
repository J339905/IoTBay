package uts.isd.model.dao;

import java.sql.*;
import java.util.ArrayList;
import uts.isd.model.Product;

public class ProductDAO {
    private Connection conn;

    public ProductDAO(Connection connection) {
        this.conn = connection;
    }

    // CREATE
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO Product (ProductName, ProductType, ProductDescription, ProductPrice, ProductStock) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getProductType());
            ps.setString(3, product.getDescription());
            ps.setDouble(4, product.getPrice());
            ps.setInt(5, product.getQuantity());
            ps.executeUpdate();
        }
    }

    // READ
    public ArrayList<Product> getAllProducts() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("ProductID"),
                    rs.getString("ProductName"),
                    rs.getString("ProductType"),
                    rs.getString("ProductDescription"),
                    rs.getDouble("ProductPrice"),
                    rs.getInt("ProductStock")
                ));
            }
        }
        return products;
    }

    // UPDATE
    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE Product SET ProductName = ?, ProductType = ?, ProductDescription = ?, ProductPrice = ?, ProductStock = ? WHERE ProductID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getProductType());
            ps.setString(3, product.getDescription());
            ps.setDouble(4, product.getPrice());
            ps.setInt(5, product.getQuantity());
            ps.setInt(6, product.getProductid());
            ps.executeUpdate();
        }
    }

    // DELETE
    public void deleteProduct(int productId) throws SQLException {
        String sql = "DELETE FROM Product WHERE ProductID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ps.executeUpdate();
        }
    }
}
