package Core;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static BufferedWriter logWriter;

    public static void initializeLogger() throws IOException {
        logWriter = new BufferedWriter(new FileWriter("ticketing_system.log", true));
        log("Logger initialized.");
    }

    public static void log(String message) {
        try {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String logMessage = timestamp + " INFO: " + message;
            System.out.println(logMessage);
            if (logWriter != null) {
                logWriter.write(logMessage);
                logWriter.newLine();
            }
        } catch (IOException e) {
            System.err.println("Failed to write log: " + e.getMessage());
        }
    }

    public static void logError(String message) {
        try {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String logMessage = timestamp + " ERROR: " + message;
            System.err.println(logMessage);
            if (logWriter != null) {
                logWriter.write(logMessage);
                logWriter.newLine();
            }
        } catch (IOException e) {
            System.err.println("Failed to write error log: " + e.getMessage());
        }
    }

    public static void closeLogger() {
        try {
            if (logWriter != null) {
                log("Logger shutting down.");
                logWriter.close();
            }
        } catch (IOException e) {
            System.err.println("Failed to close log file: " + e.getMessage());
        }
    }
}
