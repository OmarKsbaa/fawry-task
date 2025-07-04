# Fawry E-Commerce System

## Author
Created by: Omar Mostafa

## Overview
This project implements an e-commerce system for the Fawry Rise Journey Full Stack Development Internship Challenge. The system allows customers to purchase various types of products (perishable, non-perishable, digital), add them to a cart, and complete the checkout process.

## Features
- Product management with different product types:
  - Perishable products (with expiry dates)
  - Non-perishable products
  - Digital products (no shipping required)
- Shopping cart functionality
- Shipping service for physical products
- Checkout process with validation:
  - Checks for expired products
  - Verifies available quantity
  - Validates customer's balance
- Receipt generation with detailed pricing

## Project Structure
- `model`: Contains the data models
  - `Product.java`: Abstract base class for all products
  - `PerishableProduct.java`: Products with expiry dates
  - `NonPerishableProduct.java`: Products that don't expire
  - `DigitalProduct.java`: Digital products that don't require shipping
  - `Shippable.java`: Interface for products that can be shipped
  - `Customer.java`: Represents a customer
  - `ShoppingCart.java`: Shopping cart functionality
- `service`: Contains business logic
  - `ShippingService.java`: Handles shipping calculations and processing
  - `CheckoutService.java`: Manages the checkout process
- `ECommerceApp.java`: Interactive application with UI
- `DemoApp.java`: Demo application showing system functionality

## Running the Application
1. Navigate to the project directory
2. Compile the Java files:
   ```
   javac -d bin src/main/java/com/fawry/ecommerce/*.java src/main/java/com/fawry/ecommerce/*/*.java
   ```
3. Run the interactive application:
   ```
   java -cp bin com.fawry.ecommerce.ECommerceApp
   ```
4. Alternatively, run the demo application that shows predefined scenarios:
   ```
   java -cp bin com.fawry.ecommerce.DemoApp
   ```

## Example Output
The demo application demonstrates the following scenarios:

### Successful Checkout
```
** Shipment notice **
1x TV 15000.0g
2x Cheese 200.0g
Total package weight 15.4kg
** Checkout receipt **
1x TV 499.99
1x Scratch Card 25.0
2x Cheese 15.98
----------------------
Subtotal 540.97
Shipping 43.49
Amount 584.46
Customer current balance: $415.54
```

### Error Scenarios
- Expired product
- Insufficient balance
- Empty cart

## Requirements
- Java 8 or higher
