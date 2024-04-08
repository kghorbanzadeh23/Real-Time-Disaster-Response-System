package edu.ucalgary.oop;
import java.util.Scanner;


public class App{
public static void main(String[] args) {
    String mode = "central"; // Default mode
boolean modeRecognized = true;

// Check if a mode is provided and recognize the mode
if (args.length > 0) {
    if ("central".equals(args[0].toLowerCase())) {
        mode = "central";
    } else if ("location-based".equals(args[0].toLowerCase())) {
        mode = "location-based";
    } else {
        modeRecognized = false;
        System.out.println("Unrecognized mode. Please enter 'central' or 'location-based'.");
    }
}

if (modeRecognized) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to the Disaster Relief Worker Interface - Mode: " + mode);

    Location defaultLocation = null;

    if ("location-based".equals(mode)) {
        System.out.println("Enter Name of Location:");
        String nameLocation = scanner.nextLine();
        System.out.println("Enter Street of Location:");
        String streetLocation = scanner.nextLine();
        defaultLocation = new Location(nameLocation, streetLocation);
    } else {
        // For central mode, you might have a default or central location pre-defined or not needed
        defaultLocation = new Location("Central", "Central street");  // Replace with actual central location if needed
    }
    
    ReliefWorkerInterface reliefWorkerInterface = new ReliefWorkerInterface(defaultLocation, scanner, mode);
    reliefWorkerInterface.startInterface();
}
}}