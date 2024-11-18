import java.io.File;
import java.io.IOException;

public class FileCreation {

    /**
     * Ensures that the file {@code products.txt} exists in the root directory of the project.
     * If it doesn't, this method creates the file. If it exists, a message is printed
     * informing the user that the file already exists.
     */
    public static void ensureFileExists() {
        try {
            // Specify the file path (this creates it in the project root directory)
            File file = new File("products.txt");
            // Check if file does not exist
            if (!file.exists()) {
                // Create the file
                if (file.createNewFile()) {
                    System.out.println("File 'products.txt' has been created successfully!");
                } else {
                    System.out.println("File 'products.txt' already exists.");
                }
            }
        } catch (IOException e) {
            // Handle any IO exceptions that occur while creating the file
            System.out.println("An error occurred while creating the file: " + e.getMessage());
        }
    }
}