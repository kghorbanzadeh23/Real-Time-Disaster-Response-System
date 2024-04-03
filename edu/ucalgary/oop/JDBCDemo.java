/*
Copyright Ann Barcomb and Emily Marasco, 2021-2024
Licensed under GPL v3
See LICENSE.txt for more information.
*/

package edu.ucalgary.oop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDemo{

    private Connection dbConnect;
    private ResultSet results;
            
    public JDBCDemo(){
        createConnection();
    }
    
    public void createConnection(){
                
        try{
            dbConnect = DriverManager.getConnection("jdbc:postgresql://localhost/ensf380project", "oop", "ucalgary");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DisasterVictim findDisasterVictimByName(String firstName, String lastName) {
        String sql = "SELECT first_name, last_name, date FROM disastervictim WHERE first_name = ? AND last_name = ?;";
        try (PreparedStatement pstmt = dbConnect.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String date = rs.getString("date"); // Assuming there's a date column
                // Assuming DisasterVictim has a constructor that matches these parameters
                return new DisasterVictim(firstName, lastName, date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertDisasterVictim(String firstName, String lastName, String date) {
        // Assuming the DisasterVictims table and its structure. Replace with actual table name and columns
        String sql = "INSERT INTO disastervictim (first_name, last_name, date) VALUES (?, ?, ?);";
        try (PreparedStatement pstmt = dbConnect.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setDate(3, Date.valueOf(date));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertFamilyRelation(String firstName1, String lastName1, String firstName2, String lastName2, String relationshipType) {
        String sql = "INSERT INTO familyrelation (first_name1, last_name1, first_name2, last_name2, relationship_type) VALUES (?, ?, ?, ?, ?);";
    try (PreparedStatement pstmt = dbConnect.prepareStatement(sql)) {
        pstmt.setString(1, firstName1);
        pstmt.setString(2, lastName1);
        pstmt.setString(3, firstName2);
        pstmt.setString(4, lastName2);
        pstmt.setString(5, relationshipType);
        int affectedRows = pstmt.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Inserting family relation failed, no rows affected.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public void insertMedicalRecord(String locationName, String treatmentDetails, String dateOfTreatment) {

            // If location ID is found, insert the medical record
            String insertRecordSql = "INSERT INTO MedicalRecord (location_name, treatment_detail, date_of_treatment) VALUES (?, ?, ?);";
            try (PreparedStatement pstmtInsert = dbConnect.prepareStatement(insertRecordSql)) {
                pstmtInsert.setString(1, locationName);
                pstmtInsert.setString(2, treatmentDetails);
                pstmtInsert.setDate(3, Date.valueOf(dateOfTreatment));
                pstmtInsert.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 

        public List<String> searchVictimsByName(String searchQuery) {
        List<String> victimNames = new ArrayList<>();
        String sql = "SELECT first_name, last_name FROM disastervictim WHERE LOWER(first_name) LIKE LOWER(?) OR LOWER(last_name) LIKE LOWER(?);";

        try (PreparedStatement pstmt = dbConnect.prepareStatement(sql)) {
            pstmt.setString(1, "%" + searchQuery + "%");
            pstmt.setString(2, "%" + searchQuery + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                victimNames.add(firstName + " " + lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return victimNames;
    }

    public List<String> searchInquiriesByPartialName(String partialName) {
        List<String> inquiriesInfo = new ArrayList<>();
        String sql = "SELECT i.firstName, i.lastName, i.phoneNumber, il.callDate, il.details " +
                     "FROM INQUIRER i " +
                     "JOIN INQUIRY_LOG il ON i.id = il.inquirer " +
                     "WHERE LOWER(i.firstName) LIKE LOWER(?) OR LOWER(i.lastName) LIKE LOWER(?);";
    
        try (PreparedStatement pstmt = dbConnect.prepareStatement(sql)) {
            pstmt.setString(1, "%" + partialName + "%");
            pstmt.setString(2, "%" + partialName + "%");
            ResultSet rs = pstmt.executeQuery();
    
            while (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phoneNumber = rs.getString("phoneNumber");
                Date callDate = rs.getDate("callDate");
                String details = rs.getString("details");
                String inquiryInfo = "Name: " + firstName + " " + lastName +
                                     ", Phone: " + phoneNumber +
                                     ", Date of Call: " + callDate +
                                     ", Inquiry Details: " + details;
                inquiriesInfo.add(inquiryInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inquiriesInfo;
    }
    public void logNewInquiry(int inquirerId, String details) {
        String sql = "INSERT INTO INQUIRY_LOG (inquirer, callDate, details) VALUES (?, CURRENT_DATE, ?);";

    try (PreparedStatement pstmt = dbConnect.prepareStatement(sql)) {
        pstmt.setInt(1, inquirerId);
        pstmt.setString(2, details);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public void close() {
        try {
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {

        JDBCDemo myJDBC = new JDBCDemo();
 
        myJDBC.createConnection();
        
        myJDBC.close();        
    }
}
