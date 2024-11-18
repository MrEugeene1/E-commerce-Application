package ecommerceapplication2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Seller extends User {

    /**
     * A list of products managed by the seller.
     */
    private List<Product> products;

    /**
     * Constructs a new Seller object with the specified user information.
     *
     * @param userId      The unique ID of the seller.
     * @param firstName   The first name of the seller.
     * @param lastName    The last name of the seller.
     * @param email       The email address of the seller.
     * @param password    The password of the seller.
     * @param phoneNumber The phone number of the seller.
     * @param address     The address of the seller.
     * @param role        The role of the user, typically "Seller".
     */
    public Seller(String userId, String firstName, String lastName, String email,
                  String password, String phoneNumber, String address, String role) {
        super(userId, firstName, lastName, email, password, phoneNumber, address, role);
        this.products = new ArrayList<>();
    }

    /**
     * Adds a physical product to the seller's product list after validation.
     *
     * @param product The physical product to be added.
     */
    public void addPhysicalProduct(PhysicalProduct product) {
        try {
            validateProductInput(product);
            products.add(product);
            product.saveProductToFile(); // Save to products.txt
            System.out.println("Physical product '" + product.getName() + "' added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Input Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Data Type Error: Please enter valid input values.");
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }
    }

    /**
     * Adds a digital product to the seller's product list after validation.
     *
     * @param product The digital product to be added.
     */
    public void addDigitalProduct(DigitalProduct product) {
        try {
            validateProductInput(product);
            products.add(product);
            product.saveProductToFile(); // Save to products.txt
            System.out.println("Digital product '" + product.getName() + "' added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Input Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Data Type Error: Please enter valid input values.");
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }
    }

    /**
     * Displays the list of products managed by the seller. If no products are available,
     * it prints a message indicating an empty product list.
     */
    public void displayProducts() {
        try {
            ArrayList<Product> productsFromFile = FileHandler.loadProductsFromFile("products.txt");

            if (productsFromFile.isEmpty()) {
                System.out.println("No products available.");
                return;
            }

            System.out.println("\nList of Products:");
            for (Product product : productsFromFile) {
                try {
                    product.displayDetails();
                    System.out.println(); // Line break between products
                } catch (NullPointerException e) {
                    System.out.println("Error: Product details are missing.");
                } catch (Exception e) {
                    System.out.println("Error displaying product details: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Unexpected Error while displaying products: " + e.getMessage());
        }
    }

    /**
     * Checks if a product with the same ID already exists in the seller's product list.
     *
     * @param productId The ID of the product to check for duplicates.
     * @return True if a duplicate product is found, false otherwise.
     */
    private boolean isDuplicateProduct(String productId) {
        try {
            if (productId == null || productId.isEmpty()) {
                throw new IllegalArgumentException("Product ID cannot be null or empty.");
            }

            for (Product product : products) {
                if (product.getProductId().equals(productId)) {
                    return true;
                }
            }
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("Input Error: " + e.getMessage());
            return true; // Return true to prevent adding an invalid product
        } catch (Exception e) {
            System.out.println("Unexpected Error while checking for duplicate products: " + e.getMessage());
            return true;
        }
    }

    /**
     * Validates the input details of a product before adding it to the list.
     *
     * @param product The product to validate.
     * @throws IllegalArgumentException if any input validation fails.
     */
    private void validateProductInput(Product product) throws IllegalArgumentException {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero.");
        }
        if (isDuplicateProduct(product.getProductId())) {
            throw new IllegalArgumentException("Product with ID '" + product.getProductId() + "' already exists.");
        }
    }

    /**
     * Gets the list of products managed by the seller.
     *
     * @return A list of products.
     */
    public List<Product> getProducts() {
        return products;
    }
}
