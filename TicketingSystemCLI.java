import Core.Logger;
import Core.TicketPool;
import actors.Vendor;
import actors.Customer;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TicketingSystemCLI {
    private static TicketPool ticketPool;
    private static ExecutorService executorService;

    public static void main(String[] args) {
        try {
            Logger.initializeLogger();
            Logger.log("System starting...");

            Scanner scanner = new Scanner(System.in);

            System.out.println("Welcome to the Real-Time Event Ticketing System CLI!");
            Logger.log("Prompting user for system configuration.");

            // Collect configuration
            System.out.print("Enter maximum ticket capacity: ");
            int maxCapacity = scanner.nextInt();

            System.out.print("Enter vendor ticket release rate (ms): ");
            int releaseRate = scanner.nextInt();

            System.out.print("Enter customer retrieval rate (ms): ");
            int retrievalRate = scanner.nextInt();

            // Initialize system
            ticketPool = new TicketPool(maxCapacity);
            executorService = Executors.newCachedThreadPool();

            // Start vendors
            System.out.print("Enter number of vendors: ");
            int vendorCount = scanner.nextInt();
            for (int i = 0; i < vendorCount; i++) {
                executorService.execute(new Vendor(ticketPool, releaseRate));
            }

            // Start customers
            System.out.print("Enter number of customers: ");
            int customerCount = scanner.nextInt();
            for (int i = 0; i < customerCount; i++) {
                executorService.execute(new Customer(ticketPool, retrievalRate));
            }

            // Monitor system
            System.out.println("System is running. Press 'q' to quit.");
            while (!scanner.next().equals("q")) {
                System.out.println("Tickets available: " + ticketPool.getAvailableTickets());
                Logger.log("Tickets available: " + ticketPool.getAvailableTickets());
            }

            // Shut down system
            executorService.shutdownNow();
            Logger.log("System stopped.");
        } catch (Exception e) {
            Logger.logError("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Logger.closeLogger();
        }
    }
}
