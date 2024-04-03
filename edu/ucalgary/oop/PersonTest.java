package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;

public class PersonTest {
    private Person person;
    private String firstName = "John";
    private String lastName = "Doe";
    private String dateOfBirth; // Set this to a specific date for testing age calculation

    @Before
    public void setUp() {
        // Initialize dateOfBirth to a date that makes the age calculation predictable
        LocalDate birthDate = LocalDate.now().minusYears(25); // Example: 25 years ago
        dateOfBirth = birthDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        
        person = new Person(firstName, lastName, dateOfBirth);
    }

    @Test
    public void testPersonConstructor() {
        assertEquals("First name should match", firstName, person.getFirstName());
        assertEquals("Last name should match", lastName, person.getLastName());
        assertEquals("Date of birth should match", dateOfBirth, person.getDateOfBirth());
        assertEquals("Age should be calculated correctly", 25, person.getAge());
    }

    @Test
    public void testSettersAndGetters() {
        String newFirstName = "Jane";
        String newLastName = "Smith";
        LocalDate newBirthDate = LocalDate.now().minusYears(30);
        String newDateOfBirth = newBirthDate.format(DateTimeFormatter.ISO_LOCAL_DATE);

        person.setFirstName(newFirstName);
        person.setLastName(newLastName);
        person.setDateOfBirth(newDateOfBirth);

        assertEquals("First name should be updated", newFirstName, person.getFirstName());
        assertEquals("Last name should be updated", newLastName, person.getLastName());
        assertEquals("Date of birth should be updated", newDateOfBirth, person.getDateOfBirth());
        assertEquals("Age should be updated correctly", 30, person.getAge());
    }

    @Test
    public void testAgeCalculation() {
        // Assuming the setUp method sets the birthdate to 25 years ago
        int expectedAge = Period.between(LocalDate.parse(dateOfBirth), LocalDate.now()).getYears();
        assertEquals("Age should be calculated correctly", expectedAge, person.getAge());
        
        // Testing age recalculation upon setting a new date of birth
        LocalDate newBirthDate = LocalDate.now().minusYears(20);
        person.setDateOfBirth(newBirthDate.toString());
        expectedAge = Period.between(newBirthDate, LocalDate.now()).getYears();
        assertEquals("Age should be recalculated correctly", expectedAge, person.getAge());
    }
}