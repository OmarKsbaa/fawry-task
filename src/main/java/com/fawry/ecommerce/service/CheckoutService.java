package com.fawry.ecommerce.service;

import com.fawry.ecommerce.model.Customer;
import com.fawry.ecommerce.model.Product;
import com.fawry.ecommerce.model.ShoppingCart;
import com.fawry.ecommerce.model.Shippable;
import java.util.List;
import java.util.Map;

/**
 * Service for handling checkout operations
 */
public class CheckoutService {
    private ShippingService shippingService;

    public CheckoutService() {
        this.shippingService = new ShippingService();
    }

    /**
     * Process checkout for a customer with items in cart
     * @param customer Customer checking out
     * @param cart Shopping cart with items
     * @return true if checkout successful, false otherwise
     */
    public boolean processCheckout(Customer customer, ShoppingCart cart) {
        // Check if cart is empty
        if (cart.isEmpty()) {
            System.out.println("Error: Cart is empty. Cannot proceed with checkout.");
            return false;
        }

        // Check if any product is expired or out of stock
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product.isExpired()) {
                System.out.println("Error: Product '" + product.getName() + "' is expired. Cannot proceed with checkout.");
                return false;
            }

            if (product.getQuantity() < quantity) {
                System.out.println("Error: Product '" + product.getName() + "' is out of stock. Cannot proceed with checkout.");
                return false;
            }
        }

        // Calculate amounts
        double subtotal = cart.calculateSubtotal();
        List<Shippable> shippableItems = cart.getShippableItems();
        double shippingFees = shippingService.calculateShippingCost(shippableItems);
        double totalAmount = subtotal + shippingFees;

        // Check if customer has sufficient balance
        if (customer.getBalance() < totalAmount) {
            System.out.println("Error: Insufficient balance. Required: $" + totalAmount + ", Available: $" + customer.getBalance());
            return false;
        }

        // Process shipment if there are shippable items
        if (!shippableItems.isEmpty()) {
            shippingService.processShipment(shippableItems);
        }

        // Print checkout receipt
        printCheckoutReceipt(cart, subtotal, shippingFees, totalAmount);

        // Deduct amount from customer balance
        customer.deductBalance(totalAmount);
        System.out.println("Customer current balance: $" + customer.getBalance());

        // Update product quantities
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            product.reduceQuantity(quantity);
        }

        // Clear the cart
        cart.clear();

        return true;
    }

    /**
     * Print checkout receipt
     * @param cart Shopping cart with items
     * @param subtotal Subtotal amount
     * @param shippingFees Shipping fees
     * @param totalAmount Total amount
     */
    private void printCheckoutReceipt(ShoppingCart cart, double subtotal, double shippingFees, double totalAmount) {
        System.out.println("** Checkout receipt **");
        
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            
            System.out.println(quantity + "x " + product.getName() + " " + (product.getPrice() * quantity));
        }
        
        System.out.println("----------------------");
        System.out.println("Subtotal " + subtotal);
        System.out.println("Shipping " + shippingFees);
        System.out.println("Amount " + totalAmount);
    }
}
