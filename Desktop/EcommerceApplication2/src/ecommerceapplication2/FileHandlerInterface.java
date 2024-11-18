package ecommerceapplication2;

import java.util.ArrayList;

/**
 * The {@code FileHandlerInterface} defines methods for handling file-related operations 
 * within the e-commerce application, including checking product existence, saving data to a file, 
 * displaying file contents, and loading products from a file.
 * 
 * <p>Classes implementing this interface should provide the necessary functionality 
 * for interacting with files to store and retrieve product data.
 * 
 * <p>Usage example:
 * <pre>
 *     public class FileHandler implements FileHandlerInterface {
 *         // Implementation of methods for file handling
 *     }
 * </pre>
 * 
 * This interface serves as a contract for any class that aims to manage file operations 
 * for products, ensuring consistency and reusability of file handling code.
 * 
 * @see ProductInterface
 */
public interface FileHandlerInterface {

    /**
     * Checks if a product with the specified product ID exists in the given file.
     *
     * @param fileName  The name of the file to search in.
     * @param productId The product ID to look for.
     * @return {@code true} if the product ID exists in the file, otherwise {@code false}.
     */
    boolean productExists(String fileName, String productId);

    /**
     * Saves the given product data to the specified file.
     * The data is appended to the end of the file if it exists; otherwise, a new file is created.
     *
     * @param filename    The name of the file to save the data to.
     * @param productData The product data to write to the file, typically in a specific format.
     */
    void saveToFile(String filename, String productData);

    /**
     * Displays all the contents of the specified file.
     * This method reads from the file and outputs each line to the console.
     *
     * @param filename The name of the file to read from and display.
     */
    void displayFromFile(String filename);

    /**
     * Loads products from the specified file and returns them as a list of {@code ProductInterface} objects.
     * This method reads the product data from the file and instantiates the appropriate product objects.
     *
     * @param filename The name of the file to load products from.
     * @return An {@code ArrayList} of {@code ProductInterface} objects representing the products loaded from the file.
     */
    ArrayList<ProductInterface> loadProductsFromFile(String filename);
}
