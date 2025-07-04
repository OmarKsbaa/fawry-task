package com.fawry.ecommerce.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Shopping cart for holding products that a customer wants to buy
 */
public class ShoppingCart {
    private Map<Product, Integer> items; // Product and quantity

    public ShoppingCart() {
        this.items = new HashMap<>();
    }

    /**
     * Add product to cart
     * @param product Product to add
     * @param quantity Quantity to add
     * @return true if addition successful, false otherwise
     */
    public boolean addProduct(Product product, int quantity) {
        // Check if quantity is positive
        if (quantity <= 0) {
            System.out.println("Quantity must be positive");
            return false;
        }

        // Check if product has enough quantity
        if (product.getQuantity() < quantity) {
            System.out.println("Not enough quantity available for " + product.getName());
            return false;
        }

        // If product already exists in cart, update quantity
        if (items.containsKey(product)) {
            int currentQuantity = items.get(product);
            // Check if total quantity exceeds available quantity
            if (currentQuantity + quantity > product.getQuantity()) {
                System.out.println("Cannot add more than available quantity for " + product.getName());
                return false;
            }
            items.put(product, currentQuantity + quantity);
        } else {
            items.put(product, quantity);
        }

        System.out.println("Added " + quantity + "x " + product.getName() + " to cart");
        return true;
    }

    /**
     * Get all items in the cart
     * @return Map of products and their quantities
     */
    public Map<Product, Integer> getItems() {
        return items;
    }

    /**
     * Check if cart is empty
     * @return true if cart is empty, false otherwise
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Calculate subtotal of all items in cart
     * @return Subtotal amount
     */
    public double calculateSubtotal() {
        double subtotal = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            subtotal += entry.getKey().getPrice() * entry.getValue();
        }
        return subtotal;
    }

    /**
     * Get list of shippable items
     * @return List of shippable items
     */
    public List<Shippable> getShippableItems() {
        List<Shippable> shippableItems = new ArrayList<>();
        
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            if (product.requiresShipping()) {
                if (product instanceof Shippable) {
                    for (int i = 0; i < entry.getValue(); i++) {
                        shippableItems.add((Shippable) product);
                    }
                }
            }
        }
        
        return shippableItems;
    }

    /**
     * Clear the cart
     */
    public void clear() {
        items.clear();
    }
}
