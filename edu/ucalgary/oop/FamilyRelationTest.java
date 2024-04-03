package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FamilyRelationTest {
    private DisasterVictim personOne;
    private DisasterVictim personTwo;
    private FamilyRelation relation;

    @Before
    public void setUp() {
        personOne = new DisasterVictim("John", "Doe", "2023-01-01");
        personTwo = new DisasterVictim("Jane", "Doe", "2023-02-01");
        relation = new FamilyRelation(personOne, personTwo, "Sibling");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNullArguments() {
        new FamilyRelation(null, null, null);
    }

    @Test
    public void testGetters() {
        assertEquals(personOne, relation.getPersonOne());
        assertEquals(personTwo, relation.getPersonTwo());
        assertEquals("Sibling", relation.getRelationshipType());
    }

    @Test
    public void testSetters() {
        DisasterVictim newPerson = new DisasterVictim("Alice", "Doe", "2023-03-01");
        relation.setPersonOne(newPerson);
        assertEquals(newPerson, relation.getPersonOne());

        relation.setRelationshipType("Parent-Child");
        assertEquals("Parent-Child", relation.getRelationshipType());
        
        relation.setPersonTwo(newPerson);
        assertEquals(newPerson, relation.getPersonTwo());
    }

    @Test
    public void testEqualsAndHashCode() {
        FamilyRelation sameRelation = new FamilyRelation(personOne, personTwo, "Sibling");
        assertEquals(relation, sameRelation);

        FamilyRelation differentRelation = new FamilyRelation(personOne, new DisasterVictim("Bob", "Doe", "2023-04-01"), "Parent-Child");
        assertNotEquals(relation, differentRelation);
    }

    @Test
    public void testRemoveRelation() {
        relation.remove();
        assertFalse(personOne.getFamilyConnections().contains(relation));
        assertFalse(personTwo.getFamilyConnections().contains(relation));
    }
}
