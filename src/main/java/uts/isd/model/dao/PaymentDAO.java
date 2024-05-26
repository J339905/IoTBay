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

    public void createPayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO Payment (cardHolderName, cardNumber, cvv, expiryDate, amount, paymentDate) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, payment.getCardHolderName());
        stmt.setString(2, payment.getCreditCardNumber());
        stmt.setInt(3, payment.getCvv());
        stmt.setString(4, payment.getExpiryDate());
        stmt.setDouble(5, payment.getAmount());
        stmt.setString(6, payment.getDate());
        stmt.executeUpdate();
    }

    public List<Payment> getAllPayments() throws SQLException {
        String sql = "SELECT * FROM Payment";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<Payment> payments = new ArrayList<>();
        while (rs.next()) {
            int paymentId = rs.getInt("paymentId");
            String cardHolderName = rs.getString("cardHolderName");
            String creditCardNumber = rs.getString("cardNumber");
            int cvv = rs.getInt("cvv");
            String expiryDate = rs.getString("expiryDate");
            double amount = rs.getDouble("amount");
            String date = rs.getString("paymentDate");

            Payment payment = new Payment(paymentId, cardHolderName, creditCardNumber, cvv, expiryDate, amount, date);
            payments.add(payment);
        }
        return payments;
    }

    public List<Payment> searchPaymentsById(int paymentId) throws SQLException {
        String sql = "SELECT * FROM Payment WHERE paymentId = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, paymentId);
        ResultSet rs = stmt.executeQuery();

        List<Payment> payments = new ArrayList<>();
        while (rs.next()) {
            String cardHolderName = rs.getString("cardHolderName");
            String creditCardNumber = rs.getString("cardNumber");
            int cvv = rs.getInt("cvv");
            String expiryDate = rs.getString("expiryDate");
            double amount = rs.getDouble("amount");
            String date = rs.getString("paymentDate");

            Payment payment = new Payment(paymentId, cardHolderName, creditCardNumber, cvv, expiryDate, amount, date);
            payments.add(payment);
        }
        return payments;
    }

    public List<Payment> searchPaymentsByDate(String date) throws SQLException {
        String sql = "SELECT * FROM Payment WHERE paymentDate = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, date);
        ResultSet rs = stmt.executeQuery();

        List<Payment> payments = new ArrayList<>();
        while (rs.next()) {
            int paymentId = rs.getInt("paymentId");
            String cardHolderName = rs.getString("cardHolderName");
            String creditCardNumber = rs.getString("cardNumber");
            int cvv = rs.getInt("cvv");
            String expiryDate = rs.getString("expiryDate");
            double amount = rs.getDouble("amount");

            Payment payment = new Payment(paymentId, cardHolderName, creditCardNumber, cvv, expiryDate, amount, date);
            payments.add(payment);
        }
        return payments;
    }

    public void updatePayment(Payment payment) throws SQLException {
        String sql = "UPDATE Payment SET cardHolderName = ?, cardNumber = ?, cvv = ?, expiryDate = ?, amount = ?, paymentDate = ? WHERE paymentId = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, payment.getCardHolderName());
        stmt.setString(2, payment.getCreditCardNumber());
        stmt.setInt(3, payment.getCvv());
        stmt.setString(4, payment.getExpiryDate());
        stmt.setDouble(5, payment.getAmount());
        stmt.setString(6, payment.getDate());
        stmt.setInt(7, payment.getPaymentId());
        stmt.executeUpdate();
    }

    public void deletePayment(int paymentId) throws SQLException {
        String sql = "DELETE FROM Payment WHERE paymentId = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, paymentId);
        stmt.executeUpdate();
    }
}