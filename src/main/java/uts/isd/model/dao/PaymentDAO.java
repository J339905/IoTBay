package main.java.uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uts.isd.model.Payment;

public class PaymentDAO {
    private Connection conn;
    private PreparedStatement fetchUserPaymentsStmt;
    private PreparedStatement fetchUserPaymentsByDateStmt;
    private PreparedStatement addPaymentStmt;
    private PreparedStatement deletePaymentStmt;
    private PreparedStatement updatePaymentStmt;

    private String fetchUserPaymentsQuery = "SELECT * FROM Payment WHERE orderID IN (SELECT OrderID FROM frilab.Order WHERE UserID = ?)";
    private String fetchUserPaymentsByDateQuery = "SELECT * FROM Payment WHERE orderID IN (SELECT OrderID FROM frilab.Order WHERE UserID = ?) AND paymentDate = ?";
    private String addPaymentQuery = "INSERT INTO Payment (orderID, amount, paymentMethod, paymentDate) VALUES (?, ?, ?, ?)";
    private String deletePaymentQuery = "DELETE FROM Payment WHERE paymentID = ?";
    private String updatePaymentQuery = "UPDATE Payment SET amount = ?, paymentMethod = ?, paymentDate = ? WHERE paymentID = ?";

    public PaymentDAO(Connection connection) throws SQLException {
        this.conn = connection;
        fetchUserPaymentsStmt = conn.prepareStatement(fetchUserPaymentsQuery);
        fetchUserPaymentsByDateStmt = conn.prepareStatement(fetchUserPaymentsByDateQuery);
        addPaymentStmt = conn.prepareStatement(addPaymentQuery);
        deletePaymentStmt = conn.prepareStatement(deletePaymentQuery);
        updatePaymentStmt = conn.prepareStatement(updatePaymentQuery);
    }

    public List<Payment> fetchUserPayments(int userId) throws SQLException {
        List<Payment> payments = new ArrayList<>();
        fetchUserPaymentsStmt.setInt(1, userId);
        ResultSet rs = fetchUserPaymentsStmt.executeQuery();

        while (rs.next()) {
            int paymentId = rs.getInt("paymentID");
            int orderId = rs.getInt("orderID");
            double amount = rs.getDouble("amount");
            String paymentMethod = rs.getString("paymentMethod");
            String paymentDate = rs.getString("paymentDate");

            Payment payment = new Payment(paymentId, orderId, amount, paymentMethod, paymentDate);
            payments.add(payment);
        }

        rs.close();
        return payments;
    }

    public List<Payment> fetchUserPaymentsByDate(int userId, String date) throws SQLException {
        List<Payment> payments = new ArrayList<>();
        fetchUserPaymentsByDateStmt.setInt(1, userId);
        fetchUserPaymentsByDateStmt.setString(2, date);
        ResultSet rs = fetchUserPaymentsByDateStmt.executeQuery();

        while (rs.next()) {
            int paymentId = rs.getInt("paymentID");
            int orderId = rs.getInt("orderID");
            double amount = rs.getDouble("amount");
            String paymentMethod = rs.getString("paymentMethod");
            String paymentDate = rs.getString("paymentDate");

            Payment payment = new Payment(paymentId, orderId, amount, paymentMethod, paymentDate);
            payments.add(payment);
        }

        rs.close();
        return payments;
    }

    public void addPayment(Payment payment) throws SQLException {
        addPaymentStmt.setInt(1, payment.getOrderId());
        addPaymentStmt.setDouble(2, payment.getAmount());
        addPaymentStmt.setString(3, payment.getPaymentMethod());
        addPaymentStmt.setString(4, payment.getPaymentDate());
        addPaymentStmt.executeUpdate();
    }

    public void deletePayment(int paymentId) throws SQLException {
        deletePaymentStmt.setInt(1, paymentId);
        deletePaymentStmt.executeUpdate();
    }

    public void updatePayment(Payment payment) throws SQLException {
        updatePaymentStmt.setDouble(1, payment.getAmount());
        updatePaymentStmt.setString(2, payment.getPaymentMethod());
        updatePaymentStmt.setString(3, payment.getPaymentDate());
        updatePaymentStmt.setInt(4, payment.getPaymentId());
        updatePaymentStmt.executeUpdate();
    }

    public void close() throws SQLException {
        if (fetchUserPaymentsStmt != null) {
            fetchUserPaymentsStmt.close();
        }
        if (fetchUserPaymentsByDateStmt != null) {
            fetchUserPaymentsByDateStmt.close();
        }
        if (addPaymentStmt != null) {
            addPaymentStmt.close();
        }
        if (deletePaymentStmt != null) {
            deletePaymentStmt.close();
        }
        if (updatePaymentStmt != null) {
            updatePaymentStmt.close();
        }
    }
}
