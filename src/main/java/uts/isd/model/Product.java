package uts.isd.model;

import net.bytebuddy.asm.Advice.This;

public class Product {
    protected int productid;
	protected String productName;
	protected String productType;
	protected String description;
	protected Double price;
	protected int quantity;
    public Product(int productId, String ProductName, String productType, String description, Double price, int quantity){
        this.productid = productId;
        this.productName = ProductName;
        this.productType = productType;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public int getproductid(){
        return productid;
    }
    public void setproductid(){
        this.productid = productid;
    }
    public String getproductName(){
        return productName;
    }
    public void setproductName(){
        this.productName = productName;
    }
    public String getproductType(){
        return productType;
    }
    public void setproductType(){
        this.productType = productType;
    }
    public String getproductDescription(){
        return description;
    }
    public void setProductDescription(){
        this.description = description;
    }
    
    public Double getproductPrice(){
        return price;
    }
    public void setProducPrice(){
        this.price = price;
    }
    public int getproductQuantity(){
        return this.quantity;
    }
    public void setProducQuantity(){
        this.quantity = quantity;
    }
    
}
