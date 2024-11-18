package ecommerceapplication2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public abstract class Product implements ProductInterface, Serializable  {
    private String productId; // Unique identifier for the product
    private String name; // Name of the product
    private double price; // Price of the product
    protected static final String FILE_PATH = "products.txt"; // File path for saving products

    /**
     * Constructor for creating a new Product with error handling for invalid inputs.
     *
     * @param productId Unique identifier of the product (cannot be null or empty)
     * @param name      Name of the product (cannot be null or empty)
     * @param price     Price of the product (must be greater than zero)
     * @throws IllegalArgumentException if any of the parameters are invalid
     */
    public Product(String productId, String name, double price) {
        if (productId == null || productId.isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty.");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero.");
        }
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    /**
     * Formats the product details into a comma-separated string for file saving.
     *
     * @return A string in the format: "ProductType,productId,name,price"
     */
    public String toFileFormat() {
        return this.getClass().getSimpleName() + "," + this.productId + "," + this.name + "," + this.price;
    }

    /**
     * Retrieves the unique identifier of the product.
     *
     * @return The product ID
     */
    public String getProductId() {
        return this.productId;
    }

    /**
     * Retrieves the name of the product.
     *
     * @return The name of the product
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the price of the product.
     *
     * @return The price of the product
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Abstract method to display product details, implemented by subclasses.
     */
    public abstract void displayDetails();

    /**
     * Saves the product details to a file.
     * If the file does not exist, it is created.
     */
    public void saveProductToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(toFileFormat());
            writer.newLine();
            System.out.println("Product saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving product: " + e.getMessage());
        }
    }
}
