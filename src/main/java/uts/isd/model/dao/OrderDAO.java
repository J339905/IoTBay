package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import uts.isd.model.Order;

public class OrderDAO {
    private Connection conn;
    private PreparedStatement insertStmt;
    private PreparedStatement updateStmt;
    private PreparedStatement deleteStmt;
    private PreparedStatement selectAllStmt;
    private PreparedStatement selectStmt;

    public OrderDAO(Connection connection) throws SQLException {
        this.conn = connection;
        conn.setAutoCommit(true);
        initStatements();
    }

    private void initStatements() throws SQLException {
        String insertQuery = "INSERT INTO `Order` (UserID, Order_Date, Order_Status, Delivery_address, Quantity) VALUES (?, ?, ?, ?, ?)";
        String updateQuery = "UPDATE `Order` SET UserID = ?, Order_Date = ?, Order_Status = ?, Delivery_address = ?, Quantity = ? WHERE OrderID = ?";
        String deleteQuery = "DELETE FROM `Order` WHERE OrderID = ?";
        String selectAllQuery = "SELECT * FROM `Order`";
        String selectQuery = "SELECT * FROM `Order` WHERE OrderID = ?";

        insertStmt = conn.prepareStatement(insertQuery);
        updateStmt = conn.prepareStatement(updateQuery);
        deleteStmt = conn.prepareStatement(deleteQuery);
        selectAllStmt = conn.prepareStatement(selectAllQuery);
        selectStmt = conn.prepareStatement(selectQuery);
    }

    public boolean insertOrder(Order order) throws SQLException {
        insertStmt.setInt(1, order.getUserId());
        insertStmt.setObject(2, order.getOrderDate());
        insertStmt.setString(3, order.getOrderStatus());
        insertStmt.setString(4, order.getDeliveryAddress());
        insertStmt.setString(5, order.getQuantity());
        return insertStmt.executeUpdate() > 0;
    }

    public ArrayList<Order> listAllOrders() throws SQLException {
        ArrayList<Order> listOrder = new ArrayList<>();
        try (ResultSet resultSet = selectAllStmt.executeQuery()) {
            while (resultSet.next()) {
                listOrder.add(extractOrderFromResultSet(resultSet));
            }
        }
        return listOrder;
    }
    
    public ArrayList<Order> listAllOrders(String sortBy, String sortOrder) throws SQLException {
        String query = "SELECT * FROM `Order` ORDER BY " + sortBy + " " + sortOrder;
        PreparedStatement stmt = conn.prepareStatement(query);
        ArrayList<Order> listOrder = new ArrayList<>();
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            listOrder.add(extractOrderFromResultSet(resultSet));
        }
        resultSet.close();
        return listOrder;
    }

    public ArrayList<Order> listOrdersByUserId(int userId, String sortBy, String sortOrder) throws SQLException {
        String query = "SELECT * FROM `Order` WHERE UserID = ? ORDER BY " + sortBy + " " + sortOrder;
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, userId);
        ArrayList<Order> listOrder = new ArrayList<>();
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            listOrder.add(new Order(
                resultSet.getInt("OrderID"),
                resultSet.getInt("UserID"),
                resultSet.getObject("Order_Date", LocalDateTime.class),
                resultSet.getString("Order_Status"),
                resultSet.getString("Delivery_address"),
                resultSet.getString("Quantity")
            ));
        }
        resultSet.close();
        stmt.close();
        return listOrder;
    }
    
    
    

    public boolean updateOrder(Order order) throws SQLException {
        updateStmt.setInt(1, order.getUserId());
        updateStmt.setObject(2, order.getOrderDate());
        updateStmt.setString(3, order.getOrderStatus());
        updateStmt.setString(4, order.getDeliveryAddress());
        updateStmt.setString(5, order.getQuantity());
        updateStmt.setInt(6, order.getOrderId());
        return updateStmt.executeUpdate() > 0;
    }

    public boolean deleteOrder(int orderId) throws SQLException {
        deleteStmt.setInt(1, orderId);
        return deleteStmt.executeUpdate() > 0;
    }

    public Order getOrder(int id) throws SQLException {
        selectStmt.setInt(1, id);
        try (ResultSet resultSet = selectStmt.executeQuery()) {
            if (resultSet.next()) {
                return extractOrderFromResultSet(resultSet);
            }
        }
        return null;
    }

    private Order extractOrderFromResultSet(ResultSet rs) throws SQLException {
        return new Order(
            rs.getInt("OrderID"),
            rs.getInt("UserID"),
            rs.getObject("Order_Date", LocalDateTime.class),
            rs.getString("Order_Status"),
            rs.getString("Delivery_address"),
            rs.getString("Quantity")
        );
    }
}
