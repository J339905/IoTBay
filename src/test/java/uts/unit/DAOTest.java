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

import org.junit.jupiter.api.Test;

import uts.isd.model.Logs;
import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.UserDAO;
import uts.isd.model.dao.logDAO;
import uts.isd.model.dao.ProductDAO;

public class DAOTest {

    private DBConnector connector;
    private Connection conn;
    private UserDAO userDAO;
    private ProductDAO ProductDAO;
    private logDAO logDAO;

    public DAOTest() throws ClassNotFoundException, SQLException {
        connector = new DBConnector();
        conn = connector.openConnection();
        userDAO = new UserDAO(conn);
        logDAO = new logDAO(conn);
        ProductDAO = new ProductDAO(conn);
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
    @Test
    public void testUpdateUserWithInvalidData() throws SQLException {
        User user = userDAO.findUser("Nish@gmail.com", "Iwonalfsdf");
        assertNotNull(user);

        String invalidFirstname = "John123";
        String invalidLastname = "Doe@";
        String invalidPhone = "123hvm456";
        String invalidPassword = "123";

        String nameRegex = "^[a-zA-Z\\s'-]+$";
        String phoneRegex = "^\\d+$";

        assertFalse(invalidFirstname.matches(nameRegex), "First name should be letters only");
        assertFalse(invalidLastname.matches(nameRegex), "Last name should be letters only");
        assertFalse(invalidPhone.matches(phoneRegex), "Phone number should be numbers only");
        assertTrue(invalidPassword.length() < 6, "Password should have a length greater than 5");

    }
}
