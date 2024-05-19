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
        String query = "INSERT INTO Payment (orderID, amount, paymentDate, paymentMethod, cardNumber, expiryDate, cvv) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, payment.getOrderID());
        stmt.setDouble(2, payment.getAmount());
        stmt.setDate(3, new java.sql.Date(payment.getPaymentDate().getTime()));
        stmt.setString(4, payment.getPaymentMethod());
        stmt.setString(5, payment.getCardNumber());
        stmt.setString(6, payment.getExpiryDate());
        stmt.setString(7, payment.getCvv());
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

    // Search for payments based on payment ID and date
    public List<Payment> searchPayments(int userId, String paymentID, String paymentDate) throws SQLException {
        List<Payment> payments = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM Payment WHERE userId = ?");
        List<Object> parameters = new ArrayList<>();
        parameters.add(userId);
    
        if (paymentID != null && !paymentID.isEmpty()) {
            query.append(" AND paymentID = ?");
            parameters.add(Integer.parseInt(paymentID));
        }
    
        if (paymentDate != null && !paymentDate.isEmpty()) {
            query.append(" AND DATE(paymentDate) = ?");
            parameters.add(java.sql.Date.valueOf(paymentDate));
        }
    
        PreparedStatement ps = conn.prepareStatement(query.toString());
        for (int i = 0; i < parameters.size(); i++) {
            ps.setObject(i + 1, parameters.get(i));
        }
    
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int paymentID = rs.getInt("paymentID");
            int orderID = rs.getInt("orderID");
            double amount = rs.getDouble("amount");
            String paymentMethod = rs.getString("paymentMethod");
            Date paymentDateObj = rs.getDate("paymentDate");
            String cardNumber = rs.getString("cardNumber");
            String expiryDate = rs.getString("expiryDate");
            String cvv = rs.getString("cvv");
    
            Payment payment = new Payment(paymentID, orderID, amount, paymentDateObj, paymentMethod, cardNumber, expiryDate, cvv);
            payments.add(payment);
        }
    
        return payments;
    }
    
}
