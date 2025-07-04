package com.fawry.ecommerce.model;

/**
 * Non-perishable product that doesn't expire (e.g., TV, Mobile)
 */
public class NonPerishableProduct extends Product implements Shippable {
    private double weight; // in kg

    public NonPerishableProduct(String name, double price, int quantity, boolean requiresShipping, double weight) {
        super(name, price, quantity, requiresShipping);
        this.weight = weight;
    }

    @Override
    public boolean isExpired() {
        return false; // Non-perishable products don't expire
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
