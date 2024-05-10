package uts.isd.model;

import java.time.LocalDateTime;

public class Shipment {
    private int shipmentId;
    private int orderId;
    private int invoiceid;
    private LocalDateTime shipmentDate;
    private String shipmentStatus;

    public Shipment(int shipmentId, int invoiceid, int orderId, LocalDateTime shipmentDate, String shipmentStatus) {
        this.shipmentId = shipmentId;
        this.invoiceid = invoiceid;
        this.orderId = orderId;
        this.shipmentDate = shipmentDate;
        this.shipmentStatus = shipmentStatus;
    }

    public int getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public int getinvoiceId() {
        return invoiceid;
    }

    public void setinvoiceId(int invoiceid) {
        this.invoiceid = invoiceid;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(LocalDateTime shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }
}
