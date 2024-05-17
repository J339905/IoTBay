package uts.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import uts.isd.model.Cart;
import uts.isd.model.CartItem;
import uts.isd.model.Order;
import uts.isd.model.Product;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.OrderDAO;

public class DAOTest {

    private static DBConnector connector;
    private static Connection conn;
    private static OrderDAO orderDAO;

    @BeforeAll
    public static void setUp() throws ClassNotFoundException, SQLException {
        connector = new DBConnector();
        conn = connector.openConnection();
        orderDAO = new OrderDAO(conn);
    }

    @Test
    public void testConnection() throws SQLException {
        assertNotNull(conn, "Connection should be established");
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
}
