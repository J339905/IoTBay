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

    public void removeItem(int productId) {
        CartItem itemToRemove = null;
        for (CartItem item : items) {
            if (item.getProduct().getProductid() == productId) {
                itemToRemove = item;
                break;
            }
        }
        if (itemToRemove != null) {
            items.remove(itemToRemove);
        }
    }

    public void clearItems() {
        items.clear();
        System.out.println("Cleared all items from the cart.");
    }

    // Calculate total price of all items in the cart
    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getProduct().getProductprice() * item.getQuantity();
        }
        return total;
    }

    // Calculate total quantity of all items in the cart
    public int getTotalQuantity() {
        int quantity = 0;
        for (CartItem item : items) {
            quantity += item.getQuantity();
        }
        return quantity;
    }
}
