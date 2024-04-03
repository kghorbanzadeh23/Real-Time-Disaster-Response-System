package edu.ucalgary.oop;
import java.util.ArrayList;


public interface DisasterVictimsEntryInterface {
    // Methods to handle disaster victim information
    void addDisasterVictim(DisasterVictim victim);
    DisasterVictim updateDisasterVictim(DisasterVictim original, DisasterVictim updatedVictim);
    DisasterVictim findDisasterVictimById(int id);

    // Methods to handle medical records
    void addMedicalRecord(MedicalRecord record);
    void updateMedicalRecord( MedicalRecord updatedRecord);
    ArrayList<MedicalRecord> listMedicalRecordsByLocation(Location location);

    // Method to handle location information
    void addLocation(Location location);
    void updateLocation(Location location);
    Location findLocationByName(String name);
    ArrayList<Location> listAllLocations();
}
