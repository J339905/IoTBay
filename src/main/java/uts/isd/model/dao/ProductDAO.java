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
    private String readQuery = "SELECT * FROM product";

    public ProductDAO(Connection connection) throws SQLException {
        this.conn = connection;
        this.conn.setAutoCommit(true);
        this.readst = connection.prepareStatement(readQuery);
    }

    // Method to get all products
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

    // Method to add a new product
    public void addProduct(Product product) throws SQLException {
        String insertQuery = "INSERT INTO product (productname, productcategory, productdescription, productprice, productstock) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement insertst = conn.prepareStatement(insertQuery);

        insertst.setString(1, product.getProductname());
        insertst.setString(2, product.getProductcategory());
        insertst.setString(3, product.getProductdescription());
        insertst.setDouble(4, product.getProductprice());
        insertst.setInt(5, product.getProductstock());

        insertst.executeUpdate();
        insertst.close();
    }

    // Method to update an existing product
    public void updateProduct(Product product) throws SQLException {
        String updateQuery = "UPDATE product SET productname = ?, productcategory = ?, productdescription = ?, productprice = ?, productstock = ? WHERE productid = ?";
        PreparedStatement updatest = conn.prepareStatement(updateQuery);

        updatest.setString(1, product.getProductname());
        updatest.setString(2, product.getProductcategory());
        updatest.setString(3, product.getProductdescription());
        updatest.setDouble(4, product.getProductprice());
        updatest.setInt(5, product.getProductstock());
        updatest.setInt(6, product.getProductid());

        updatest.executeUpdate();
        updatest.close();
    }

    // Method to delete a product
    public void deleteProduct(int productid) throws SQLException {
        String deleteQuery = "DELETE FROM product WHERE productid = ?";
        PreparedStatement deletest = conn.prepareStatement(deleteQuery);
        
        deletest.setInt(1, productid);
        deletest.executeUpdate();
        deletest.close();
    }

    // Method to search products by name and type
    public List<Product> searchProducts(String name, String type) throws SQLException {
        List<Product> products = new ArrayList<>();
        String searchQuery = "SELECT * FROM product WHERE productname LIKE ? AND productcategory LIKE ?";
        PreparedStatement searchst = conn.prepareStatement(searchQuery);

        searchst.setString(1, "%" + name + "%");
        searchst.setString(2, "%" + type + "%");
        
        ResultSet rs = searchst.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("productid");
            String productName = rs.getString("productname");
            String category = rs.getString("productcategory");
            String description = rs.getString("productdescription");
            double price = rs.getDouble("productprice");
            int stock = rs.getInt("productstock");

            products.add(new Product(id, productName, category, description, price, stock));
        }

        rs.close();
        searchst.close();
        return products;
    }

    // Method to close resources
    public void close() throws SQLException {
        if (readst != null && !readst.isClosed()) {
            readst.close();
        }
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
