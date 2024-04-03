package edu.ucalgary.oop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ReliefServiceTest {
    private Inquirer inquirer;
    private DisasterVictim missingPerson;
    private String dateOfInquiry;
    private String infoProvided;
    private Location lastKnownLocation;
    private ReliefService service;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        inquirer = new Inquirer("Jane", "Doe", "555-0101", "janedoe@example.com");
        missingPerson = new DisasterVictim("John", "Doe", "2023-01-01");
        dateOfInquiry = "2023-01-15";
        infoProvided = "Last seen near the river";
        lastKnownLocation = new Location("Riverbank", "123 River Road");
        service = new ReliefService(inquirer, missingPerson, dateOfInquiry, infoProvided, lastKnownLocation);

        System.setOut(new PrintStream(outContent)); // Redirects standard output
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut); // Restores standard output
    }

    @Test
    public void constructorTest() {
        assertNotNull("ReliefService object should not be null", service);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorValidationTest() {
        new ReliefService(null, missingPerson, dateOfInquiry, infoProvided, lastKnownLocation);
    }

    @Test
    public void getDateOfInquiryTest() {
        assertEquals("Date of inquiry should match the one provided", dateOfInquiry, service.getDateOfInquiry());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDateOfInquiryInvalidFormatTest() {
        service.setDateOfInquiry("01-15-2023");
    }

    @Test
    public void getAndSetInquirerTest() {
        Inquirer newInquirer = new Inquirer("Alice", "Smith", "555-0202", "alicesmith@example.com");
        service.setInquirer(newInquirer);
        assertEquals("Inquirer should be updated to new inquirer", newInquirer, service.getInquirer());
    }

    @Test
    public void getAndSetMissingPersonTest() {
        DisasterVictim newVictim = new DisasterVictim("Alice", "Smith", "2023-02-01");
        service.setMissingPerson(newVictim);
        assertEquals("Missing person should be updated to new victim", newVictim, service.getMissingPerson());
    }

    @Test
    public void getInfoProvidedTest() {
        assertEquals("Info provided should match the one provided", infoProvided, service.getInfoProvided());
    }

    @Test
    public void getAndSetLastKnownLocationTest() {
        Location newLocation = new Location("Park", "456 Park Ave");
        service.setLastKnownLocation(newLocation);
        assertEquals("Last known location should be updated to new location", newLocation, service.getLastKnownLocation());
    }

    @Test
    public void getLogDetailsTest() {
        String expectedLog = "Inquirer: Jane, Missing Person: John, Date of Inquiry: 2023-01-15, Info Provided: Last seen near the river, Last Known Location: Riverbank";
        assertEquals("Log details should match expected output", expectedLog, service.getLogDetails());
    }

    @Test
    public void printLogDetailTest() {
    service.printLogDetails();
    String expectedOutput = "Inquirer: Jane, Missing Person: John, Date of Inquiry: 2023-01-15, Info Provided: Last seen near the river, Last Known Location: Riverbank\n".trim();
    String actualOutput = outContent.toString().trim();
    assertEquals("Printed log details should match expected output", expectedOutput, actualOutput);
}
}
