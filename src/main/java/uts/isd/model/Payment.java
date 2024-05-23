package uts.isd.model;
import java.time.LocalDateTime;

public class Payment {
    private int paymentId;
    private int orderId;
    private double amount;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private String creditCardDetails;

    public Payment(int paymentId, int orderId, double amount, LocalDateTime paymentDate, String paymentMethod, String creditCardDetails) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.creditCardDetails = creditCardDetails;
    }

    // Getters and Setters
    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getCreditCardDetails() { return creditCardDetails; }
    public void setCreditCardDetails(String creditCardDetails) { this.creditCardDetails = creditCardDetails; }
}
