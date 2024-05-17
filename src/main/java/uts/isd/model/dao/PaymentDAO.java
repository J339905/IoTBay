package main.java.uts.isd.model.dao;

import uts.isd.model.Payment;
import uts.isd.model.DBConnector;

import java.sql.*;
import java.util.ArrayList;

public class PaymentDAO {
    private Connection connection;

    public PaymentDAO() {
        connection = DBConnector.getConnection();
    }

    public void addPayment(Payment payment) throws SQLException {
        String query = "INSERT INTO payment (orderID, amount, paymentDate, PaymentMethod) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, payment.getOrderId());
        stmt.setInt(2, payment.getAmount());
        stmt.setTimestamp(3, payment.getPaymentDate());
        stmt.setString(4, payment.getPaymentMethod());
        stmt.executeUpdate();
    }

    public ArrayList<Payment> getPaymentsByOrderId(int orderId) throws SQLException {
        ArrayList<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM payment WHERE orderID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, orderId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Payment payment = new Payment(
                rs.getInt("paymentID"),
                rs.getInt("orderID"),
                rs.getInt("amount"),
                rs.getTimestamp("paymentDate"),
                rs.getString("PaymentMethod")
            );
            payments.add(payment);
        }
        return payments;
    }
}
