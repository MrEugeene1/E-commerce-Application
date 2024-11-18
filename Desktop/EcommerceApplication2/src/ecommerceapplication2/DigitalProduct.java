package ecommerceapplication2;

/**
 * Represents a digital product in the e-commerce application.
 * This class extends the {@code Product} class and adds attributes specific to digital products,
 * such as file size and format.
 */
public class DigitalProduct extends Product {
    private String fileSize; // The size of the digital file
    private String format;   // The format of the digital file (e.g., PDF, MP4)

    /**
     * Constructs a {@code DigitalProduct} object with the specified details.
     *
     * @param productId The unique identifier for the product.
     * @param name      The name of the product.
     * @param price     The price of the product.
     * @param fileSize  The size of the digital file.
     * @param format    The format of the digital product (e.g., PDF, MP4).
     */
    public DigitalProduct(String productId, String name, double price, String fileSize, String format) {
        super(productId, name, price);
        this.fileSize = fileSize;
        this.format = format;
    }

    /**
     * Returns a string representation of the digital product's details in a format suitable for file storage.
     *
     * @return A comma-separated string containing the product type, product ID, name, price, file size, and format.
     */
    @Override
    public String toFileFormat() {
        return "DigitalProduct," + getProductId() + "," + getName() + "," + getPrice() + "," + this.fileSize + "," + this.format;
    }

    /**
     * Displays the details of the digital product.
     * The information includes the product's ID, name, price, file size, and format.
     */
    @Override
    public void displayDetails() {
        System.out.println("\nDigital Product - " + getName());
        System.out.println("  Product ID: " + getProductId());
        System.out.println("  Price: $" + getPrice());
        System.out.println("  File Size: " + fileSize);
        System.out.println("  Format: " + format);
    }

    // Getters
    public String getFileSize() {
        return fileSize;
    }

    public String getFormat() {
        return format;
    }
}
