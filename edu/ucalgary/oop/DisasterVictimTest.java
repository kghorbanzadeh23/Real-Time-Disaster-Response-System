package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


public class DisasterVictimTest {
    private DisasterVictim victim;
    private MedicalRecord record;
    private Location location;
    private Supply supply;

    @Before
    public void setUp() {
        victim = new DisasterVictim("John", "Doe", "2023-01-01");
        location = new Location("Test Location", "123 Test Address");
        record = new MedicalRecord(location, "Test Treatment", "2023-01-01");
        supply = new Supply("Water", 10, location);
        
    }

    @Test
    public void testConstructorAndBasicGetters() {
        assertNotNull(victim);
        assertEquals("John", victim.getFirstName());
        assertEquals("Doe", victim.getLastName());
        assertEquals("2023-01-01", victim.getEntryDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDateFormat() {
        new DisasterVictim("Jane", "Doe", "01-01-2023");
    }

    @Test
    public void testDietaryRestrictions() {
        victim.addDietaryRestriction(DietaryPlan.GFML);
        assertTrue(victim.getDietaryRestrictions().contains(DietaryPlan.GFML));
        victim.removeDietaryRestriction(DietaryPlan.GFML);
        assertFalse(victim.getDietaryRestrictions().contains(DietaryPlan.GFML));
    }

    @Test
    public void testFamilyConnections() {
        DisasterVictim relative = new DisasterVictim("Jane", "Doe", "2023-02-01");
        FamilyRelation relation = new FamilyRelation(victim, relative, "Sibling");
        victim.addFamilyConnection(relation);
        assertTrue(victim.getFamilyConnections().contains(relation));
        victim.removeFamilyConnection(relation);
        assertFalse(victim.getFamilyConnections().contains(relation));
    }

    @Test
    public void testMedicalRecords() {
        MedicalRecord record = new MedicalRecord(new Location("Hospital", "123 Street"),"Condition", "2023-03-01");
        victim.addMedicalRecord(record);
        assertTrue(victim.getMedicalRecords().contains(record));
    }

    @Test
    public void testPersonalBelongings() {
        Supply supply = new Supply("Water", 100, new Location("Shelter", "1234 Street"));
        victim.addPersonalBelonging(supply);
        assertTrue(victim.getPersonalBelongings().contains(supply));
        victim.removePersonalBelonging(supply);
        assertFalse(victim.getPersonalBelongings().contains(supply));
    }
    @Test
    public void testGenderSettingAndGetting() {
        DisasterVictim victim = new DisasterVictim("John", "Doe", "2023-01-01");
        String expectedGender = "girl"; // Ensure "Male" is in GenderOptions.txt
        victim.setGender(expectedGender);
        assertThat(victim.getGender(), is(expectedGender));
    }

    @Test
public void testSettingDateOfBirth() {
    victim.setFirstName("Kamand");
    assertEquals("Kamand", victim.getFirstName());
    victim.setLastName("Ghorbanzadeh");
    assertEquals("Ghorbanzadeh", victim.getLastName());
    victim.setDateOfBirth("2000-01-01");
    assertEquals("2000-01-01", victim.getDateOfBirth());
}

@Test
public void testSettingAge() {
    DisasterVictim victimWithAge = new DisasterVictim("John", "Doe", "2023-01-01");
    victimWithAge.setFirstName("Kamand");
    assertEquals("Kamand", victimWithAge.getFirstName());
    victimWithAge.setLastName("Ghorbanzadeh");
    assertEquals("Ghorbanzadeh", victimWithAge.getLastName());
    victimWithAge.setAge(23);
    assertEquals(23, (int) victimWithAge.getAge());
}

    @Test
    public void testAddDisasterVictim() {
        DisasterVictim newVictim = new DisasterVictim("Jane", "Doe", "2023-02-01");
        victim.addDisasterVictim(newVictim);
        assertThat(victim.findDisasterVictimById(newVictim.getAssignedSocialID()), is(notNullValue()));
    }

    @Test
    public void testUpdateDisasterVictim() {
        DisasterVictim updatedVictim = new DisasterVictim("Updated", "Victim", "2023-02-01");
        victim.addDisasterVictim(victim);
        updatedVictim.setAssignedSocialID(victim.getAssignedSocialID());
        victim.updateDisasterVictim(victim, updatedVictim);

        DisasterVictim foundVictim = victim.findDisasterVictimById(victim.getAssignedSocialID());
        assertEquals("Names should match after update", "Updated", foundVictim.getFirstName());
    }

    @Test
    public void testAddMedicalRecord() {
        victim.addMedicalRecord(record);
        assertFalse("Medical records should not be empty after adding a record", victim.getMedicalRecords().isEmpty());
    }

    @Test
public void testUpdateMedicalRecord() {
    // Set the ID on the original record
    record.setID("someUniqueIdentifier");
    victim.addMedicalRecord(record);

    // Create an updated record with the same ID
    MedicalRecord updatedRecord = new MedicalRecord(location, "Updated Treatment", "2023-01-02");
    updatedRecord.setID("someUniqueIdentifier"); // Same ID as the original record

    victim.updateMedicalRecord(updatedRecord);

    MedicalRecord foundRecord = victim.getMedicalRecords().get(0);
    assertEquals("Treatment details should match after update", "Updated Treatment", foundRecord.getTreatmentDetails());
}

    @Test
    public void testAddLocation() {
        victim.addLocation(location);
        assertFalse("Location list should not be empty after adding a location", victim.getLocations().isEmpty());
    }

    @Test
    public void testUpdateLocation() {
        Location updatedLocation = new Location("Updated Location", "1234 Updated Address");
        victim.addLocation(location);
        updatedLocation.setName(location.getName()); // Assuming names are used as unique identifiers
        victim.updateLocation(updatedLocation);

        Location foundLocation = victim.findLocationByName(updatedLocation.getName());
        assertEquals("Addresses should match after update", "1234 Updated Address", foundLocation.getAddress());
    }

    @Test
    public void testListAllLocations() {
        victim.addLocation(location);
        List<Location> locations = victim.listAllLocations();
        assertEquals("There should be one location in the list", 1, locations.size());
    }

    
    @Test
    public void testFindDisasterVictimById_NotFound() {
        assertThat(victim.findDisasterVictimById(999), is(nullValue()));
    }
}
