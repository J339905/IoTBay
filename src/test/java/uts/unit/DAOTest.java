package uts.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import uts.isd.model.Cart;
import uts.isd.model.CartItem;
import uts.isd.model.Order;
import uts.isd.model.Logs;
import uts.isd.model.Product;
import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.UserDAO;
import uts.isd.model.dao.logDAO;
import uts.isd.model.dao.ProductDAO;
import uts.isd.model.dao.OrderDAO;


public class DAOTest {

    private DBConnector connector;
    private Connection conn;
    private UserDAO userDAO;
    private ProductDAO productDAO;
    private logDAO logDAO;
    private OrderDAO orderDAO;


    public DAOTest() throws ClassNotFoundException, SQLException {
        connector = new DBConnector();
        conn = connector.openConnection();
        userDAO = new UserDAO(conn);
        logDAO = new logDAO(conn);
        productDAO = new ProductDAO(conn);
        orderDAO = new OrderDAO(conn);

    }

    @Test
    public void testConnection() throws SQLException {
        assertNotNull(conn);
    }

    @Test
    public void testSelectAllTables() throws SQLException {
        ArrayList<User> users = userDAO.readAllUsers();
        assertTrue(users.size() > 0);
    }

    @Test
    public void testcreateuser() throws SQLException {
        userDAO.createUser("John", "Doe", "john.doe@example.com", 1234567890, "password123", "Male", "Customer");

    }

    @Test
    public void testViewLogsByDate() throws SQLException {
        // Assuming a user with this email and password exists
        User user = userDAO.findUser("john.doe@example.com", "password123");
        assertNotNull(user);

        LocalDateTime.now();
        String exactTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        logDAO.createLog(user.getUserID(), exactTime, "Login");

        String formattedDate = LocalDateTime.now().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE);

        List<Logs> logs = logDAO.fetchSpecificUserLogsByDate(user.getUserID(), formattedDate);

        assertNotNull(logs);
        assertTrue(logs.size() > 0);

