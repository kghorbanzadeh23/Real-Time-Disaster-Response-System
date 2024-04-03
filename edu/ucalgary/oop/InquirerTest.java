package edu.ucalgary.oop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class InquirerTest {
    private Inquirer inquirer;

    @Before
    public void setUp() {
        inquirer = new Inquirer("John", "Doe", "555-5555", "Info");
        Inquirer.addVictim(new DisasterVictim("Jane", "Doe", "2023-01-01"));
    }

    @After
    public void tearDown() {
        // Clean up static list to ensure test isolation
        Inquirer.clearAllVictims();
    }

    @Test
    public void constructorTest() {
        assertNotNull(inquirer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNullTest() {
        new Inquirer(null, null, null, null);
    }

    @Test
    public void logQueryTest() {
        inquirer.logQuery("Missing person report");
        assertFalse(inquirer.getInteractionLogs().isEmpty());
        assertTrue(inquirer.getInteractionLogs().get(0).contains("John Doe: Missing person report"));
    }

    @Test
    public void searchVictimsByNamePartTest() {
        List<DisasterVictim> results = inquirer.searchVictimsByNamePart("Jane");
        assertEquals(1, results.size());
        assertEquals("Jane", results.get(0).getFirstName());
    }

    @Test
    public void addInteractionTest() {
        inquirer.addInteraction("Checked the database");
        assertEquals("Checked the database", inquirer.getInteractionLogs().get(0));
    }

    @Test
    public void getDetailsTest() {
        assertEquals("John", inquirer.getFirstName());
        assertEquals("Doe", inquirer.getLastName());
        assertEquals("555-5555", inquirer.getServicesPhoneNum());
        assertEquals("Info", inquirer.getInfo());
    }    
}
