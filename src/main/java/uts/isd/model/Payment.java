package uts.isd.model;

import java.time.LocalDateTime;

public class Payment {

    private int paymentId;
    private int orderId;
    private int amount;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private String cvv;
    private String nameOnCard;
    private LocalDateTime expiryDate;
    private LocalDateTime datePaid;
    private String transactionStatus;
    private String confirmationNumber;
    private int userId; // Assuming each payment is linked to a specific user

    // Constructor
    public Payment(int paymentId, int orderId, int amount, LocalDateTime paymentDate, String paymentMethod,
                   String cvv, String nameOnCard, LocalDateTime expiryDate, LocalDateTime datePaid,
                   String transactionStatus, String confirmationNumber, int userId) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.cvv = cvv;
        this.nameOnCard = nameOnCard;
        this.expiryDate = expiryDate;
        this.datePaid = datePaid;
        this.transactionStatus = transactionStatus;
        this.confirmationNumber = confirmationNumber;
        this.userId = userId;
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCVV() {
        return cvv;
    }

    public void setCVV(String cvv) {
        this.cvv = cvv;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDateTime getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(LocalDateTime datePaid) {
        this.datePaid = datePaid;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
