package ecommerceapplication2;

/**
 * The {@code UserInterface} interface defines the contract for user-related 
 * functionalities within the e-commerce application. It includes methods for 
 * displaying available products to the user and logging in with an email and password.
 * 
 * <p>This interface can be implemented by different types of user classes 
 * (e.g., Buyer and Seller) to ensure consistent behavior for user actions 
 * across the application.
 * 
 * <p>Usage example:
 * <pre>
 *     public class Seller implements UserInterface {
 *         // Implementation of methods
 *     }
 * </pre>
 * 
 * <p>Implementing this interface allows classes to provide their own 
 * implementations for viewing products and handling user authentication.
 * 
 * @author user
 */
public interface UserInterface {

    /**
     * Displays the list of available products to the user.
     * The specific implementation may vary depending on the user type (e.g., Buyer or Seller).
     * This method provides a way for users to view product information.
     */
    void displayProducts();

    /**
     * Authenticates the user based on the provided email and password.
     *
     * @param email    The user's email address, used as the unique identifier.
     * @param password The user's password, used for authentication.
     * @return {@code true} if the login is successful; {@code false} otherwise.
     */
    boolean login(String email, String password);
}
