import java.io.*;
import java.util.Scanner;
import com.google.gson.Gson;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TicketingSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "config.json";

        // Check if the file exists
        File configFile = new File(filePath);
        Configuration config = null;

        if (configFile.exists()) {
            // Prompt to load configurations from the file
            System.out.println("Configuration file already exists. Do you want to load the settings from the file? (yes/no)");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("yes")) {
                try {
                    config = loadConfiguration(filePath);
                    System.out.println("Loaded Configuration: " + config);
                } catch (IOException e) {
                    System.out.println("Error loading configuration: " + e.getMessage());
                }
            } else {
                // Proceed with new configuration input
                System.out.println("Enter new configuration values.");
                config = getConfigurationFromUser(scanner);
                try {
                    saveConfiguration(filePath, config);
                    System.out.println("New configuration saved.");
                } catch (IOException e) {
                    System.out.println("Error saving configuration: " + e.getMessage());
                }
            }
        } else {
            // File doesn't exist, create a new one
            System.out.println("No configuration file found. Enter configuration settings.");
            config = getConfigurationFromUser(scanner);
            try {
                saveConfiguration(filePath, config);
                System.out.println("Configuration saved.");
            } catch (IOException e) {
                System.out.println("Error saving configuration: " + e.getMessage());
            }
        }

        // Now the user can continue interacting with the system
        continueSystem(scanner, config);

        scanner.close();
    }

    // Method to prompt user for configuration settings
    private static Configuration getConfigurationFromUser(Scanner scanner) {
        System.out.print("Enter total number of tickets: ");
        int totalTickets = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter ticket release rate: ");
        int ticketReleaseRate = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter customer retrieval rate: ");
        int customerRetrievalRate = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter maximum ticket capacity: ");
        int maxTicketCapacity = Integer.parseInt(scanner.nextLine());

        return new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
    }

    // Method to save configuration to a file
    private static void saveConfiguration(String filePath, Configuration config) throws IOException {
        Gson gson = new Gson();
        String jsonConfig = gson.toJson(config);

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            writer.write(jsonConfig);
        }
    }

    // Method to load configuration from a file
    private static Configuration loadConfiguration(String filePath) throws IOException {
        Gson gson = new Gson();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            return gson.fromJson(reader, Configuration.class);
        }
    }

    // Main system operation that continues after loading/saving configurations
    private static void continueSystem(Scanner scanner, Configuration config) {
        boolean running = true;

        while (running) {
            System.out.println("\nTicketing System Menu:");
            System.out.println("1. Display Configuration");
            System.out.println("2. Generate Tickets");
            System.out.println("3. Handle Customers");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Display current configuration
                    System.out.println("Current Configuration: " + config);
                    break;
                case "2":
                    // Generate tickets (Example functionality)
                    System.out.println("Generating tickets...");
                    // You can add logic here to simulate ticket generation
                    break;
                case "3":
                    // Handle customers (Example functionality)
                    System.out.println("Handling customers...");
                    // You can add logic here to simulate customer interactions
                    break;
                case "4":
                    // Exit the system
                    System.out.println("Exiting the system...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
