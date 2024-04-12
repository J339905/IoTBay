package uts.isd.model;

public class Invoice{
    protected int invoiceid;
	protected int orderid;
	protected int amount;
	protected String paymentDate;

public Invoice(){
    this.invoiceid = invoiceid;
    this.orderid = orderid;
    this.amount = amount;
    this.paymentDate = paymentDate;
}

public int getinvoiceid(){
    return invoiceid;
}
public void setproductid(){
    this.invoiceid = invoiceid;
}
public int getorderid(){
    return orderid;
}
public void setorderid(){
    this.orderid = orderid;
}
public int getamount(){
    return amount;
}
public void setamount(){
    this.amount = amount;
}
public String getpaymentDate(){
    return paymentDate;
}
public void setpaymentDate(){
    this.paymentDate = paymentDate;
}

}