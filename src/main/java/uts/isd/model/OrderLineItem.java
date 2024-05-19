package uts.isd.model;

// Represents a single line item within an order, detailing a specific product and its quantity
public class OrderLineItem {

    private int lineItemId;  // Unique identifier for the line item
    private int orderId;     // Identifier of the order to which this line item belongs
    private int productId;   // Identifier of the product being ordered
    private int quantity;    // Quantity of the product in this line item
    private double price;    // Price per unit of the product at the time of order

    // Constructor to initialize an order line item with all necessary details
    public OrderLineItem(int lineItemId, int orderId, int productId, int quantity, double price) {
        this.lineItemId = lineItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    // Returns the unique identifier of this line item
    public int getLineItemId() {
        return lineItemId;
    }

    // Sets the unique identifier for this line item
    public void setLineItemId(int lineItemId) {
        this.lineItemId = lineItemId;
    }

    // Returns the price per unit of the product
    public double getPrice() {
        return price;
    }

    // Sets the price per unit of the product
    public void setPrice(double price) {
        this.price = price;
    }

    // Returns the order ID to which this line item belongs
    public int getOrderId() {
        return orderId;
    }

    // Sets the order ID for this line item
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    // Returns the product ID of the product in this line item
    public int getProductId() {
        return productId;
    }

    // Sets the product ID for this line item
    public void setProductId(int productId) {
        this.productId = productId;
    }

    // Returns the quantity of the product in this line item
    public int getQuantity() {
        return quantity;
    }

    // Sets the quantity of the product in this line item
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
