package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class SupplyTest {
    private Supply supply;
    private Location location;
    private DisasterVictim victim;



    @Before
    public void setUp() {
        location = new Location("Shelter", "1234 Street");
        victim = new DisasterVictim("John", "Doe", "2023-01-01");
        supply = new Supply("Water", 100, location);
    }

    @Test
    public void constructorAndGetterTest() {
        assertEquals("Water", supply.getType());
        assertEquals(100, supply.getQuantity());
        assertEquals(location, supply.getLocation());
        assertNull(supply.getSupplyFor()); // Initially, supplyFor should be null
    }

    @Test
    public void testObjectCreation() {
        assertNotNull(supply);
    }
	

    @Test
    public void testSetType() {
        supply.setType("Food");
        assertEquals("setType should update the type", "Food", supply.getType());
    }

    @Test
    public void testGetType() {
        assertEquals("getType should return the correct type", "Water", supply.getType());
    }

    @Test
    public void testSetQuantity() {
        supply.setQuantity(20);
        assertEquals("setQuantity should update the quantity", 20, supply.getQuantity());
    }

    @Test
    public void testGetQuantity() {
        assertEquals("getQuantity should return the correct quantity", 100, supply.getQuantity());
    }

    @Test
    public void setLocationTest() {
        Location newLocation = new Location("Warehouse", "6789 Avenue");
        supply.setLocation(newLocation);
        assertEquals(newLocation, supply.getLocation());
        assertFalse(location.getSupplies().contains(supply)); // Ensure supply is removed from old location
        assertTrue(newLocation.getSupplies().contains(supply)); // Ensure supply is added to new location
    }

    @Test
    public void setSupplyForTest() {
        supply.setSupplyFor(victim);
        assertEquals(victim, supply.getSupplyFor());
        assertFalse(location.getSupplies().contains(supply)); // Supply should be removed from location's supplies list
    }

    @Test
    public void addAndRemoveSupplyLocationTest() {
        // Initially, the supply is added to the location in the setUp
        assertTrue(location.getSupplies().contains(supply));

        // Remove the supply from the location
        location.removeSupply(supply);
        assertFalse(location.getSupplies().contains(supply));

        // Add the supply back to the location
        location.addSupply(supply);
        assertTrue(location.getSupplies().contains(supply));
    }
}