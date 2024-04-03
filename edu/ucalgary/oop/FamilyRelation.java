package edu.ucalgary.oop;
import java.util.Objects;

public class FamilyRelation {
    private DisasterVictim personOne;
    private DisasterVictim personTwo;
    private String relationshipType;


    // Constructor
    public FamilyRelation(DisasterVictim personOne, DisasterVictim personTwo, String relationshipType) {
        if (personOne == null || personTwo == null || relationshipType == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        this.personOne = personOne;
        this.personTwo = personTwo;
        this.relationshipType = relationshipType;

        // Directly add this relationship to both DisasterVictims to ensure two-sided consistency
        personOne.addFamilyConnection(this);
        personTwo.addFamilyConnection(this);
    }

    // Getter and setter for personOne
    public DisasterVictim getPersonOne() {
        return personOne;
    }

    public void setPersonOne(DisasterVictim personOne) {
        // Update personOne and ensure the relationship is reflected in both DisasterVictims
        this.personOne.removeFamilyConnection(this);
        this.personOne = personOne;
        this.personOne.addFamilyConnection(this);
        personTwo.addFamilyConnection(this);
    }

    // Getter and setter for relationshipTo
    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    // Getter and setter for personTwo
    public DisasterVictim getPersonTwo() {
        return personTwo;
    }

    public void setPersonTwo(DisasterVictim personTwo) {
        // Update personTwo and ensure the relationship is reflected in both DisasterVictims
        this.personTwo.removeFamilyConnection(this);
        this.personTwo = personTwo;
        this.personTwo.addFamilyConnection(this);
        personOne.addFamilyConnection(this);
    }

    public void remove() {
        // Remove this relationship from both DisasterVictims to maintain consistency
        personOne.removeFamilyConnection(this);
        personTwo.removeFamilyConnection(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamilyRelation that = (FamilyRelation) o;
        return (Objects.equals(personOne, that.personOne) && Objects.equals(personTwo, that.personTwo)) ||
               (Objects.equals(personOne, that.personTwo) && Objects.equals(personTwo, that.personOne));
    }

    @Override
    public int hashCode() {
        return Objects.hash(personOne, personTwo) + Objects.hash(personTwo, personOne);
    }

}