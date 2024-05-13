package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import uts.isd.model.Product;


public class ProductDAO {
private PreparedStatement readst;
private String readQuery = "SELECT ProductID, ProductName, ProductCategory, ProductDescription, ProductPrice, ProductStock from Product";
// private String insertQuery = "SELECT S, FirstName, LastName from Account";
// read query, try select * instead of certain columns

public ProductDAO(Connection connection) throws SQLException{
    connection.setAutoCommit(true);
    readst = connection.prepareStatement(readQuery);
}


public ArrayList<Product> fetchProduct() throws SQLException{
    ResultSet rs = readst.executeQuery();
    ArrayList<Product> products = new ArrayList<Product>();
   
    while(rs.next()){
    int ProductID = rs.getInt(1);
    String ProductName = rs.getString(2);
    String ProductCategory = rs.getString(3);
    String ProductDescription = rs.getString(4);
    double ProductPrice = rs.getDouble(5);
    int ProductStock = rs.getInt(6);
   
    Product p = new Product(ProductID, ProductName, ProductCategory, ProductDescription, ProductPrice, ProductStock);
   


System.out.println(ProductName + " " + ProductCategory);

products.add(p);
    }
    return products;
}

}