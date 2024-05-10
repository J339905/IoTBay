package main.java.uts.isd.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import uts.isd.model.Order;
import uts.isd.model.OrderLineItem;

public class OrderDAO {
    private Connection conn;

    public OrderDAO(Connection connection) {
        this.conn = connection;
    }

    public void createOrder(Order order, List<OrderLineItem> lineItems) throws SQLException {
        String insertOrderSQL = "INSERT INTO Orders (userId, orderDate, status) VALUES (?, ?, ?)";
        PreparedStatement orderStmt = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS);

        try {
            conn.setAutoCommit(false); // Start transaction

            orderStmt.setInt(1, order.getUserId());
            orderStmt.setTimestamp(2, new Timestamp(order.getOrderDate().getTime()));
            orderStmt.setString(3, order.getStatus());
            int affectedRows = orderStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            try (ResultSet generatedKeys = orderStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setOrderId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }

            String insertLineItemSQL = "INSERT INTO OrderLineItems (orderId, productId, quantity, unitPrice) VALUES (?, ?, ?, ?)";
            try (PreparedStatement lineItemStmt = conn.prepareStatement(insertLineItemSQL)) {
                for (OrderLineItem item : lineItems) {
                    lineItemStmt.setInt(1, order.getOrderId());
                    lineItemStmt.setInt(2, item.getProductId());
                    lineItemStmt.setInt(3, item.getQuantity());
                    lineItemStmt.setDouble(4, item.getUnitPrice());
                    lineItemStmt.addBatch();
                }
                lineItemStmt.executeBatch();
            }

            conn.commit(); // Commit transaction
        } catch (SQLException ex) {
            conn.rollback(); // Rollback transaction on error
            throw ex;
        } finally {
            conn.setAutoCommit(true); // Restore auto-commit mode
        }
    }

    public Order getOrderById(int orderId) throws SQLException {
        String query = "SELECT * FROM Orders WHERE orderId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Order(
                        rs.getInt("orderId"),
                        rs.getInt("userId"),
                        rs.getTimestamp("orderDate"),
                        rs.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error retrieving order: " + e.getMessage(), e);
        }
        return null;
    }

    public void updateOrder(Order order) throws SQLException {
        String updateSQL = "UPDATE Orders SET userId=?, orderDate=?, status=? WHERE orderId=?";
        try (PreparedStatement stmt = conn.prepareStatement(updateSQL)) {
            stmt.setInt(1, order.getUserId());
            stmt.setTimestamp(2, new Timestamp(order.getOrderDate().getTime()));
            stmt.setString(3, order.getStatus());
            stmt.setInt(4, order.getOrderId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error updating order: " + e.getMessage(), e);
        }
    }

    public void cancelOrder(int orderId) throws SQLException {
        String updateSQL = "UPDATE Orders SET status='Cancelled' WHERE orderId=?";
        try (PreparedStatement stmt = conn.prepareStatement(updateSQL)) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error cancelling order: " + e.getMessage(), e);
        }
    }
}
