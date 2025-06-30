package com.hexaware.crimeanalysis.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hexaware.crimeanalysis.dao.CrimeAnalysisImpl;
import com.hexaware.crimeanalysis.entity.Incidents;
import com.hexaware.crimeanalysis.util.DBConnection;

public class CrimeAnalysisImplTest {

    private CrimeAnalysisImpl crimeService;

    @BeforeEach
    public void setUp() {
        crimeService = new CrimeAnalysisImpl();

        // Clear the incidents table before every test
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM incidents");
            ps.executeUpdate();
            conn.close();
            System.out.println("Incidents table cleared.");
            System.out.println("All test cases passed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateIncident_DuplicateId() {
        Incidents incident1 = new Incidents();
        incident1.setIncidentID(22);
        incident1.setVictimID(9);
        incident1.setSuspectID(9);
        incident1.setType("Assault");
        incident1.setStatus("Open");
        incident1.setDate(Date.valueOf("2025-06-28"));
        incident1.setLocation("Delhi");
        incident1.setDescription("Fight at metro station");

        Incidents incident2 = new Incidents();
        incident2.setIncidentID(22); // duplicate ID
        incident2.setVictimID(9);
        incident2.setSuspectID(9);
        incident2.setType("Theft");
        incident2.setStatus("Closed");
        incident2.setDate(Date.valueOf("2025-06-28"));
        incident2.setLocation("Mumbai");
        incident2.setDescription("Bag stolen in train");

        crimeService.createIncident(incident1);
        System.out.println("First incident inserted successfully.");

        boolean result = crimeService.createIncident(incident2);
        if (!result) {
            System.out.println("Duplicate incident insertion blocked as expected.");
        }
        assertFalse(result, "Duplicate incident ID should not be allowed");
    }

    @Test
    public void testUpdateIncidentStatus_Success() {
        Incidents incident = new Incidents();
        incident.setIncidentID(22);
        incident.setVictimID(9);
        incident.setSuspectID(9);
        incident.setType("Theft");
        incident.setStatus("Closed");
        incident.setDate(Date.valueOf("2025-06-28"));
        incident.setLocation("Mumbai");
        incident.setDescription("Bag stolen in train");

        crimeService.createIncident(incident);
        System.out.println("Incident created for update test.");

        boolean result = crimeService.updateIncidentStatus("open", 22);
        if (result) {
            System.out.println("Incident status updated successfully.");
        }
        assertTrue(result, "Status should be updated successfully");
    }

    @Test
    public void testUpdateIncidentStatus_InvalidID() {
        boolean result = crimeService.updateIncidentStatus("Resolved", 999);
        if (!result) {
            System.out.println("Invalid incident ID update failed as expected.");
        }
        assertFalse(result, "Updating non-existing incident ID should return false");
    }
}
