package edu.ucalgary.oop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReliefWorkerInterface {
    private Scanner scanner;
    private JDBCDemo jdbcDemo;
    private Location currentLocation;
    private String mode;
    private List<Location> locations = new ArrayList<>();

    private void enterDisasterVictim() {
        System.out.println("Enter Disaster Victim Details:");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Date: ");
        String date = scanner.nextLine();
        // Assume additional details are collected here as needed

        // Creating a new DisasterVictim instance
        DisasterVictim victim = new DisasterVictim(firstName, lastName , date);
        jdbcDemo.insertDisasterVictim(firstName, lastName, date);
        
        // Adding the newly created DisasterVictim to the currentLocation
        currentLocation.addOccupant(victim);
        
        System.out.println("Disaster Victim added successfully.");
    }

    private void enterFamilyRelation() {
        System.out.println("Entering Family Relation:");
    System.out.print("Enter the first person's first name: ");
    String firstName1 = scanner.nextLine();
    System.out.print("Enter the first person's last name: ");
    String lastName1 = scanner.nextLine();
    System.out.print("Enter the second person's first name: ");
    String firstName2 = scanner.nextLine();
    System.out.print("Enter the second person's last name: ");
    String lastName2 = scanner.nextLine();
    System.out.print("Enter the relationship type (e.g., sibling, parent): ");
    String relationshipType = scanner.nextLine();

    // Finding the DisasterVictim objects
    DisasterVictim personOne = jdbcDemo.findDisasterVictimByName(firstName1, lastName1);
    DisasterVictim personTwo = jdbcDemo.findDisasterVictimByName(firstName2, lastName2);


    if (personOne != null && personTwo != null) {
        // Assuming FamilyRelation constructor: FamilyRelation(DisasterVictim personOne, DisasterVictim personTwo, String relationshipType)
        FamilyRelation relation = new FamilyRelation(personOne, personTwo, relationshipType);
        jdbcDemo.insertFamilyRelation(firstName1, lastName1, firstName2, lastName2, relationshipType);
        System.out.println("Family relation added successfully.");
    } else {
        System.out.println("Error: Could not find one or both of the specified Disaster Victims.");
    }
    }
    private void enterMedicalRecord() {
        System.out.println("Entering Medical Record:");
    
        System.out.print("Enter treatment details: ");
        String treatmentDetails = scanner.nextLine();
    
        System.out.print("Enter the date of treatment (YYYY-MM-DD): ");
        String dateOfTreatment = scanner.nextLine();
    
        if (isValidDateFormat(dateOfTreatment)) {
            MedicalRecord record = new MedicalRecord(currentLocation, treatmentDetails, dateOfTreatment);
            jdbcDemo.insertMedicalRecord(currentLocation.getName(), treatmentDetails, dateOfTreatment);

            System.out.println("Medical record added successfully.");
        } else {
            System.out.println("Error: Invalid input.");
        }
    }


    private boolean isValidDateFormat(String date) {
    try {
        LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        return true;
    } catch (Exception e) {
        return false;
    }
}


private void searchInquiries() {
    System.out.print("Enter the partial name to search for inquiries: ");
    String partialName = scanner.nextLine();

    List<String> inquiriesInfo = jdbcDemo.searchInquiriesByPartialName(partialName);
    if (inquiriesInfo.isEmpty()) {
        System.out.println("No inquirer found with the provided partial name.");
    } else {
        System.out.println("Inquiry Information:");
        for (String info : inquiriesInfo) {
            System.out.println(info);
        }
    }
}

private void logInquiry() {
    System.out.print("Enter Inquirer ID (1-9): ");
    int inquirerId = scanner.nextInt();  // Note: You might want to add input validation here
    scanner.nextLine(); // Consume the newline left by nextInt()

    if (inquirerId < 1 || inquirerId > 9) {
        System.out.println("Invalid ID. Please enter a number between 1 to 9.");
        return; // Exit the method if the input is not valid
    }
    System.out.print("Enter details of the inquiry: ");
    String details = scanner.nextLine();

    jdbcDemo.logNewInquiry(inquirerId, details);
    System.out.println("The inquiry has been logged successfully.");
}
    public ReliefWorkerInterface(Location location, Scanner scanner, String mode) {
        this.scanner = scanner;
        this.jdbcDemo = new JDBCDemo();
        this.currentLocation = location;
        this.mode = mode;
    }
    

    public void startInterface() {
        System.out.println("Welcome to the Disaster Relief Worker Interface");
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Enter new Disaster Victim");
            System.out.println("2. Enter Family Relation");
            System.out.println("3. Enter Medical Record for a Victim");
            System.out.println("4. Search Inquiries");
            System.out.println("5. Log Inquiry");
            System.out.println("6. Exit");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    enterDisasterVictim();
                    break;
                case "2":
                    enterFamilyRelation();
                    break;
                case "3":
                    enterMedicalRecord();
                    break;
                case "4":
                    searchInquiries();
                    break;
        
                case "5":
                    logInquiry();
                    break;

                case "6":
                    System.out.println("Exiting...");
                    return;
            
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        
    }

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
    }
}
