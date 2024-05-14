package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import uts.isd.model.Order;

public class OrderDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public OrderDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    public void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    public void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
    public boolean insertOrder(Order order) throws SQLException {
        String sql = "INSERT INTO `Order` (UserID, Order_Date, Order_Status, Delivery_address, Quantity) VALUES (?, ?, ?, ?, ?)";
        connect();
        
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, order.getUserId());
        statement.setObject(2, order.getOrderDate());
        statement.setString(3, order.getOrderStatus());
        statement.setString(4, order.getDeliveryAddress());
        statement.setString(5, order.getQuantity());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public ArrayList<Order> listAllOrders() throws SQLException {
        ArrayList<Order> listOrder = new ArrayList<>();
        String sql = "SELECT * FROM `Order`";
        
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            int orderId = resultSet.getInt("OrderID");
            int userId = resultSet.getInt("UserID");
            LocalDateTime orderDate = resultSet.getObject("Order_Date", LocalDateTime.class);
            String orderStatus = resultSet.getString("Order_Status");
            String deliveryAddress = resultSet.getString("Delivery_address");
            String quantity = resultSet.getString("Quantity");

            Order order = new Order(orderId, userId, orderDate, orderStatus, deliveryAddress, quantity);
            listOrder.add(order);
        }
        
        resultSet.close();
        statement.close();
        disconnect();
        
        return listOrder;
    }

    public boolean updateOrder(Order order) throws SQLException {
        String sql = "UPDATE `Order` SET UserID = ?, Order_Date = ?, Order_Status = ?, Delivery_address = ?, Quantity = ? WHERE OrderID = ?";
        connect();
        
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, order.getUserId());
        statement.setObject(2, order.getOrderDate());
        statement.setString(3, order.getOrderStatus());
        statement.setString(4, order.getDeliveryAddress());
        statement.setString(5, order.getQuantity());
        statement.setInt(6, order.getOrderId());
        
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public boolean deleteOrder(Order order) throws SQLException {
        String sql = "DELETE FROM `Order` WHERE OrderID = ?";
        
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, order.getOrderId());
        
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public Order getOrder(int id) throws SQLException {
        Order order = null;
        String sql = "SELECT * FROM `Order` WHERE OrderID = ?";
        
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
        
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            int orderId = resultSet.getInt("OrderID");
            int userId = resultSet.getInt("UserID");
            LocalDateTime orderDate = resultSet.getObject("Order_Date", LocalDateTime.class);
            String orderStatus = resultSet.getString("Order_Status");
            String deliveryAddress = resultSet.getString("Delivery_address");
            String quantity = resultSet.getString("Quantity");

            order = new Order(orderId, userId, orderDate, orderStatus, deliveryAddress, quantity);
        }
        
        resultSet.close();
        statement.close();
        disconnect();
        return order;
    }
}
