package uts.isd.model.dao;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import uts.isd.model.Product;

public class ProductDAO {
    private PreparedStatement readst;
    private String readQuery = "SELECT ProductID, ProductName, ProductCategory, ProductDescription, ProductPrice, ProductStock FROM Product";

    public ProductDAO(Connection connection) throws SQLException {
        connection.setAutoCommit(true);
        readst = connection.prepareStatement(readQuery);
    }

    public ArrayList<Product> fetchProduct() throws SQLException {
        ResultSet rs = readst.executeQuery();
        ArrayList<Product> products = new ArrayList<Product>();

        while (rs.next()) {
            int productID = rs.getInt("ProductID");
            String productName = rs.getString("ProductName");
            String productCategory = rs.getString("ProductCategory");
            String productDescription = rs.getString("ProductDescription");
            double productPrice = rs.getDouble("ProductPrice");
            int productStock = rs.getInt("ProductStock");

            Product p = new Product(productID, productName, productCategory, productDescription, productPrice, productStock);
            products.add(p);
        }
        return products;
    }
}
