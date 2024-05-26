package uts.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import uts.isd.model.Payment;
import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.PaymentDAO;
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
    private PaymentDAO paymentDAO;

    public DAOTest() throws ClassNotFoundException, SQLException {
        connector = new DBConnector();
        conn = connector.openConnection();
        userDAO = new UserDAO(conn);
        logDAO = new logDAO(conn);
        productDAO = new ProductDAO(conn);
        orderDAO = new OrderDAO(conn);
        paymentDAO = new PaymentDAO(conn);
    }

    @Test
    public void testConnection() throws SQLException {
        assertNotNull(conn);
    }

    @Test
    public void testSelectAllTables() throws SQLException {
        List<User> users = userDAO.readAllUsers();
        assertTrue(users.size() > 0);
    }

    @Test
    public void testCreateUser() throws SQLException {
        userDAO.createUser("John", "Doe", "john.doe@example.com", 1234567890, "password123", "Male", "Customer");
    }

    @Test
    public void testCreatePayment() throws SQLException {
        Payment payment = new Payment(0, "John Doe", "1234567812345678", 123, "12/25", 100.0, LocalDate.now().toString());
        paymentDAO.createPayment(payment);
        
        List<Payment> payments = paymentDAO.getAllPayments();
        assertTrue(payments.stream().anyMatch(p -> p.getCardHolderName().equals("John Doe")));
    }

    @Test
    public void testReadAllPayments() throws SQLException {
        List<Payment> payments = paymentDAO.getAllPayments();
        assertNotNull(payments);
        assertTrue(payments.size() > 0);
    }

    @Test
    public void testUpdatePayment() throws SQLException {
        Payment payment = new Payment(0, "Jane Doe", "8765432187654321", 321, "11/24", 200.0, LocalDate.now().toString());
        paymentDAO.createPayment(payment);

        List<Payment> payments = paymentDAO.getAllPayments();
        Payment paymentToUpdate = payments.stream().filter(p -> p.getCardHolderName().equals("Jane Doe")).findFirst().orElse(null);
        assertNotNull(paymentToUpdate);

        paymentToUpdate.setCardHolderName("Updated Jane Doe");
        paymentDAO.updatePayment(paymentToUpdate);

        Payment updatedPayment = paymentDAO.searchPaymentsById(paymentToUpdate.getPaymentId()).get(0);
        assertEquals("Updated Jane Doe", updatedPayment.getCardHolderName());
    }

    @Test
    public void testDeletePayment() throws SQLException {
        Payment payment = new Payment(0, "Mark Doe", "1122334455667788", 456, "10/23", 150.0, LocalDate.now().toString());
        paymentDAO.createPayment(payment);

        List<Payment> payments = paymentDAO.getAllPayments();
        Payment paymentToDelete = payments.stream().filter(p -> p.getCardHolderName().equals("Mark Doe")).findFirst().orElse(null);
        assertNotNull(paymentToDelete);

        paymentDAO.deletePayment(paymentToDelete.getPaymentId());

        List<Payment> updatedPayments = paymentDAO.getAllPayments();
        assertFalse(updatedPayments.stream().anyMatch(p -> p.getCardHolderName().equals("Mark Doe")));
    }

    @Test
    public void testSearchPaymentsById() throws SQLException {
        Payment payment = new Payment(0, "Alice Doe", "4433221100112233", 789, "09/22", 300.0, LocalDate.now().toString());
        paymentDAO.createPayment(payment);

        List<Payment> payments = paymentDAO.getAllPayments();
        Payment paymentToSearch = payments.stream().filter(p -> p.getCardHolderName().equals("Alice Doe")).findFirst().orElse(null);
        assertNotNull(paymentToSearch);

        List<Payment> searchResults = paymentDAO.searchPaymentsById(paymentToSearch.getPaymentId());
        assertEquals(1, searchResults.size());
        assertEquals("Alice Doe", searchResults.get(0).getCardHolderName());
    }

    @BeforeEach
    public void setUp() throws SQLException {
        conn.createStatement().executeUpdate("DELETE FROM Payment WHERE cardHolderName LIKE 'Test%'");
    }

    @AfterEach
    public void tearDown() throws SQLException {
        conn.createStatement().executeUpdate("DELETE FROM Payment WHERE cardHolderName LIKE 'Test%'");
    }

    // Add other existing test methods here

}



 