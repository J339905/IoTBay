package uts.isd.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();
    public void addItem(CartItem item) {
        items.add(item);
        System.out.println("Added item: " + item.getProduct().getProductname() + " | Total items: " + items.size());
    }
    

    public void removeItem(CartItem item) {
        items.remove(item);
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    // Optionally add more methods to manage items in the cart
}
