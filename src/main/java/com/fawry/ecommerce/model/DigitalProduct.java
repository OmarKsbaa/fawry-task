package com.fawry.ecommerce.model;

/**
 * Digital product that doesn't expire and doesn't require shipping (e.g., Mobile scratch cards)
 */
public class DigitalProduct extends Product {
    
    public DigitalProduct(String name, double price, int quantity) {
        super(name, price, quantity, false); // Digital products don't require shipping
    }

    @Override
    public boolean isExpired() {
        return false; // Digital products don't expire
    }
}
