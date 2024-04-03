package edu.ucalgary.oop;

import java.util.List;

public interface InquirerServiceInterface {
    void logQuery(String query);
    List<DisasterVictim> searchVictimsByNamePart(String namePart);
}
