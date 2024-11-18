package ecommerceapplication2;

import java.util.Scanner;

/**
 * The {@code Payment} class represents a payment transaction in the e-commerce application.
 * It provides methods for processing and validating payment details like card number, expiry date, and CVV.
 * This class handles exceptions during the payment process and validates payment information.
 * 
 * <p><strong>Usage:</strong></p>
 * <p>To use this class, create an instance with a payment ID, amount, and payment method,
 * then call {@link #processPayment()} to handle the payment process.</p>
 * 
 * @author Your Name
 * @version 1.0
 * @since 2024-11-10
 */
public class Payment {
    private String paymentId;       // Unique identifier for the payment
    private double amount;          // Payment amount
    private String paymentMethod;   // Payment method (e.g., Credit Card, PayPal)
    private boolean paymentStatus;  // Indicates if payment was successful
    private String cardNumber;      // Card number used for payment
    private String expiryDate;      // Expiry date of the card
    private String cvv;             // CVV of the card
    private boolean isProcessed;    // Indicates if payment has been processed

    /**
     * Constructs a {@code Payment} object with the specified details.
     *
     * @param paymentId      The unique identifier for the payment.
     * @param amount         The amount to be paid.
     * @param paymentMethod  The method of payment.
     */
    public Payment(String paymentId, double amount, String paymentMethod) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = true;
        this.isProcessed = false;
    }

    /**
     * Returns the unique identifier for the payment.
     *
     * @return The payment ID.
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * Returns the amount to be paid.
     *
     * @return The payment amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Returns the method of payment.
     *
     * @return The payment method.
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Checks if the payment status is successful.
     *
     * @return {@code true} if the payment is successful; {@code false} otherwise.
     */
    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Processes the payment by collecting and validating card details from the user.
     * Validates the card number, expiry date, and CVV, and simulates payment processing.
     * Sets {@code isProcessed} to {@code true} upon successful processing.
     */
    public void processPayment() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("\nProcessing Payment...");
            System.out.print("Enter card number (16 digits): ");
            this.cardNumber = scanner.nextLine();

            if (!isValidCardNumber(this.cardNumber)) {
                throw new IllegalArgumentException("Invalid card number. Please enter a 16-digit card number.");
            }

            System.out.print("Enter expiry date (MM/YY): ");
            this.expiryDate = scanner.nextLine();

            if (!isValidExpiryDate(this.expiryDate)) {
                throw new IllegalArgumentException("Invalid expiry date. Please enter in MM/YY format.");
            }

            System.out.print("Enter CVV (3 digits): ");
            this.cvv = scanner.nextLine();

            if (!isValidCVV(this.cvv)) {
                throw new IllegalArgumentException("Invalid CVV. Please enter a 3-digit CVV.");
            }

            System.out.println("Processing your payment of $" + amount + "...");
            Thread.sleep(2000); // Simulate delay
            isProcessed = true;
            System.out.println("Payment processed successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println("Payment Error: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Payment processing interrupted.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred during payment: " + e.getMessage());
        }
    }

    /**
     * Cancels the payment if it has not been processed.
     * If the payment has already been processed, it cannot be cancelled.
     */
    public void cancelPayment() {
        if (isProcessed) {
            System.out.println("Payment has already been processed. Cannot cancel.");
        } else {
            System.out.println("Payment cancelled successfully.");
        }
    }

    /**
     * Validates the card number to ensure it has exactly 16 digits.
     *
     * @param cardNumber The card number to validate.
     * @return {@code true} if the card number is valid; {@code false} otherwise.
     */
    private boolean isValidCardNumber(String cardNumber) {
        return cardNumber.matches("\\d{16}");
    }

    /**
     * Validates the expiry date to ensure it is in MM/YY format.
     *
     * @param expiryDate The expiry date to validate.
     * @return {@code true} if the expiry date is valid; {@code false} otherwise.
     */
    private boolean isValidExpiryDate(String expiryDate) {
        return expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}");
    }

    /**
     * Validates the CVV to ensure it has exactly 3 digits.
     *
     * @param cvv The CVV to validate.
     * @return {@code true} if the CVV is valid; {@code false} otherwise.
     */
    private boolean isValidCVV(String cvv) {
        return cvv.matches("\\d{3}");
    }

    /**
     * Checks if the payment has been processed.
     *
     * @return {@code true} if the payment has been processed; {@code false} otherwise.
     */
    public boolean isProcessed() {
        return isProcessed;
    }
}
