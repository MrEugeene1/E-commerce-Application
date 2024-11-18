package ecommerceapplication2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class User {

    // Instance Variables
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    protected String password;
    private String phoneNumber;
    private String address;
    private String role;

    // Static Variables
    private static Map<String, User> userDatabase = new HashMap<>();
    private static final String FILE_PATH = "users.txt";

    /**
     * Constructor for creating a new User object.
     *
     * @param userId The unique ID of the user.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param email The email address of the user.
     * @param password The password for the user account.
     * @param phoneNumber The phone number of the user.
     * @param address The address of the user.
     * @param role The role of the user, either "buyer" or "seller".
     */
    public User(String userId, String firstName, String lastName, String email, String password,
                String phoneNumber, String address, String role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    /**
     * Static block to load users from file at the start of the program.
     */
    static {
        loadUsersFromFile();
    }

    /**
     * Validates if the given email is in the correct format.
     *
     * @param email The email to be validated.
     * @return True if the email format is valid, false otherwise.
     */
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    /**
     * Validates if the given password meets the complexity requirements.
     *
     * @param password The password to be validated.
     * @return True if the password is valid, false otherwise.
     */
    private static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(passwordRegex);
    }

    /**
     * Validates if the given user ID is exactly three unique digits.
     *
     * @param userId The user ID to be validated.
     * @return True if the user ID is valid, throws an exception otherwise.
     */
    private static boolean isValidUserId(String userId) {
        if (!userId.matches("\\d{3}")) {
            throw new IllegalArgumentException("User ID must be exactly three digits.");
        }
        if (userId.charAt(0) == userId.charAt(1) || userId.charAt(0) == userId.charAt(2) || userId.charAt(1) == userId.charAt(2)) {
            throw new IllegalArgumentException("User ID must contain three unique digits.");
        }
        return true;
    }

    /**
     * Validates if the given phone number starts with '0' and is exactly 10 digits long.
     *
     * @param phoneNumber The phone number to be validated.
     * @return True if the phone number is valid, throws an exception otherwise.
     */
    private static boolean isValidPhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches("0\\d{9}")) {
            throw new IllegalArgumentException("Phone number must start with '0' and be exactly 10 digits long.");
        }
        return true;
    }

    /**
     * Validates if the role is either 'buyer' or 'seller'.
     *
     * @param role The role to be validated.
     * @return True if the role is valid, throws an exception otherwise.
     */
    private static boolean isValidRole(String role) {
        if (!role.equalsIgnoreCase("buyer") && !role.equalsIgnoreCase("seller")) {
            throw new IllegalArgumentException("Invalid role. Please enter 'buyer' or 'seller' only.");
        }
        return true;
    }

    /**
     * Registers a new user by validating inputs and saving to the database.
     *
     * @param userId User ID.
     * @param firstName First name.
     * @param lastName Last name.
     * @param email Email address.
     * @param password Password.
     * @param phoneNumber Phone number.
     * @param address Address.
     * @param role User role ("buyer" or "seller").
     * @return True if signup is successful, false otherwise.
     */
    public static boolean signup(String userId, String firstName, String lastName, String email,
                                 String password, String phoneNumber, String address, String role) {
        try {
            // Validate user ID
            isValidUserId(userId);

            // Validate email and check if it already exists
            if (!isValidEmail(email)) {
                System.out.println("Invalid email format. Please enter a valid email address.");
                return false;
            }
            if (userDatabase.containsKey(email)) {
                System.out.println("Email already exists. Please log in or use a different email.");
                return false;
            }

            // Validate password
            if (!isValidPassword(password)) {
                System.out.println("Invalid password. Password must be at least 8 characters, include uppercase, lowercase, digit, and special character.");
                return false;
            }

            // Validate phone number
            isValidPhoneNumber(phoneNumber);

            // Validate role
            isValidRole(role);

            // Create and save the user if all validations pass
            User user = new User(userId, firstName, lastName, email, password, phoneNumber, address, role);
            userDatabase.put(email, user);
            saveUserToFile(user);
            System.out.println("Signup successful! You can now log in.");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Input Error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Saves the user data to a file.
     *
     * @param user The user object to be saved.
     */
    private static void saveUserToFile(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(user.userId + "," + user.firstName + "," + user.lastName + "," +
                         user.email + "," + user.password + "," + user.phoneNumber + "," + 
                         user.address + "," + user.role);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    /**
     * Loads users from a file into the user database.
     */
    private static void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    User user = new User(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]);
                    userDatabase.put(parts[3], user);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file.");
        }
    }

    /**
     * Logs in a user by validating the provided email and password.
     *
     * @param email    User email.
     * @param password User password.
     * @return True if login is successful, false otherwise.
     */
    public static boolean login(String email, String password) {
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format. Please enter a valid email.");
            return false;
        }

        if (email.isEmpty() || password.isEmpty()) {
            System.out.println("Email or password cannot be empty. Please enter both values.");
            return false;
        }

        User user = userDatabase.get(email);
        if (user == null) {
            System.out.println("No account found with this email. Please sign up or try again.");
            return false;
        }

        if (!user.password.equals(password)) {
            System.out.println("Incorrect password. Please try again.");
            return false;
        }

        System.out.println("Login successful. Welcome " + user.firstName + "!");
        return true;
    }
}
