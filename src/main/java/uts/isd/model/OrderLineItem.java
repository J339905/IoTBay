package uts.isd.model;

public class OrderLineItem {

    private int lineItemId;
    private int orderId;
    private int productId;
    private int quantity;

    private double price;

    public OrderLineItem(int lineItemId, int orderId, int productId, int quantity, double price) {
        this.lineItemId = lineItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getLineItemId() {
        return lineItemId;
    }

    public void setLineItemId(int lineItemId) {
        this.lineItemId = lineItemId;
    }

    public double getprice() {
        return price;
    }

    public void setLineItemId(double price) {
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
