package com.fawry.ecommerce.model;

import java.time.LocalDate;

/**
 * Perishable product that can expire (e.g., Cheese, Biscuits)
 */
public class PerishableProduct extends Product implements Shippable {
    private LocalDate expiryDate;
    private double weight; // in kg

    public PerishableProduct(String name, double price, int quantity, boolean requiresShipping, 
                            LocalDate expiryDate, double weight) {
        super(name, price, quantity, requiresShipping);
        this.expiryDate = expiryDate;
        this.weight = weight;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}
