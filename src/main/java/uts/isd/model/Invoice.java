package uts.isd.model;

import java.time.LocalDateTime;

public class Invoice {
    private int invoiceId;
    private int orderId;
    private int amount;
    private LocalDateTime paymentDate;

    // Constructor
    public Invoice(int invoiceId, int orderId, int amount, LocalDateTime paymentDate) {
        this.invoiceId = invoiceId;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    // Getters and setters
    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
