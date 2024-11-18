package ecommerceapplication2;

/**
 * Represents a physical product in the e-commerce application.
 * This class extends the {@code Product} class and adds attributes specific to physical products,
 * such as weight and dimensions.
 */
public class PhysicalProduct extends Product {
    private double weight; // Weight of the physical product
    private String dimensions; // Dimensions of the physical product

    /**
     * Constructs a {@code PhysicalProduct} object with the specified details.
     *
     * @param productId  The unique identifier for the product.
     * @param name       The name of the product.
     * @param price      The price of the product.
     * @param weight     The weight of the physical product.
     * @param dimensions The dimensions of the physical product.
     */
    public PhysicalProduct(String productId, String name, double price, double weight, String dimensions) {
        super(productId, name, price);
        this.weight = weight;
        this.dimensions = dimensions;
    }

    /**
     * Returns a string representation of the physical product's details in a format suitable for file storage.
     *
     * @return A comma-separated string containing the product type, product ID, name, price, weight, and dimensions.
     */
    @Override
    public String toFileFormat() {
        return "PhysicalProduct," + getProductId() + "," + getName() + "," + getPrice() + "," + weight + "," + dimensions;
    }

    /**
     * Displays the details of the physical product.
     * The information includes the product's ID, name, price, weight, and dimensions.
     */
    @Override
    public void displayDetails() {
        System.out.println("\nPhysical Product - " + getName());
        System.out.println("  Product ID: " + getProductId());
        System.out.println("  Price: $" + getPrice());
        System.out.println("  Weight: " + weight + " kg");
        System.out.println("  Dimensions: " + dimensions);
    }

    // Getters
    public double getWeight() {
        return weight;
    }

    public String getDimensions() {
        return dimensions;
    }
}
