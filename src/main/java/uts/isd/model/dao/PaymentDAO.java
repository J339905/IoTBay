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
        String sql = "INSERT INTO payments (cardHolderName, creditCardNumber, cvv, expiryDate, amount, date) VALUES (?, ?, ?, ?, ?, ?)";
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
        String sql = "SELECT * FROM payments";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<Payment> payments = new ArrayList<>();
        while (rs.next()) {
            int paymentId = rs.getInt("paymentId");
            String cardHolderName = rs.getString("cardHolderName");
            String creditCardNumber = rs.getString("creditCardNumber");
            int cvv = rs.getInt("cvv");
            String expiryDate = rs.getString("expiryDate");
            double amount = rs.getDouble("amount");
            String date = rs.getString("date");

            Payment payment = new Payment(paymentId, cardHolderName, creditCardNumber, cvv, expiryDate, amount, date);
            payments.add(payment);
        }
        return payments;
    }

    public List<Payment> searchPaymentsById(int paymentId) throws SQLException {
        String sql = "SELECT * FROM payments WHERE paymentId = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, paymentId);
        ResultSet rs = stmt.executeQuery();

        List<Payment> payments = new ArrayList<>();
        while (rs.next()) {
            String cardHolderName = rs.getString("cardHolderName");
            String creditCardNumber = rs.getString("creditCardNumber");
            int cvv = rs.getInt("cvv");
            String expiryDate = rs.getString("expiryDate");
            double amount = rs.getDouble("amount");
            String date = rs.getString("date");

            Payment payment = new Payment(paymentId, cardHolderName, creditCardNumber, cvv, expiryDate, amount, date);
            payments.add(payment);
        }
        return payments;
    }

    public List<Payment> searchPaymentsByDate(String date) throws SQLException {
        String sql = "SELECT * FROM payments WHERE date = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, date);
        ResultSet rs = stmt.executeQuery();

        List<Payment> payments = new ArrayList<>();
        while (rs.next()) {
            int paymentId = rs.getInt("paymentId");
            String cardHolderName = rs.getString("cardHolderName");
            String creditCardNumber = rs.getString("creditCardNumber");
            int cvv = rs.getInt("cvv");
            String expiryDate = rs.getString("expiryDate");
            double amount = rs.getDouble("amount");

            Payment payment = new Payment(paymentId, cardHolderName, creditCardNumber, cvv, expiryDate, amount, date);
            payments.add(payment);
        }
        return payments;
    }
}
