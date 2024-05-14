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
    private String readQuery = "SELECT * from product";
    private String createQuery = "INSERT INTO product (productname, productcategory, productdescription, productprice, productstock) VALUES (?, ?, ?, ?, ?)";
    private String updateQuery = "UPDATE product SET productname = ?, productcategory = ?, productdescription = ?, productprice = ?, productstock = ? WHERE productid = ?";
    private String deleteQuery = "DELETE FROM product WHERE productid = ?";

    public ProductDAO(Connection connection) throws SQLException {
        this.conn = connection;
        connection.setAutoCommit(true);
        readst = connection.prepareStatement(readQuery);
        createStmt = connection.prepareStatement(createQuery);
        updateStmt = connection.prepareStatement(updateQuery);
        deleteStmt = connection.prepareStatement(deleteQuery);
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
        createStmt.setString(1, product.getProductname());
        createStmt.setString(2, product.getProductcategory());
        createStmt.setString(3, product.getProductdescription());
        createStmt.setDouble(4, product.getProductprice());
        createStmt.setInt(5, product.getProductstock());
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

    public void deleteProduct(int productId) throws SQLException {
        deleteStmt.setInt(1, productId);
        deleteStmt.executeUpdate();
    }
}
