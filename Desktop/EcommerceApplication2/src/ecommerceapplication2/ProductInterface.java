package ecommerceapplication2;

/**
 * The {@code ProductInterface} interface defines the basic contract that any
 * product class in the e-commerce application must adhere to. It includes methods
 * for retrieving product details, formatting data for file storage, displaying
 * product details, and saving product information to a file.
 *
 * <p>This interface can be implemented by different types of product classes
 * (e.g., digital and physical products) to ensure consistency in accessing and
 * managing product information.
 * 
 * <p>Usage example:
 * <pre>
 *     public class DigitalProduct implements ProductInterface {
 *         // Implementation of methods
 *     }
 * </pre>
 *
 * @author user
 */
public interface ProductInterface {

    /**
     * Retrieves the unique identifier for the product.
     *
     * @return the product ID as a {@code String}.
     */
    String getProductId();

    /**
     * Retrieves the name of the product.
     *
     * @return the product name as a {@code String}.
     */
    String getName();

    /**
     * Retrieves the price of the product.
     *
     * @return the product price as a {@code double}.
     */
    double getPrice();

    /**
     * Formats the product details into a string representation suitable for file storage.
     *
     * @return a comma-separated string representing the product's details.
     */
    String toFileFormat();

    /**
     * Displays the details of the product. 
     * Implementing classes should provide a formatted output of all relevant 
     * product information.
     */
    void displayDetails();

    /**
     * Saves the product information to a specified file.
     * Implementing classes should define how and where the product data is saved.
     */
    void saveProductToFile();
}
