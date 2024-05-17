package main.java.uts.isd.model.dao;

import uts.isd.model.Payment;
import java.sql.*;
import java.util.ArrayList;

public class PaymentDAO {

    private Connection conn;

    public PaymentDAO() throws SQLException {
        conn = DBConnector.getConnection();
    }

    public void createPayment(Payment payment) throws SQLException {
        String query = "INSERT INTO payments (userId, paymentMethod, amount, paymentStatus, paymentDate) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, payment.getUserId());
        statement.setString(2, payment.getPaymentMethod());
        statement.setDouble(3, payment.getAmount());
        statement.setString(4, payment.getPaymentStatus());
        statement.setDate(5, new java.sql.Date(payment.getPaymentDate().getTime()));
        statement.executeUpdate();
    }

    public ArrayList<Payment> getPaymentsByUserId(int userId) throws SQLException {
        String query = "SELECT * FROM payments WHERE userId = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, userId);
        ResultSet rs = statement.executeQuery();

        ArrayList<Payment> payments = new ArrayList<>();
        while (rs.next()) {
            Payment payment = new Payment();
            payment.setPaymentId(rs.getInt("paymentId"));
            payment.setUserId(rs.getInt("userId"));
            payment.setPaymentMethod(rs.getString("paymentMethod"));
            payment.setAmount(rs.getDouble("amount"));
            payment.setPaymentStatus(rs.getString("paymentStatus"));
            payment.setPaymentDate(rs.getDate("paymentDate"));
            payments.add(payment);
        }
        return payments;
    }
}
