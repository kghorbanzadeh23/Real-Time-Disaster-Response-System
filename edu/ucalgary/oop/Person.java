package edu.ucalgary.oop;
import java.time.LocalDate;
import java.time.Period;

public class Person {
    private String firstName;
    private String lastName;
    private String dateOfBirth; // Assuming dateOfBirth is in ISO format (yyyy-MM-dd)
    private int age;

    // Constructor
    public Person(String firstName, String lastName, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.age = calculateAge(); // Initialize age based on dateOfBirth
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        this.age = calculateAge(); // Recalculate age when dateOfBirth is updated
    }

    // Calculate the age based on dateOfBirth
    private int calculateAge() {
        LocalDate birthDate = LocalDate.parse(this.dateOfBirth);
        LocalDate currentDate = LocalDate.now();
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}
