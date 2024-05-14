package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uts.isd.model.Product;

public class ProductDAO {
    private Connection conn;
    private PreparedStatement readst;
    private String readQuery = "SELECT * from product";

    public ProductDAO(Connection connection) throws SQLException {
        this.conn = connection;
        this.conn.setAutoCommit(true);
        this.readst = connection.prepareStatement(readQuery);
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        ResultSet rs = readst.executeQuery();
        
        while (rs.next()) {
            int id = rs.getInt("productid");
            String name = rs.getString("productname");
            String category = rs.getString("productcategory");
            String description = rs.getString("productdescription");
            double price = rs.getDouble("productprice");
            int stock = rs.getInt("productstock");

            products.add(new Product(id, name, category, description, price, stock));
        }
        
        rs.close();
        return products;
    }

    // Close the connection when it's no longer needed
    public void close() throws SQLException {
        if (readst != null && !readst.isClosed()) {
            readst.close();
        }
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
