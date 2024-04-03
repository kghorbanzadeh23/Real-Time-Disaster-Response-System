package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Inquirer implements InquirerServiceInterface {
    private final String FIRST_NAME;
    private final String LAST_NAME;
    private final String INFO;
    private final String SERVICES_PHONE;
    private List<String> interactionLogs;
    private static List<DisasterVictim> allVictims = new ArrayList<>();


    public Inquirer(String firstName, String lastName, String phone, String info) {
        if (firstName == null || lastName == null || phone == null || info == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        this.FIRST_NAME = firstName;
        this.LAST_NAME = lastName;
        this.SERVICES_PHONE = phone;
        this.INFO = info;
        this.interactionLogs = new ArrayList<>(); // Initialize the interaction log list
    }

    @Override
    public void logQuery(String query) {
        String logEntry = FIRST_NAME + " " + LAST_NAME + ": " + query;
        interactionLogs.add(logEntry);
        // Consider logging this information to a centralized log manager if required
    }

    @Override
    public List<DisasterVictim> searchVictimsByNamePart(String firstName) {
        return allVictims.stream()
                .filter(victim -> victim.getFullName().toLowerCase().contains(firstName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static void addVictim(DisasterVictim victim) {
        allVictims.add(victim);
    }

    public static void clearAllVictims() {
        allVictims.clear();    
    }

    // Method to add an interaction to the log
    public void addInteraction(String interactionDetail) {
        if (interactionDetail == null) {
            throw new IllegalArgumentException("Interaction detail cannot be null");
        }
        interactionLogs.add(interactionDetail);
    }

    // Method to get the interaction log
    public List<String> getInteractionLogs() {
        return new ArrayList<>(interactionLogs); // Return a copy to maintain encapsulation
    }

    // Getters
    public String getFirstName() { return FIRST_NAME; }
    public String getLastName() { return LAST_NAME; }
    public String getServicesPhoneNum() { return SERVICES_PHONE; }
    public String getInfo() { return INFO; }
}
