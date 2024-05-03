package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import uts.isd.model.Product;




public class ProductDAO {
private PreparedStatement readst;
private String readQuery = "SELECT ProductID, ProductName, ProductType, ProductDescription, ProductPrice, ProductStock  from Product";
private Connection connection;
// private String insertQuery = "SELECT S, FirstName, LastName from Account";


public ProductDAO(Connection connection) throws SQLException{
    connection.setAutoCommit(true);
    readst = connection.prepareStatement(readQuery);
}


public ArrayList<Product> fetchProduct() throws SQLException{
    ResultSet rs = readst.executeQuery();
    ArrayList<Product> products = new ArrayList<Product>();
   
    while(rs.next()){
    int productId = rs.getInt(1);
    String ProductType = rs.getString(3);
    String ProductName = rs.getString(2);
    int ProductStock = rs.getInt(6);
    double ProductPrice = rs.getDouble(5);
    String ProductDescription = rs.getString(4);
   
    Product p = new Product(productId, ProductName, ProductType, ProductDescription, ProductPrice, ProductStock);
   


System.out.println(ProductName + " " + ProductType);

products.add(p);
    }
    return products;
}

}
