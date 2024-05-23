package uts.isd.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import uts.isd.model.Payment;

public class PaymentDAO {
    private Connection conn;

    public PaymentDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertPayment(Payment payment) throws SQLException {
        String query = "INSERT INTO Payment (orderID, amount, paymentDate, paymentMethod, creditCardDetails) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, payment.getOrderId());
        stmt.setDouble(2, payment.getAmount());
        stmt.setTimestamp(3, Timestamp.valueOf(payment.getPaymentDate()));
        stmt.setString(4, payment.getPaymentMethod());
        stmt.setString(5, payment.getCreditCardDetails());
        return stmt.executeUpdate() > 0;
    }

    public List<Payment> listPaymentsByUserId(int userId) throws SQLException {
        String query = "SELECT p.* FROM Payment p JOIN `Order` o ON p.orderID = o.OrderID WHERE o.UserID = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        List<Payment> payments = new ArrayList<>();
        while (rs.next()) {
            payments.add(new Payment(
                rs.getInt("paymentID"),
                rs.getInt("orderID"),
                rs.getDouble("amount"),
                rs.getTimestamp("paymentDate").toLocalDateTime(),
                rs.getString("paymentMethod"),
                rs.getString("creditCardDetails")
            ));
        }
        rs.close();
        stmt.close();
        return payments;
    }

    public Payment getPaymentById(int paymentId) throws SQLException {
        String query = "SELECT * FROM Payment WHERE paymentID = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, paymentId);
        ResultSet rs = stmt.executeQuery();
        Payment payment = null;
        if (rs.next()) {
            payment = new Payment(
                rs.getInt("paymentID"),
                rs.getInt("orderID"),
                rs.getDouble("amount"),
                rs.getTimestamp("paymentDate").toLocalDateTime(),
                rs.getString("paymentMethod"),
                rs.getString("creditCardDetails")
            );
        }
        rs.close();
        stmt.close();
        return payment;
    }

    public boolean updatePayment(Payment payment) throws SQLException {
        String query = "UPDATE Payment SET orderID = ?, amount = ?, paymentDate = ?, paymentMethod = ?, creditCardDetails = ? WHERE paymentID = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, payment.getOrderId());
        stmt.setDouble(2, payment.getAmount());
        stmt.setTimestamp(3, Timestamp.valueOf(payment.getPaymentDate()));
        stmt.setString(4, payment.getPaymentMethod());
        stmt.setString(5, payment.getCreditCardDetails());
        stmt.setInt(6, payment.getPaymentId());
        return stmt.executeUpdate() > 0;
    }

    public boolean deletePayment(int paymentId) throws SQLException {
        String query = "DELETE FROM Payment WHERE paymentID = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, paymentId);
        return stmt.executeUpdate() > 0;
    }
}
