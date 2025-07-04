package com.fawry.ecommerce.service;

import com.fawry.ecommerce.model.Shippable;
import java.util.List;

/**
 * Service for handling shipping operations
 */
public class ShippingService {
    private static final double BASE_SHIPPING_RATE = 4.99; // Base shipping fee
    private static final double WEIGHT_RATE = 2.50; // Fee per kg

    /**
     * Process shipment for a list of shippable items
     * @param items List of shippable items to ship
     */
    public void processShipment(List<Shippable> items) {
        if (items.isEmpty()) {
            return;
        }

        System.out.println("** Shipment notice **");
        
        // Group items by name and count them
        java.util.Map<String, Integer> itemCounts = new java.util.HashMap<>();
        java.util.Map<String, Double> itemWeights = new java.util.HashMap<>();
        
        double totalWeight = 0;
        
        for (Shippable item : items) {
            String name = item.getName();
            itemCounts.put(name, itemCounts.getOrDefault(name, 0) + 1);
            itemWeights.putIfAbsent(name, item.getWeight());
            totalWeight += item.getWeight();
        }
        
        // Print shipment details
        for (java.util.Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
            String name = entry.getKey();
            int count = entry.getValue();
            double weight = itemWeights.get(name);
            
            System.out.println(count + "x " + name + " " + (weight * 1000) + "g");
        }
        
        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }

    /**
     * Calculate shipping cost for a list of shippable items
     * @param items List of shippable items
     * @return Shipping cost
     */
    public double calculateShippingCost(List<Shippable> items) {
        if (items.isEmpty()) {
            return 0;
        }
        
        double totalWeight = 0;
        for (Shippable item : items) {
            totalWeight += item.getWeight();
        }
        
        // Calculate shipping cost based on weight
        return BASE_SHIPPING_RATE + (totalWeight * WEIGHT_RATE);
    }
}
