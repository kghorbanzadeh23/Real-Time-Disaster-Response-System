package edu.ucalgary.oop;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class DisasterVictim implements DisasterVictimsEntryInterface{
    private static int counter = 0;
    private static GenderRepository genderRepository = new GenderRepository();
    private static List<String> validGenders;
    private List<DisasterVictim> allVictims = new ArrayList<>();
    private  List<Location> allLocations;

    private Set<DietaryPlan> dietaryRestrictions = new HashSet<>(); // Use a Set to avoid duplicates
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private int assignedSocialID;
    private ArrayList<FamilyRelation> familyConnections = new ArrayList<>();
    private ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
    private ArrayList<Supply> personalBelongings = new ArrayList<>();
    private String entryData;
    private String gender;
    private String comments;
    private Integer age;

    public DisasterVictim(String firstName, String lastName, String entryData) {
        this.firstName = firstName;
        this.lastName = lastName;
        if (validGenders == null) {
            validGenders = genderRepository.readFile("../GenderOptions.txt");
        }
        if (!isValidDateFormat(entryData)) {
            throw new IllegalArgumentException("Invalid date format for entry date. Expected format: YYYY-MM-DD");
        }
        this.entryData = entryData;
        this.assignedSocialID = generateSocialID();
        this.allLocations= new ArrayList<>();
    }
    public void setEntryDate(String entryDate){
        this.entryData = entryData;
    }
    public List<Location> getLocations(){
        return new ArrayList<>(this.allLocations);
    }

    public void setAssignedSocialID(int id){
        this.assignedSocialID = id;
    }

    private static int generateSocialID() {
        counter++;
        return counter;
    }

    private static boolean isValidDateFormat(String date) {
    if (date == null) {
        return false;
    }
    String dateFormatPattern = "^\\d{4}-\\d{2}-\\d{2}$";
    return date.matches(dateFormatPattern);
    }
    private static String validateDateFormat(String date) {
        if (!isValidDateFormat(date)) {
            throw new IllegalArgumentException("Invalid date format for entry date. Expected format: YYYY-MM-DD");
        }
        return date;
    }

    public void setDateOfBirth(String dateOfBirth) {
        if (this.age != null) {
            throw new IllegalArgumentException("Cannot set both birthdate and approximate age.");
        }
        this.dateOfBirth = validateDateFormat(dateOfBirth);
    }

    public void setAge(Integer age) {
        if (this.dateOfBirth != null && !this.dateOfBirth.isEmpty()) {
            throw new IllegalArgumentException("Cannot set both approximate age and birthdate.");
        }
        this.age = age;
    }
    
    public void setGender(String gender) {
        if (validGenders.contains(gender)) {
            this.gender = gender;
        } else {
            throw new IllegalArgumentException("Invalid gender: " + gender);
        }
    }

    public void addDietaryRestriction(DietaryPlan restriction) {
        dietaryRestrictions.add(restriction);
    }

    public void removeDietaryRestriction(DietaryPlan restriction) {
        dietaryRestrictions.remove(restriction);
    }

    public Set<DietaryPlan> getDietaryRestrictions() {
        return new HashSet<>(dietaryRestrictions); // Return a copy to maintain encapsulation
    }

    // Method to set multiple dietary restrictions at once
    public void setDietaryRestrictions(Set<DietaryPlan> restrictions) {
        this.dietaryRestrictions = new HashSet<>(restrictions); // Copy to maintain encapsulation
    }
  
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAssignedSocialID() {
        return assignedSocialID;
    }

    public Integer getAge(){
        return age;
    }
    
    public ArrayList<FamilyRelation> getFamilyConnections() {
        return familyConnections;
    }

    public ArrayList<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public ArrayList<Supply> getPersonalBelongings() {
        return personalBelongings;
    }

    // The add and remove methods remain correct.
    
    public void setFamilyConnections(ArrayList<FamilyRelation> connections) {
        this.familyConnections.clear();
        this.familyConnections.addAll(connections); // No need for a loop
    }

    public void setMedicalRecords(ArrayList<MedicalRecord> records) {
        this.medicalRecords.clear();
        this.medicalRecords.addAll(records); // No need for a loop
    }

    public void setPersonalBelongings(ArrayList<Supply> belongings) {
        this.personalBelongings = belongings;
    }

    public void addPersonalBelonging(Supply supply) {
        // Ensure the list is initialized
        if (this.personalBelongings == null) {
            this.personalBelongings = new ArrayList<>(); // Initialize the list if it's null
        }
        this.personalBelongings.add(supply); // Use ArrayList's add method
    }

    // Remove a Supply from personalBelongings, assuming it only appears once
    public void removePersonalBelonging(Supply unwantedSupply) {
        if (this.personalBelongings != null) {
            this.personalBelongings.remove(unwantedSupply); // Use ArrayList's remove method
        }
    }

    public void addFamilyConnection(FamilyRelation relation) {
        if (!this.familyConnections.contains(relation)) {
            this.familyConnections.add(relation);
        }
    }
    
    public void removeFamilyConnection(FamilyRelation relation) {
        this.familyConnections.remove(relation);
    }

    

    public String getEntryDate() {
        return entryData;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments =  comments;
    }

    public String getGender() {
        return gender;
    }

    public String getFullName() {
        String fullName = "";
        if (firstName != null && !firstName.isEmpty()) {
            fullName += firstName;
        }
        if (lastName != null && !lastName.isEmpty()) {
            // Add a space before the last name if the first name is not empty
            if (!fullName.isEmpty()) {
                fullName += " ";
            }
            fullName += lastName;
        }
        return fullName;
    }

    
    public void addDisasterVictim(DisasterVictim victim) {
        allVictims.add(victim);
    }

    public DisasterVictim updateDisasterVictim(DisasterVictim original, DisasterVictim updatedVictim) {
            original.setFirstName(updatedVictim.getFirstName());
            original.setLastName(updatedVictim.getLastName());
            original.setEntryDate(updatedVictim.getEntryDate());
            return original;

    }

    public DisasterVictim findDisasterVictimById(int id) {
        for (DisasterVictim victim : allVictims) {
            if (victim.getAssignedSocialID() == id) {
                return victim;
            }
        }
        return null; // Or throw an exception
    }

    public void addMedicalRecord(MedicalRecord record) {
        if (this.medicalRecords == null) {
            this.medicalRecords = new ArrayList<>(); // Initialize if null
        }
        this.medicalRecords.add(record); // Directly add the record
    }

    public void updateMedicalRecord(MedicalRecord updatedRecord) {
        for (int i = 0; i < this.medicalRecords.size(); i++) {
            if (this.medicalRecords.get(i).equals(updatedRecord)) {
                this.medicalRecords.set(i, updatedRecord);
                return;
            }
        }
    }

    public void addLocation(Location location) {
        allLocations.add(location);
    }

    public void updateLocation(Location updatedLocation) {
        for (int i = 0; i < allLocations.size(); i++) {
            if (allLocations.get(i).getName().equals(updatedLocation.getName())) {
                allLocations.set(i, updatedLocation);
                return;
            }
        }
    }

    public Location findLocationByName(String name) {
        for (Location location : allLocations) {
            if (location.getName().equals(name)) {
                return location;
            }
        }
        return null; // Or consider throwing an exception
    }

    public ArrayList<Location> listAllLocations() {
        return new ArrayList<>(allLocations); // Return a copy for encapsulation
    }

    public ArrayList<MedicalRecord> listMedicalRecordsByLocation(Location location) {
        ArrayList<MedicalRecord> records = new ArrayList<>();
        for (DisasterVictim victim : location.getOccupants()) {
            records.addAll(victim.getMedicalRecords());
        }
        return records;
    }

}