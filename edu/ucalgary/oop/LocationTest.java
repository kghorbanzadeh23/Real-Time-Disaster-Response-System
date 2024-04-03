package edu.ucalgary.oop;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class LocationTest {
    private Location location;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        location = new Location("Shelter", "1234 Street");
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut); // Restores standard output
    }

    @Test
    public void constructorAndGetterTest() {
        assertEquals("Shelter", location.getName());
        assertEquals("1234 Street", location.getAddress());
    }

    @Test
    public void setNameTest() {
        location.setName("New Shelter");
        assertEquals("New Shelter", location.getName());
    }

    @Test
    public void setAddressTest() {
        location.setAddress("5678 Avenue");
        assertEquals("5678 Avenue", location.getAddress());
    }

    @Test
    public void addAndRemoveOccupantTest() {
        DisasterVictim victim = new DisasterVictim("John", "Doe", "2023-01-01");
        location.addOccupant(victim);
        assertTrue(location.getOccupants().contains(victim));

        location.removeOccupant(victim);
        assertFalse(location.getOccupants().contains(victim));
    }

    @Test
    public void addAndRemoveSupplyTest() {
        Supply supply = new Supply("Water", 100, location);
        assertTrue(location.getSupplies().contains(supply));

        location.removeSupply(supply);
        assertFalse(location.getSupplies().contains(supply));
    }

    @Test
    public void setOccupantsTest() {
        ArrayList<DisasterVictim> occupants = new ArrayList<>();
        DisasterVictim victim1 = new DisasterVictim("Alice","Jones", "2023-02-01");
        DisasterVictim victim2 = new DisasterVictim("Bob","Smith", "2023-03-01");
        occupants.add(victim1);
        occupants.add(victim2);
        location.setOccupants(occupants);
        assertEquals(2, location.getOccupants().size());
        assertTrue(location.getOccupants().contains(victim1));
        assertTrue(location.getOccupants().contains(victim2));
    }

    @Test
    public void setSuppliesTest() {
        ArrayList<Supply> supplies = new ArrayList<>();
        Supply supply1 = new Supply("Food", 50, location);
        Supply supply2 = new Supply("Medicine", 20, location);
        supplies.add(supply1);
        supplies.add(supply2);
        location.setSupplies(supplies);
        assertEquals(2, location.getSupplies().size());
        assertTrue(location.getSupplies().contains(supply1));
        assertTrue(location.getSupplies().contains(supply2));
    }

    @Test
    public void updateLogTest() {
        location.updateLog("New Shelter", "5678 Avenue");
        String expectedLog = "Updating location log:\n" +
                             "Old Name: Shelter, New Name: New Shelter\n" +
                             "Old Address: 1234 Street, New Address: 5678 Avenue";
        String actualOutput = outContent.toString().trim();
        assertEquals(expectedLog, actualOutput);
        assertEquals("New Shelter", location.getName());
        assertEquals("5678 Avenue", location.getAddress());
    }
}
