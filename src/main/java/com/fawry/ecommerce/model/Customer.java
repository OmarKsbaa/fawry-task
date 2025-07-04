package com.fawry.ecommerce.model;

/**
 * Represents a customer in the system
 */
public class Customer {
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Deduct amount from customer balance
     * @param amount Amount to deduct
     * @return true if deduction successful, false if insufficient balance
     */
    public boolean deductBalance(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
