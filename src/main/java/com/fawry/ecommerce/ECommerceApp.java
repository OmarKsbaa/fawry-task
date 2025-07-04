package com.fawry.ecommerce;

import com.fawry.ecommerce.model.*;
import com.fawry.ecommerce.service.CheckoutService;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Main application class for the e-commerce system
 */
public class ECommerceApp {
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
        
        NonPerishableProduct mobile = new NonPerishableProduct(
                "Mobile", 399.99, 8, true, 0.3); // Weighs 300g
        
        DigitalProduct scratchCard = new DigitalProduct(
                "Scratch Card", 25.0, 100); // Digital product
        
        // Create customer with initial balance
        Customer customer = new Customer("Omar Mostafa", 1000.0);
        
        // Start interactive shopping session
        startShoppingSession(customer, cheese, biscuits, tv, mobile, scratchCard, checkoutService);
    }
    
    private static void startShoppingSession(Customer customer, PerishableProduct cheese, 
            PerishableProduct biscuits, NonPerishableProduct tv, NonPerishableProduct mobile, 
            DigitalProduct scratchCard, CheckoutService checkoutService) {
        
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();
        
        System.out.println("========================================");
        System.out.println("Welcome to Fawry E-Commerce System");
        System.out.println("========================================");
        System.out.println("Hello, " + customer.getName() + "!");
        System.out.println("Your current balance: $" + customer.getBalance());
        
        while (true) {
            System.out.println("\nAvailable products:");
            System.out.println("1. Cheese - $" + cheese.getPrice() + " (Available: " + cheese.getQuantity() + ")");
            System.out.println("2. Biscuits - $" + biscuits.getPrice() + " (Available: " + biscuits.getQuantity() + ")");
            System.out.println("3. TV - $" + tv.getPrice() + " (Available: " + tv.getQuantity() + ")");
            System.out.println("4. Mobile - $" + mobile.getPrice() + " (Available: " + mobile.getQuantity() + ")");
            System.out.println("5. Scratch Card - $" + scratchCard.getPrice() + " (Available: " + scratchCard.getQuantity() + ")");
            System.out.println("6. View Cart");
            System.out.println("7. Checkout");
            System.out.println("8. Exit");
            
            System.out.print("\nEnter your choice: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            
            if (choice >= 1 && choice <= 5) {
                System.out.print("Enter quantity: ");
                int quantity;
                try {
                    quantity = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    continue;
                }
                
                Product selectedProduct = null;
                switch (choice) {
                    case 1:
                        selectedProduct = cheese;
                        break;
                    case 2:
                        selectedProduct = biscuits;
                        break;
                    case 3:
                        selectedProduct = tv;
                        break;
                    case 4:
                        selectedProduct = mobile;
                        break;
                    case 5:
                        selectedProduct = scratchCard;
                        break;
                }
                
                cart.addProduct(selectedProduct, quantity);
                
            } else if (choice == 6) {
                // View cart
                if (cart.isEmpty()) {
                    System.out.println("Your cart is empty.");
                } else {
                    System.out.println("\nYour cart:");
                    for (java.util.Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
                        Product product = entry.getKey();
                        int quantity = entry.getValue();
                        System.out.println(quantity + "x " + product.getName() + " - $" + (product.getPrice() * quantity));
                    }
                    System.out.println("Subtotal: $" + cart.calculateSubtotal());
                }
                
            } else if (choice == 7) {
                // Checkout
                boolean success = checkoutService.processCheckout(customer, cart);
                if (success) {
                    System.out.println("Thank you for your purchase!");
                }
                
            } else if (choice == 8) {
                // Exit
                break;
                
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        
        System.out.println("Thank you for shopping with us!");
        scanner.close();
    }
    
    // Example method to demonstrate the system with predetermined actions
    private static void runDemo(Customer customer, Product cheese, Product biscuits, 
            Product tv, Product scratchCard, CheckoutService checkoutService) {
        
        ShoppingCart cart = new ShoppingCart();
        
        // Add products to cart as per example
        System.out.println("\nDEMO MODE:");
        cart.addProduct(cheese, 2);
        cart.addProduct(tv, 3);
        cart.addProduct(scratchCard, 1);
        
        // Process checkout
        System.out.println("\nProcessing checkout...");
        checkoutService.processCheckout(customer, cart);
    }
}
