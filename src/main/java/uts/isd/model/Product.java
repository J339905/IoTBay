package uts.isd.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int productid;
    private String productname;  // Corrected the String type
    private String productcategory;  // Corrected the String type
    private String productdescription;  // Corrected the String type
    private double productprice;
    private int productstock;

    public Product(int productid, String productname, String productcategory, String productdescription, double productprice, int productstock) {
        this.productid = productid;
        this.productname = productname;
        this.productcategory = productcategory;
        this.productdescription = productdescription;
        this.productprice = productprice;
        this.productstock = productstock;      
    }

    public Product(){}

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductcategory() {
        return productcategory;
    }

    public void setProductcategory(String productcategory) {
        this.productcategory = productcategory;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public double getProductprice() {
        return productprice;
    }

    public void setProductprice(double productprice) {  
        this.productprice = productprice;
    }

    public int getProductstock() {
        return productstock;
    }

    public void setProductstock(int productstock) {
        this.productstock = productstock;
    }
}