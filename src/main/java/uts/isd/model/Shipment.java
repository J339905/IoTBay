package uts.isd.model;

import java.time.LocalDateTime;

// Represents a shipment associated with an order
public class Shipment {
    private int shipmentId; // Unique identifier for the shipment
    private int orderId; // ID of the order this shipment is related to
    private int invoiceid; // ID of the invoice associated with this shipment
    private LocalDateTime shipmentDate; // Date when the shipment was dispatched
    private String shipmentStatus; // Current status of the shipment (e.g., In Transit, Delivered)

    // Constructor to initialize a Shipment object with necessary details
    public Shipment(int shipmentId, int invoiceid, int orderId, LocalDateTime shipmentDate, String shipmentStatus) {
        this.shipmentId = shipmentId;
        this.invoiceid = invoiceid;
        this.orderId = orderId;
        this.shipmentDate = shipmentDate;
        this.shipmentStatus = shipmentStatus;
    }

    // Getter for shipment ID
    public int getShipmentId() {
        return shipmentId;
    }

    // Setter for shipment ID
    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    // Getter for invoice ID
    public int getinvoiceId() {
        return invoiceid;
    }

    // Setter for invoice ID
    public void setinvoiceId(int invoiceid) {
        this.invoiceid = invoiceid;
    }

    // Getter for order ID
    public int getOrderId() {
        return orderId;
    }

    // Setter for order ID
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    // Getter for the shipment date
    public LocalDateTime getShipmentDate() {
        return shipmentDate;
    }

    // Setter for the shipment date
    public void setShipmentDate(LocalDateTime shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    // Getter for the shipment status
    public String getShipmentStatus() {
        return shipmentStatus;
    }

    // Setter for the shipment status
    public void setShipmentStatus(String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }
}
