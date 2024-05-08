package uts.isd.model;

public class Invoice{
    protected int invoiceid;
	protected int orderid;
	protected int amount;
	protected String paymentDate;

    
    public Invoice(int invoiceid, int orderid, int amount, String paymentDate) {
        this.invoiceid = invoiceid;
        this.orderid = orderid;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }
    public int getInvoiceid() {
        return invoiceid;
    }
    public void setInvoiceid(int invoiceid) {
        this.invoiceid = invoiceid;
    }
    public int getOrderid() {
        return orderid;
    }
    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

}