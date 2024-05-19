package uts.isd.model;
import java.time.LocalDateTime;

// Represents an order made by a user, containing details about the order
public class Order {

    private int orderId;             // Unique identifier for the order
    private int userId;              // User ID associated with the order
    private LocalDateTime orderDate; // Date and time when the order was placed
    private String orderStatus;      // Current status of the order (e.g., pending, shipped)
    private String deliveryAddress;  // Delivery address for the order
    private String quantity;         // Quantity of items ordered (stored as a string, consider changing to int if applicable)

    // Constructor to initialize an order with all necessary details
    public Order(int orderId, int userId, LocalDateTime orderDate, String orderStatus, String deliveryAddress, String quantity) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
        this.quantity = quantity;
    }

    // Returns the order ID
    public int getOrderId() {
        return orderId;
    }

    // Sets the order ID
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    // Returns the user ID associated with the order
    public int getUserId() {
        return userId;
    }

    // Sets the user ID for the order
    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Returns the date and time when the order was placed
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    // Sets the date and time for the order
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    // Returns the current status of the order
    public String getOrderStatus() {
        return orderStatus;
    }

    // Sets the status of the order
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    // Returns the delivery address for the order
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    // Sets the delivery address for the order
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    // Returns the quantity of items ordered
    public String getQuantity() {
        return quantity;
    }

    // Sets the quantity of items in the order
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
