package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import uts.isd.model.Product;

public class ProductDAO {
    private PreparedStatement readst;
    private String readQuery = "SELECT ProductID, ProductName, ProductCategory, ProductDescription, ProductPrice, ProductStock FROM Product";
    private Connection conn; // Added instance variable to store connection
    

    public ProductDAO(Connection connection) throws SQLException {
        connection.setAutoCommit(true);
        readst = conn.prepareStatement(readQuery);
    }
    public ArrayList<Product> fetchProduct() throws SQLException {
        ResultSet rs = null;
        ArrayList<Product> products = new ArrayList<>();

        try {
            rs = readst.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt(1);
                String productName = rs.getString(2);
                String productCategory = rs.getString(3);
                String productDescription = rs.getString(4);
                double productPrice = rs.getDouble(5);
                int productStock = rs.getInt(6);

                Product p = new Product(productId, productName, productCategory, productDescription, productPrice, productStock);
                products.add(p);
            }
        } catch (SQLException e) {
            // Log SQLException
            e.printStackTrace();
            throw e; // Rethrow the exception
        } finally {
            // Close resources in finally block to ensure they are always closed
            if (rs != null) {
                rs.close();
            }
        }

        return products;
    }


    }

