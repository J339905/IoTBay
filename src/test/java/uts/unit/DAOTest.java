package uts.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import uts.isd.model.Logs;
import uts.isd.model.Product;
import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.UserDAO;
import uts.isd.model.dao.logDAO;
import uts.isd.model.dao.ProductDAO;

public class DAOTest {

    private DBConnector connector;
    private Connection conn;
    private UserDAO userDAO;
    private ProductDAO productDAO;
    private logDAO logDAO;

    public DAOTest() throws ClassNotFoundException, SQLException {
        connector = new DBConnector();
        conn = connector.openConnection();
        userDAO = new UserDAO(conn);
        logDAO = new logDAO(conn);
        productDAO = new ProductDAO(conn);
    }

    @Test
    public void testConnection() throws SQLException {
        assertNotNull(conn);
    }

    @BeforeEach
    public void setUp() throws SQLException {
        // Ensure only test-specific products are cleaned up before each test to avoid conflicts
        conn.createStatement().executeUpdate("DELETE FROM product WHERE productname LIKE 'TestProduct%'");
    }

    @Test
    public void testCreateIoTDevice() throws SQLException {
        User staffUser = userDAO.findUser("staff@gmail.com", "staff");
        assertNotNull(staffUser);

        Product product1 = new Product(0, "TestProduct1", "Type1", "Description1", 100.0, 10);
        productDAO.addProduct(product1);

        Product product2 = new Product(0, "TestProduct2", "Type2", "Description2", 200.0, 20);
        productDAO.addProduct(product2);

        // Verify that the products were created
        List<Product> products = productDAO.getAllProducts();
        assertTrue(products.stream().anyMatch(p -> p.getProductname().equals("TestProduct1")));
        assertTrue(products.stream().anyMatch(p -> p.getProductname().equals("TestProduct2")));
    }

    @Test
    public void testReadIoTDevices() throws SQLException {
        // Add a sample product
        Product product1 = new Product(0, "TestProduct1", "Type1", "Description1", 100.0, 10);
        productDAO.addProduct(product1);

        // Retrieve the product
        List<Product> products = productDAO.getAllProducts();
        assertNotNull(products);
        assertTrue(products.stream().anyMatch(p -> p.getProductname().equals("TestProduct1")));
    }

    @Test
    public void testUpdateIoTDevice() throws SQLException {
        // Add a sample product
        Product product = new Product(0, "TestProduct1", "Type1", "Description1", 100.0, 10);
        productDAO.addProduct(product);

        // Get the product ID after adding
        int productId = productDAO.getAllProducts().stream()
                .filter(p -> p.getProductname().equals("TestProduct1"))
                .findFirst()
                .orElseThrow(() -> new SQLException("Product not found"))
                .getProductid();

        // Update the product
        Product updatedProduct = new Product(productId, "UpdatedTestProduct1", "UpdatedType", "UpdatedDescription", 150.0, 15);
        productDAO.updateProduct(updatedProduct);

        // Verify the update
        Product retrievedProduct = productDAO.getProductById(productId);
        assertEquals("UpdatedTestProduct1", retrievedProduct.getProductname());
        assertEquals("UpdatedType", retrievedProduct.getProductcategory());
        assertEquals("UpdatedDescription", retrievedProduct.getProductdescription());
        assertEquals(150.0, retrievedProduct.getProductprice());
        assertEquals(15, retrievedProduct.getProductstock());
    }

    @Test
    public void testDeleteIoTDevice() throws SQLException {
        // Add a sample product
        Product product = new Product(0, "TestProduct1", "Type1", "Description1", 100.0, 10);
        productDAO.addProduct(product);

        // Get the product ID after adding
        int productId = productDAO.getAllProducts().stream()
                .filter(p -> p.getProductname().equals("TestProduct1"))
                .findFirst()
                .orElseThrow(() -> new SQLException("Product not found"))
                .getProductid();

        // Delete the product
        productDAO.deleteProduct(productId);

        // Verify the deletion
        List<Product> products = productDAO.getAllProducts();
        assertFalse(products.stream().anyMatch(p -> p.getProductid() == productId));
    }

    @Test
    public void testSearchIoTDevices() throws SQLException {
        // Add sample products
        productDAO.addProduct(new Product(0, "TestProduct1", "Type1", "Description1", 100.0, 10));
        productDAO.addProduct(new Product(0, "TestProduct2", "Type2", "Description2", 200.0, 20));

        // Search by name
        List<Product> searchResults = productDAO.searchProducts("TestProduct1", "");
        assertEquals(1, searchResults.size());
        assertEquals("TestProduct1", searchResults.get(0).getProductname());

        // Search by category
        searchResults = productDAO.searchProducts("", "Type2");
        assertEquals(1, searchResults.size());
        assertEquals("TestProduct2", searchResults.get(0).getProductname());

        // Search by name and category
        searchResults = productDAO.searchProducts("TestProduct1", "Type1");
        assertEquals(1, searchResults.size());
        assertEquals("TestProduct1", searchResults.get(0).getProductname());
    }
}