package uts.isd.model;

// Represents an individual item within a shopping cart, linking a product with its quantity
public class CartItem {
    private Product product;  // The product associated with this cart item
    private int quantity;     // The quantity of this product in the cart

    // Constructor to initialize the cart item with a product and its quantity
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Returns the product associated with this cart item
    public Product getProduct() {
        return product;
    }

    // Sets the product for this cart item
    public void setProduct(Product product) {
        this.product = product;
    }

    // Returns the quantity of the product in this cart item
    public int getQuantity() {
        return quantity;
    }

    // Sets the quantity of the product in this cart item
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
