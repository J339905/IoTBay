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
    private PreparedStatement createStmt;
    private PreparedStatement updateStmt;
    private PreparedStatement deleteStmt;
    private PreparedStatement maxIdStmt;

    private String readQuery = "SELECT * from product";
    private String createQuery = "INSERT INTO product (productid, productname, productcategory, productdescription, productprice, productstock) VALUES (?, ?, ?, ?, ?, ?)";
    private String updateQuery = "UPDATE product SET productname = ?, productcategory = ?, productdescription = ?, productprice = ?, productstock = ? WHERE productid = ?";
    private String deleteQuery = "DELETE FROM product WHERE productid = ?";
    private String maxIdQuery = "SELECT MAX(productid) AS maxid FROM product";

    public ProductDAO(Connection connection) throws SQLException {
        this.conn = connection;
        connection.setAutoCommit(true);
        readst = connection.prepareStatement(readQuery);
        createStmt = connection.prepareStatement(createQuery);
        updateStmt = connection.prepareStatement(updateQuery);
        deleteStmt = connection.prepareStatement(deleteQuery);
        maxIdStmt = connection.prepareStatement(maxIdQuery);
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

    public void addProduct(Product product) throws SQLException {
        int nextId = getNextProductId();
        createStmt.setInt(1, nextId);
        createStmt.setString(2, product.getProductname());
        createStmt.setString(3, product.getProductcategory());
        createStmt.setString(4, product.getProductdescription());
        createStmt.setDouble(5, product.getProductprice());
        createStmt.setInt(6, product.getProductstock());
        createStmt.executeUpdate();
    }

    public void updateProduct(Product product) throws SQLException {
        updateStmt.setString(1, product.getProductname());
        updateStmt.setString(2, product.getProductcategory());
        updateStmt.setString(3, product.getProductdescription());
        updateStmt.setDouble(4, product.getProductprice());
        updateStmt.setInt(5, product.getProductstock());
        updateStmt.setInt(6, product.getProductid());
        updateStmt.executeUpdate();
    }
    
    public Product getProductById(int productId) throws SQLException {
        String query = "SELECT * FROM product WHERE productid = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, productId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("productid");
            String name = rs.getString("productname");
            String category = rs.getString("productcategory");
            String description = rs.getString("productdescription");
            double price = rs.getDouble("productprice");
            int stock = rs.getInt("productstock");
            rs.close();
            return new Product(id, name, category, description, price, stock);
        } else {
            rs.close();
            return null;  // Return null if no product is found
        }
    }

    public List<Product> searchProducts(String name, String category) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product WHERE productname LIKE ? AND productcategory LIKE ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, "%" + name + "%");
        stmt.setString(2, "%" + category + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("productid");
            String productName = rs.getString("productname");
            String productCategory = rs.getString("productcategory");
            String description = rs.getString("productdescription");
            double price = rs.getDouble("productprice");
            int stock = rs.getInt("productstock");

            products.add(new Product(id, productName, productCategory, description, price, stock));
        }

        
        rs.close();
        return products;
    }
    
    
    
    
    public void deleteProduct(int productId) throws SQLException {
        deleteStmt.setInt(1, productId);
        deleteStmt.executeUpdate();
    }

    private int getNextProductId() throws SQLException {
        ResultSet rs = maxIdStmt.executeQuery();
        int nextId = 1; // Default to 1 if no products are found

        if (rs.next()) {
            nextId = rs.getInt("maxid") + 1;
        }
        
        rs.close();
        return nextId;
    }
}
