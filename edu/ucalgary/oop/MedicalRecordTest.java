package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MedicalRecordTest {
    private MedicalRecord record;
    private Location location;

    @Before
    public void setUp() {
        location = new Location("Hospital", "123 Health St.");
        record = new MedicalRecord(location, "Treated for flu", "2023-01-01");
    }

    @Test
    public void validConstructorTest() {
        assertNotNull("MedicalRecord should be successfully created", record);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithInvalidDateFormatTest() {
        new MedicalRecord(location, "Treated for cold", "01-01-2023");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNullLocationTest() {
        new MedicalRecord(null, "Treated for fever", "2023-02-01");
    }

    @Test
    public void getLocationTest() {
        assertEquals("Location should be Hospital", location, record.getLocation());
    }

    @Test
    public void setLocationTest() {
        Location newLocation = new Location("Clinic", "456 Wellness Rd.");
        record.setLocation(newLocation);
        assertEquals("Location should be updated to Clinic", newLocation, record.getLocation());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setLocationWithNullTest() {
        record.setLocation(null);
    }

    @Test
    public void getTreatmentDetailsTest() {
        assertEquals("Treatment details should match", "Treated for flu", record.getTreatmentDetails());
    }

    @Test
    public void setTreatmentDetailsTest() {
        String newDetails = "Treated for cold";
        record.setTreatmentDetails(newDetails);
        assertEquals("Treatment details should be updated", newDetails, record.getTreatmentDetails());
    }

    @Test
    public void getDateOfTreatmentTest() {
        assertEquals("Date of treatment should match", "2023-01-01", record.getDateOfTreatment());
    }

    @Test
    public void setDateOfTreatmentTest() {
        String newDate = "2023-03-01";
        record.setDateOfTreatment(newDate);
        assertEquals("Date of treatment should be updated", newDate, record.getDateOfTreatment());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDateOfTreatmentWithInvalidFormatTest() {
        record.setDateOfTreatment("03-01-2023");
    }

    @Test
    public void testSetAndGetID() {
        // Test setting and getting the ID
        String expectedId = "12345";
        record.setID(expectedId);
        
        String actualId = record.getID();
        assertEquals("The ID retrieved by getID should match the ID set by setID", expectedId, actualId);
    }
}
