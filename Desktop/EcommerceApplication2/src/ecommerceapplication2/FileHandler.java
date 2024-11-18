package ecommerceapplication2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileHandler {

    /**
     * Checks if a product with the specified product ID exists in the given
     * file.
     *
     * @param fileName The name of the file to search in.
     * @param productId The product ID to look for.
     * @return {@code true} if the product ID exists in the file, otherwise
     * {@code false}.
     */
    public static boolean productExists(String fileName, String productId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.split(",")[1].equals(productId)) {
                    return true; // Product ID found
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Product ID not found
    }

    /**
     * Saves the given product data to a specified file. The data is appended to
     * the end of the file.
     *
     * @param filename The name of the file to save the data to.
     * @param productData The product data to write to the file.
     */
    public static void saveToFile(String filename, String productData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(productData);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Displays all the product data from the specified file.
     *
     * @param filename The name of the file to read from.
     */
    public static void displayFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("\nList of Products:");

            while ((line = reader.readLine()) != null) {
                String[] productDetails = line.split(",");
                if (productDetails.length >= 4) {
                    String productType = productDetails[0];
                    if (productType.equals("3")) {
                        productType = "DigitalProduct";
                    } else if (productType.equals("4")) {
                        productType = "DigitalProduct";
                    }

                    String productId = productDetails[1];
                    String name = productDetails[2];
                    double price = Double.parseDouble(productDetails[3]);
                    switch (productType) {
                        case "PhysicalProduct":
                            if (productDetails.length >= 6) {
                                double weight = Double.parseDouble(productDetails[4]);
                                String dimensions = productDetails[5];
                                System.out.println("Physical Product - " + name);
                                System.out.println("Product ID: " + productId);
                                System.out.println("Price: $" + price);
                                System.out.println("Weight: " + weight + " kg");
                                System.out.println("Dimensions: " + dimensions);
                            } else {
                                System.out.println("Incomplete data for Physical Product.");
                            }
                            break;

                        case "DigitalProduct":
                            if (productDetails.length >= 6) {
                                String fileSize = productDetails[4];
                                String format = productDetails[5];
                                System.out.println("Digital Product - " + name);
                                System.out.println("Product ID: " + productId);
                                System.out.println("Price: $" + price);
                                System.out.println("File Size: " + fileSize);
                                System.out.println("Format: " + format);
                            } else {
                                System.out.println("Incomplete data for Digital Product.");
                            }
                            break;

                        default:
                            System.out.println("Unknown product type: " + productType);
                            break;
                    }
                    System.out.println(); // Blank line between products for readability
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No product file found.");
        } catch (IOException e) {
            System.out.println("Error reading products: " + e.getMessage());
        }
    }

    /**
     * Deletes the product with the specified product ID from the given file. A
     * temporary file is used to rewrite the content, excluding the product to
     * delete. The original file is replaced with the updated content.
     *
     * @param filename The name of the file to delete the product from.
     * @param productId The product ID to delete.
     */
    public static void deleteFromFile(String filename, String productId) {
        File inputFile = new File(filename);
        File tempFile = new File("temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            boolean productFound = false;

            while ((currentLine = reader.readLine()) != null) {
                // Check if the current line contains the product ID
                if (currentLine.split(",")[1].equals(productId)) {
                    productFound = true;
                    continue; // Skip this line to delete it
                }
                writer.write(currentLine);
                writer.newLine();
            }

            if (productFound) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("Product ID not found.");
            }

        } catch (IOException e) {
            System.out.println("Error processing the file: " + e.getMessage());
        }

        // Replace original file with temp file
        if (!inputFile.delete()) {
            System.out.println("Could not delete original file.");
        } else if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename temp file.");
        }
    }

    /**
     * Loads products from a file and returns them as a list of Product objects.
     *
     * @param filename The name of the file to load products from.
     * @return An ArrayList of Product objects.
     */
    public static ArrayList<Product> loadProductsFromFile(String filename) {
    ArrayList<Product> products = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            System.out.println("Reading line: " + currentLine); // Debug line to see each line
            String[] productDetails = currentLine.split(",");
            if (productDetails.length >= 4) {
                String productType = productDetails[0].trim();
                String productId = productDetails[1].trim();
                String name = productDetails[2].trim();
                double price = Double.parseDouble(productDetails[3].trim());

                if (productType.equals("PhysicalProduct")) {
                    if (productDetails.length >= 6) {
                        double weight = Double.parseDouble(productDetails[4].trim());
                        String dimensions = productDetails[5].trim();
                        PhysicalProduct physicalProduct = new PhysicalProduct(productId, name, price, weight, dimensions);
                        products.add(physicalProduct);
                    } else {
                        System.out.println("Incomplete data for PhysicalProduct with ID " + productId);
                    }
                } else if (productType.equals("DigitalProduct")) {
                    if (productDetails.length >= 6) {
                        String fileSize = productDetails[4].trim();  // Treat fileSize as a String
                        String format = productDetails[5].trim();
                        DigitalProduct digitalProduct = new DigitalProduct(productId, name, price, fileSize, format);
                        products.add(digitalProduct);
                    } else {
                        System.out.println("Incomplete data for DigitalProduct with ID " + productId);
                    }
                } else {
                    System.out.println("Unknown product type: " + productType);
                }
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println("No product file found.");
    } catch (IOException e) {
        System.out.println("Error reading products: " + e.getMessage());
    } catch (NumberFormatException e) {
        System.out.println("Error in product data format: " + e.getMessage());
    }
    return products;
}
}