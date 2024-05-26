package uts.isd.model;

public class Payment {
    private int paymentId;
    private String cardHolderName;
    private String creditCardNumber;
    private int cvv; // Ensure cvv is of type int
    private String expiryDate;
    private double amount;
    private String date;

    // Default constructor
    public Payment() {}

    // Constructor with all fields
    public Payment(int paymentId, String cardHolderName, String creditCardNumber, int cvv, String expiryDate, double amount, String date) {
        this.paymentId = paymentId;
        this.cardHolderName = cardHolderName;
        this.creditCardNumber = creditCardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.amount = amount;
        this.date = date;
    }

    // Constructor without paymentId (used for creating new payments)
    public Payment(String cardHolderName, String creditCardNumber, int cvv, String expiryDate, double amount, String date) {
        this.cardHolderName = cardHolderName;
        this.creditCardNumber = creditCardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.amount = amount;
        this.date = date;
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
