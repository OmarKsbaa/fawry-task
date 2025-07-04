package com.fawry.ecommerce.model;

/**
 * Base Product class for all products in the system
 */
public abstract class Product {
    private String name;
    private double price;
    private int quantity;
    private boolean requiresShipping;

    public Product(String name, double price, int quantity, boolean requiresShipping) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.requiresShipping = requiresShipping;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean requiresShipping() {
        return requiresShipping;
    }

    /**
     * Check if the product can be purchased (not expired and has quantity)
     * @param quantityRequested The quantity requested to purchase
     * @return true if the product can be purchased, false otherwise
     */
    public boolean canBePurchased(int quantityRequested) {
        return !isExpired() && quantityRequested <= quantity;
    }

    /**
     * Abstract method to check if the product is expired
     * @return true if the product is expired, false otherwise
     */
    public abstract boolean isExpired();

    /**
     * Reduce product quantity after purchase
     * @param quantityPurchased The quantity purchased
     */
    public void reduceQuantity(int quantityPurchased) {
        if (quantityPurchased <= quantity) {
            this.quantity -= quantityPurchased;
        } else {
            throw new IllegalArgumentException("Cannot reduce more than available quantity");
        }
    }
}
