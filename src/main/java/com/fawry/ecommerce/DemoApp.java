package com.fawry.ecommerce;

import com.fawry.ecommerce.model.*;
import com.fawry.ecommerce.service.CheckoutService;
import java.time.LocalDate;

/**
 * Demo application showing the e-commerce system functionality
 */
public class DemoApp {
    public static void main(String[] args) {
        // Initialize services
        CheckoutService checkoutService = new CheckoutService();
        
        // Create products
        PerishableProduct cheese = new PerishableProduct(
                "Cheese", 7.99, 10, true, 
                LocalDate.now().plusDays(30), 0.2); // Expires in 30 days, weighs 200g
        
        PerishableProduct biscuits = new PerishableProduct(
                "Biscuits", 4.50, 20, true, 
                LocalDate.now().plusDays(90), 0.7); // Expires in 90 days, weighs 700g
        
        NonPerishableProduct tv = new NonPerishableProduct(
                "TV", 499.99, 5, true, 15.0); // Weighs 15kg
        
        DigitalProduct scratchCard = new DigitalProduct(
                "Scratch Card", 25.0, 100); // Digital product
        
        // Create customer with initial balance
        Customer customer = new Customer("Omar Mostafa", 1000.0);
        
        // Create shopping cart
        ShoppingCart cart = new ShoppingCart();
        
        // Demo scenario
        System.out.println("====== FAWRY E-COMMERCE SYSTEM DEMO ======\n");
        
        System.out.println("Customer: " + customer.getName());
        System.out.println("Initial Balance: $" + customer.getBalance() + "\n");
        
        // Add products to cart
        System.out.println("Adding products to cart...");
        cart.addProduct(cheese, 2);
        cart.addProduct(tv, 1);
        cart.addProduct(scratchCard, 1);
        
        // Process checkout
        System.out.println("\nProcessing checkout...");
        checkoutService.processCheckout(customer, cart);
        
        // Demonstrate an error scenario - expired product
        System.out.println("\n\n====== ERROR SCENARIO: EXPIRED PRODUCT ======");
        
        // Create expired cheese
        PerishableProduct expiredCheese = new PerishableProduct(
                "Expired Cheese", 7.99, 10, true, 
                LocalDate.now().minusDays(1), 0.2); // Expired yesterday
                
        ShoppingCart cart2 = new ShoppingCart();
        cart2.addProduct(expiredCheese, 1);
        
        checkoutService.processCheckout(customer, cart2);
        
        // Demonstrate insufficient balance
        System.out.println("\n\n====== ERROR SCENARIO: INSUFFICIENT BALANCE ======");
        
        Customer poorCustomer = new Customer("Poor Customer", 20.0);
        ShoppingCart cart3 = new ShoppingCart();
        cart3.addProduct(tv, 1);
        
        checkoutService.processCheckout(poorCustomer, cart3);
        
        // Demonstrate empty cart
        System.out.println("\n\n====== ERROR SCENARIO: EMPTY CART ======");
        
        ShoppingCart emptyCart = new ShoppingCart();
        checkoutService.processCheckout(customer, emptyCart);
    }
}
