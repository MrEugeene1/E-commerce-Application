package ecommerceapplication2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

/**
 * Represents a Buyer in the e-commerce application.
 * A Buyer can add products to their cart, view the cart, remove items from it,
 * and proceed to checkout.
 */
public class Buyer extends User {
    private ArrayList<Product> cart; // List to store products added to the cart
    private static final int CHECKOUT_LIMIT = 3; // Minimum number of items to allow checkout

    /**
     * Constructs a Buyer object with the specified details.
     *
     * @param userId      The user's unique identifier.
     * @param firstName   The user's first name.
     * @param lastName    The user's last name.
     * @param email       The user's email address.
     * @param password    The user's password.
     * @param phoneNumber The user's phone number.
     * @param address     The user's address.
     */
    public Buyer(String userId, String firstName, String lastName, String email, String password,
                 String phoneNumber, String address) {
        super(userId, firstName, lastName, email, password, phoneNumber, address, "Buyer");
        this.cart = new ArrayList<>();
    }

    /**
     * Displays the list of available products and their prices.
     * Includes error handling for empty or null product lists.
     */
    public void displayAvailableProducts() {
        try {
            ArrayList<Product> productList = FileHandler.loadProductsFromFile("products.txt");
            if (productList == null || productList.isEmpty()) {
                System.out.println("No products available.");
                return;
            }
            System.out.println("\nAvailable Products:");
            for (int i = 0; i < productList.size(); i++) {
                Product product = productList.get(i);
                System.out.println((i + 1) + ". " + product.getName() + " - $" + product.getPrice());
            }
        } catch (Exception e) {
            System.out.println("An error occurred while displaying products: " + e.getMessage());
        }
    }

    /**
     * Adds a product to the cart based on its product ID.
     * Checks if the product ID exists in the product list.
     *
     * @param productId   The ID of the product to add to the cart.
     * @param productList The list of available products.
     */
    public void addProductToCart(String productId, ArrayList<Product> productList) {
        try {
            // Load the latest products from the file to ensure it has the current data
            productList = FileHandler.loadProductsFromFile("products.txt");

            for (Product product : productList) {
                if (product.getProductId().equals(productId)) {
                    cart.add(product);
                    System.out.println("Added " + product.getName() + " to your cart.");
                    return;
                }
            }

            System.out.println("Product not found.");
        } catch (Exception e) {
            System.out.println("An error occurred while adding the product to the cart: " + e.getMessage());
        }
    }

    /**
     * Views the contents of the cart and displays the total price of all items.
     * If the cart is empty, a message is displayed.
     */
    public void viewCart() {
        try {
            if (cart.isEmpty()) {
                System.out.println("Your cart is empty.");
            } else {
                System.out.println("\nYour Cart:");
                double total = 0;
                for (Product product : cart) {
                    System.out.println(product.getName() + " - $" + product.getPrice());
                    total += product.getPrice();
                }
                System.out.println("Total: $" + total);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while viewing the cart: " + e.getMessage());
        }
    }

    /**
     * Proceeds to checkout by validating the cart's contents and processing the payment.
     * If the cart has fewer items than the checkout limit, the checkout is not allowed.
     */
    public void checkout() {
        try {
            if (cart.size() < CHECKOUT_LIMIT) {
                System.out.println("You need at least " + CHECKOUT_LIMIT + " items in your cart to checkout.");
                return;
            }
            System.out.println("Checking out " + cart.size() + " items...");
            double totalAmount = 0;
            for (Product product : cart) {
                totalAmount += product.getPrice();
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your payment method (e.g., Credit Card, PayPal):");
            String paymentMethod = scanner.nextLine();
            String paymentId = UUID.randomUUID().toString();
            Payment payment = new Payment(paymentId, totalAmount, paymentMethod);
            payment.processPayment();

            if (payment.isPaymentStatus()) {
                cart.clear();
                System.out.println("Total price: $" + totalAmount);
                System.out.println("Thank you for your purchase!");
            } else {
                System.out.println("Payment failed or was cancelled. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred during checkout: " + e.getMessage());
        }
    }

    /**
     * Removes a product from the cart by its index.
     * If the index is invalid or out of bounds, an error message is displayed.
     *
     * @param index The index of the product to be removed from the cart.
     */
    public void removeFromCart(int index) {
        try {
            if (index < 0 || index >= cart.size()) {
                System.out.println("Invalid index. Please select a valid product number.");
                return;
            }
            Product removedProduct = cart.remove(index);
            System.out.println("Removed " + removedProduct.getName() + " from your cart.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while removing the product: " + e.getMessage());
        }
    }
}
