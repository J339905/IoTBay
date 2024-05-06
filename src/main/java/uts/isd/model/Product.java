package uts.isd.model;

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
    public int getProductid() {
        return productid;
    }
    public void setProductid(int productid) {
        this.productid = productid;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    
}
