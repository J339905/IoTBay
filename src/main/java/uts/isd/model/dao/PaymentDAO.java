package uts.isd.model.dao;

import uts.isd.model.Payment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {
    private Connection conn;

    public PaymentDAO(Connection conn) {
        this.conn = conn;
    }

    // Create a new payment record
    public void createPayment(Payment payment) throws SQLException {
        String query = "INSERT INTO Payment (orderID, amount, paymentDate, paymentMethod) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, payment.getOrderID());
        stmt.setDouble(2, payment.getAmount());
        stmt.setDate(3, new java.sql.Date(payment.getPaymentDate().getTime()));
        stmt.setString(4, payment.getPaymentMethod());
        stmt.executeUpdate();
    }

    // Read a payment record by paymentID
    public Payment getPaymentById(int paymentID) throws SQLException {
        String query = "SELECT * FROM Payment WHERE paymentID=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, paymentID);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Payment(
                rs.getInt("paymentID"),
                rs.getInt("orderID"),
                rs.getDouble("amount"),
                rs.getDate("paymentDate"),
                rs.getString("paymentMethod")
            );
        }
        return null;
    }

    // Read all payments
    public List<Payment> getAllPayments() throws SQLException {
        String query = "SELECT * FROM Payment";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        List<Payment> payments = new ArrayList<>();
        while (rs.next()) {
            payments.add(new Payment(
                rs.getInt("paymentID"),
                rs.getInt("orderID"),
                rs.getDouble("amount"),
                rs.getDate("paymentDate"),
                rs.getString("paymentMethod")
            ));
        }
        return payments;
    }

    // Update a payment record
    public void updatePayment(Payment payment) throws SQLException {
        String query = "UPDATE Payment SET orderID=?, amount=?, paymentDate=?, paymentMethod=? WHERE paymentID=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, payment.getOrderID());
        stmt.setDouble(2, payment.getAmount());
        stmt.setDate(3, new java.sql.Date(payment.getPaymentDate().getTime()));
        stmt.setString(4, payment.getPaymentMethod());
        stmt.setInt(5, payment.getPaymentID());
        stmt.executeUpdate();
    }

    // Delete a payment record by paymentID
    public void deletePayment(int paymentID) throws SQLException {
        String query = "DELETE FROM Payment WHERE paymentID=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, paymentID);
        stmt.executeUpdate();
    }
}
