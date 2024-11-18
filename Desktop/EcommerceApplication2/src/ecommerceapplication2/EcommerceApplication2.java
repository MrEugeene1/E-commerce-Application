package ecommerceapplication2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main class for the E-commerce application.
 * Provides a console-based interface for users to sign up, log in, and perform
 * various actions based on their role (Buyer or Seller).
 */
public class EcommerceApplication2 {
    private static ArrayList<Product> productList = new ArrayList<>();

    /**
     * The main method that serves as the entry point to the application.
     * It initializes the application, creates test accounts, and handles
     * user interactions in a continuous loop.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the E-commerce Application!");

        // Create a seller and buyer account for testing
        Seller seller = new Seller("seller1", "John", "Doe", "john@example.com", "password123", "123456789", "123 St, City", "Seller");
        Buyer buyer = new Buyer("buyer1", "Jane", "Doe", "jane@example.com", "password123", "987654321", "456 Ave, City");

        boolean buyerLoggedIn = false;
        ArrayList<Product> availableProducts = new ArrayList<>();

        // Main menu loop
        while (true) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In as Seller");
            System.out.println("3. Log In as Buyer");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        signUp(scanner);
                        break;

                    case 2:
                        logInAsSeller(scanner, seller);
                        break;

                    case 3:
                        logInAsBuyer(scanner, buyer);
                        break;

                    case 4:
                        System.out.println("Exiting the application.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please enter a number from 1 to 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number from 1 to 4.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    /**
     * Handles the sign-up process for a new user.
     *
     * @param scanner The Scanner object for reading user input.
     */
    private static void signUp(Scanner scanner) {
        try {
            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();
            System.out.print("Enter First Name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter Last Name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter Email: ");
            String signupEmail = scanner.nextLine();
            System.out.print("Enter Password: ");
            String signupPassword = scanner.nextLine();
            System.out.print("Enter Phone Number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter Address: ");
            String address = scanner.nextLine();
            System.out.print("Enter Role (e.g., buyer/seller): ");
            String role = scanner.nextLine();

            if (User.signup(userId, firstName, lastName, signupEmail, signupPassword, phoneNumber, address, role)) {
                System.out.println("You can now log in.");
            } else {
                System.out.println("Signup failed. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error during signup: " + e.getMessage());
        }
    }

    /**
     * Handles the log-in and main menu actions for a seller.
     *
     * @param scanner The Scanner object for reading user input.
     * @param seller  The seller object representing the logged-in seller.
     */
    private static void logInAsSeller(Scanner scanner, Seller seller) {
        try {
            System.out.print("Enter Seller Email: ");
            String sellerEmail = scanner.nextLine();
            System.out.print("Enter Password: ");
            String sellerPassword = scanner.nextLine();

            if (User.login(sellerEmail, sellerPassword)) {
                boolean sellerLoggedIn = true;
                while (sellerLoggedIn) {
                    System.out.println("\nSeller Menu:");
                    System.out.println("1. Add Physical Product");
                    System.out.println("2. Add Digital Product");
                    System.out.println("3. Display Products");
                    System.out.println("4. Logout");
                    System.out.print("Enter your choice: ");

                    int sellerChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (sellerChoice) {
                        case 1:
                            addPhysicalProduct(scanner);
                            break;

                        case 2:
                            addDigitalProduct(scanner);
                            break;

                        case 3:
                            seller.displayProducts();
                            break;

                        case 4:
                            System.out.println("Logging out as Seller...");
                            sellerLoggedIn = false;
                            break;

                        default:
                            System.out.println("Invalid choice.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error logging in as seller: " + e.getMessage());
        }
    }

    /**
     * Handles the log-in and main menu actions for a buyer.
     *
     * @param scanner The Scanner object for reading user input.
     * @param buyer   The buyer object representing the logged-in buyer.
     */
    private static void logInAsBuyer(Scanner scanner, Buyer buyer) {
        try {
            System.out.print("Enter Buyer Email: ");
            String buyerEmail = scanner.nextLine();
            System.out.print("Enter Password: ");
            String buyerPassword = scanner.nextLine();

            if (User.login(buyerEmail, buyerPassword)) {
                boolean buyerLoggedIn = true;
                while (buyerLoggedIn) {
                    System.out.println("\nBuyer Menu:");
                    System.out.println("1. View Available Products");
                    System.out.println("2. Add Product to Cart");
                    System.out.println("3. View Cart");
                    System.out.println("4. Checkout");
                    System.out.println("5. Logout");
                    System.out.print("Enter your choice: ");

                    int buyerChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (buyerChoice) {
                        case 1:
                            FileHandler.displayFromFile("products.txt");
                            break;

                        case 2:
                            System.out.print("Enter Product ID to add to cart: ");
                            String cartProductId = scanner.nextLine();
                            if (FileHandler.productExists("products.txt", cartProductId)) {
                                buyer.addProductToCart(cartProductId, productList);
                            } else {
                                System.out.println("Product not found in catalog.");
                            }
                            break;

                        case 3:
                            buyer.viewCart();
                            break;

                        case 4:
                            buyer.checkout();
                            break;

                        case 5:
                            System.out.println("Logging out as Buyer...");
                            buyerLoggedIn = false;
                            break;

                        default:
                            System.out.println("Invalid choice.");
                    }
                }
            } else {
                System.out.println("Invalid email or password. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error logging in as buyer: " + e.getMessage());
        }
    }

    /**
     * Adds a physical product to the product list and saves it to file.
     *
     * @param scanner The Scanner object for reading user input.
     */
    private static void addPhysicalProduct(Scanner scanner) {
        try {
            System.out.print("Enter Product ID: ");
            String productId = scanner.nextLine();
            System.out.print("Enter Product Name: ");
            String productName = scanner.nextLine();
            System.out.print("Enter Product Price: ");
            double price = scanner.nextDouble();
            System.out.print("Enter Product Weight (kg): ");
            double weight = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Enter Product Dimensions: ");
            String dimensions = scanner.nextLine();

            PhysicalProduct physicalProduct = new PhysicalProduct(productId, productName, price, weight, dimensions);
            productList.add(physicalProduct);
            FileHandler.saveToFile("products.txt", physicalProduct.toFileFormat());
            System.out.println("Physical product added and saved to file.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for product details. Please enter the correct data.");
            scanner.nextLine(); // Clear invalid input
        }
    }

    /**
     * Adds a digital product to the product list and saves it to file.
     *
     * @param scanner The Scanner object for reading user input.
     */
    private static void addDigitalProduct(Scanner scanner) {
        try {
            System.out.print("Enter Product ID: ");
            String digitalProductId = scanner.nextLine();
            System.out.print("Enter Product Name: ");
            String digitalProductName = scanner.nextLine();
            System.out.print("Enter Product Price: ");
            double digitalPrice = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Enter File Size: ");
            String fileSize = scanner.nextLine();
            System.out.print("Enter Format: ");
            String format = scanner.nextLine();

            DigitalProduct digitalProduct = new DigitalProduct(digitalProductId, digitalProductName, digitalPrice, fileSize, format);
            productList.add(digitalProduct);
            FileHandler.saveToFile("products.txt", digitalProduct.toFileFormat());
            System.out.println("Digital product added and saved to file.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for digital product details. Please enter the correct data.");
            scanner.nextLine(); // Clear invalid input
        }
    }
}