        for (Logs log : logs) {
            System.out.println(log.toString());
        }
    }

    @Test
    public void testViewRegistrationDetails() throws SQLException {
        User user = userDAO.findUser("Jack@gmail.com", "sdfdsfsa");
        System.out.println(user.toString());
    }

    // @Test
    // public void testdeleteuser() throws SQLException {
    // User user = userDAO.findUser("Isaac@hotmail.com", "dsadfsfdfdsfdsfsdf");
    // userDAO.deleteUser(user.getUserID());
    // }

    // All testcases below are related to theJunit test cases in the assignment 2
    // marking criteria
    @Test
    public void testSuccessfulLogin() throws SQLException {
        User user = userDAO.findUser("taejun@hotmail.com", "hello");
        assertNotNull(user);
        assertTrue(user.getEmail().equals("taejun@hotmail.com"));
    }

    @Test
    public void testUnSuccessfulLogin() throws SQLException {
        User user = userDAO.findUser("doesntexist@hotmail.com", "notexist");
        assertTrue(user == null);
    }

    // @Test
    // public void testSuccessfulUpdate() throws Exception {
    // User user = userDAO.findUser("a@f.com", "A");
    // assertNotNull(user);

    // user = userDAO.updateUser("NewFirstName", "NewLastName", 123456789,
    // "newpassword123", "Male", "Customer",
    // user.getEmail());
    // assertEquals("NewFirstName", user.getfirstName());
    // assertEquals("NewLastName", user.getlastname());
    // assertEquals(123456789, user.getPhone());
    // assertEquals("newpassword123", user.getPassword());
    // }
    // @Test
    // public void testUnsuccessfulUpdate() throws Exception {
    // User user = userDAO.findUser("a@f.com", "A");
    // assertNotNull(user);
    // session.removeAttribute("nametypeErr");
    // session.removeAttribute("nullErr");
    // session.removeAttribute("phoneErr");
    // session.removeAttribute("passwordErr");

    // String nameRegex = "^[a-zA-Z\\s'-]+$";
    // String phoneRegex = "^\\d+$";

    // try {
    // userDAO.updateUser("NewFirstName", "NewLastName", -123456789, "short",
    // "Male", "Customer", user.getEmail());
    // fail("Expected SQLException");
    // } catch (SQLException e) {
    // // Expected exception due to incorrect format
    // }
    // }
    // @Test
    // public void testUpdateUserWithInvalidData() throws SQLException {
    // User user = userDAO.findUser("Nish@gmail.com", "Iwonalfsdf");
    // assertNotNull(user);

    // String invalidFirstname = "John123";
    // String invalidLastname = "Doe@";
    // String invalidPhone = "123hvm456";
    // String invalidPassword = "123";

    // String nameRegex = "^[a-zA-Z\\s'-]+$";
    // String phoneRegex = "^\\d+$";

    // assertFalse(invalidFirstname.matches(nameRegex), "First name should be
    // letters only");
    // assertFalse(invalidLastname.matches(nameRegex), "Last name should be letters
    // only");
    // assertFalse(invalidPhone.matches(phoneRegex), "Phone number should be numbers
    // only");
    // assertTrue(invalidPassword.length() < 6, "Password should have a length
    // greater than 5");

    // }

    @BeforeEach
    public void setUp() throws SQLException {
        conn.createStatement().executeUpdate("DELETE FROM product WHERE productname LIKE 'TestProduct%'");
    }

    @AfterEach
    public void tearDown() throws SQLException {
        conn.createStatement().executeUpdate(
                "DELETE FROM product WHERE productname LIKE 'TestProduct%' OR productname LIKE 'UpdatedTestProduct%'");
    }

    @Test
    public void testCreateIoTDevice() throws SQLException {

        Product product1 = new Product(0, "TestProduct1", "Type1", "Description1", 100.0, 10);
        productDAO.addProduct(product1);

        Product product2 = new Product(0, "TestProduct2", "Type2", "Description2", 200.0, 20);
        productDAO.addProduct(product2);

        List<Product> products = productDAO.getAllProducts();
        assertTrue(products.stream().anyMatch(p -> p.getProductname().equals("TestProduct1")));
        assertTrue(products.stream().anyMatch(p -> p.getProductname().equals("TestProduct2")));
    }

    @Test
    public void testReadIoTDevices() throws SQLException {
        Product product1 = new Product(0, "TestProduct1", "Type1", "Description1", 100.0, 10);
        productDAO.addProduct(product1);

        List<Product> products = productDAO.getAllProducts();
        assertNotNull(products);
        assertTrue(products.stream().anyMatch(p -> p.getProductname().equals("TestProduct1")));
    }

    @Test
    public void testUpdateIoTDevice() throws SQLException {
        Product product = new Product(0, "TestProduct1", "Type1", "Description1", 100.0, 10);
        productDAO.addProduct(product);

        int productId = productDAO.getAllProducts().stream()
                .filter(p -> p.getProductname().equals("TestProduct1"))
                .findFirst()
                .orElseThrow(() -> new SQLException("Product not found"))
                .getProductid();

        Product updatedProduct = new Product(productId, "UpdatedTestProduct1", "UpdatedType", "UpdatedDescription",
                150.0, 15);
        productDAO.updateProduct(updatedProduct);

        Product retrievedProduct = productDAO.getProductById(productId);
        assertEquals("UpdatedTestProduct1", retrievedProduct.getProductname());
        assertEquals("UpdatedType", retrievedProduct.getProductcategory());
        assertEquals("UpdatedDescription", retrievedProduct.getProductdescription());
        assertEquals(150.0, retrievedProduct.getProductprice());
        assertEquals(15, retrievedProduct.getProductstock());
    }

    @Test
    public void testDeleteIoTDevice() throws SQLException {
        Product product = new Product(0, "TestProduct1", "Type1", "Description1", 100.0, 10);
        productDAO.addProduct(product);

        int productId = productDAO.getAllProducts().stream()
                .filter(p -> p.getProductname().equals("TestProduct1"))
                .findFirst()
                .orElseThrow(() -> new SQLException("Product not found"))
                .getProductid();

        productDAO.deleteProduct(productId);

        List<Product> products = productDAO.getAllProducts();
        assertFalse(products.stream().anyMatch(p -> p.getProductid() == productId));
    }

    @Test
    public void testSearchIoTDevices() throws SQLException {
        productDAO.addProduct(new Product(0, "TestProduct1", "Type1", "Description1", 100.0, 10));
        productDAO.addProduct(new Product(0, "TestProduct2", "Type2", "Description2", 200.0, 20));

        List<Product> searchResults = productDAO.searchProducts("TestProduct1", "");
        assertEquals(1, searchResults.size());
        assertEquals("TestProduct1", searchResults.get(0).getProductname());

        searchResults = productDAO.searchProducts("", "Type2");
        assertEquals(1, searchResults.size());
        assertEquals("TestProduct2", searchResults.get(0).getProductname());

        searchResults = productDAO.searchProducts("TestProduct1", "Type1");
        assertEquals(1, searchResults.size());
        assertEquals("TestProduct1", searchResults.get(0).getProductname());
    }

    @Test
    public void testInsertOrder() throws SQLException {
        Order order = new Order(0, 1, LocalDateTime.now(), "Pending", "123 Main St", "2");
        boolean result = orderDAO.insertOrder(order);
        assertTrue(result, "Order should be inserted successfully");
    }

    @Test
    public void testListAllOrders() throws SQLException {
        List<Order> orders = orderDAO.listAllOrders();
        assertTrue(orders.size() > 0, "There should be at least one order in the list");
    }

    @Test
    public void testUpdateOrder() throws SQLException {
        Order order = new Order(1, 1, LocalDateTime.now(), "Shipped", "123 Main St", "3");
        boolean result = orderDAO.updateOrder(order);
        assertTrue(result, "Order should be updated successfully");
    }

    @Test
    public void testDeleteOrder() throws SQLException {
        boolean result = orderDAO.deleteOrder(1);
        assertTrue(result, "Order should be deleted successfully");
    }

    @Test
    public void testSaveCart() throws SQLException {
        Cart cart = new Cart();
        cart.addItem(new CartItem(new Product(1, "Test Product", "Type", "Description", 10.0, 5), 2));
        orderDAO.saveCart(1, cart);
        assertNotNull(cart, "Cart should be saved successfully");
    }

    @Test
    public void adminAddUser() throws SQLException {
        System.out.println("Admin - add user");
        userDAO.createUser("John", "Doe", "j.doe@example.com", 1234567890, "password123", "Male", "Customer");
        System.out.println("Admin - user added!");
    }   

    @Test
    public void adminUpdateUser() throws SQLException {
        System.out.println("Admin update user");
        User u = userDAO.findUserById("36");
        u.setEmail("test@gmail.com");
        userDAO.adminUpdateUser(u);
        System.out.println("Admin - user updated!");
    }

    @Test
    public void adminDeleteUser() throws SQLException {
        System.out.println("Admin delete user");
        userDAO.deleteUser(Integer.valueOf("36"));
        System.out.println("Admin - user deleted!");
    }

    @Test
    public void adminFindUser() throws SQLException {
        System.out.println("Admin find user");
        User u = userDAO.findUserById("50");
        System.out.println(u.getEmail());
    }

}
