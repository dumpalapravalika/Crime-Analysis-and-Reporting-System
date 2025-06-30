package com.hexaware.crimeanalysis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.hexaware.crimeanalysis.util.DBConnection;
import com.hexaware.crimeanalysis.entity.*;
import com.hexaware.crimeanalysis.service.ICrimeAnalysisService;

public class CrimeAnalysisImpl implements ICrimeAnalysisService {

    private static List<Cases> casesList = new ArrayList<>();

    @Override
    public boolean createIncident(Incidents incident) {
        String query = "INSERT INTO Incidents (incidentID, victimID, suspectID, type, status, date, location, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, incident.getIncidentID());
            stmt.setInt(2, incident.getVictimID());
            stmt.setInt(3, incident.getSuspectID());
            stmt.setString(4, incident.getType());
            stmt.setString(5, incident.getStatus());
            stmt.setDate(6, incident.getDate());
            stmt.setString(7, incident.getLocation());
            stmt.setString(8, incident.getDescription());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error in creating incident: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateIncidentStatus(String status, int incidentId) {
        String query = "UPDATE Incidents SET status = ? WHERE incidentID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, status);
            stmt.setInt(2, incidentId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("Error in updating incident status: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Collection<Incidents> getIncidentsInDateRange(String startDate, String endDate) {
        List<Incidents> incidents = new ArrayList<>();
        String query = "SELECT * FROM Incidents WHERE date BETWEEN ? AND ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Incidents incident = new Incidents();
                incident.setIncidentID(rs.getInt("incidentID"));
                incident.setVictimID(rs.getInt("victimID"));
                incident.setSuspectID(rs.getInt("suspectID"));
                incident.setType(rs.getString("type"));
                incident.setStatus(rs.getString("status"));
                incident.setDate(rs.getDate("date"));
                incident.setLocation(rs.getString("location"));
                incident.setDescription(rs.getString("description"));
                incidents.add(incident);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching incidents in date range: " + e.getMessage());
        }

        return incidents;
    }

    @Override
    public Collection<Incidents> searchIncidents(String criteria) {
        List<Incidents> result = new ArrayList<>();
        String query = "SELECT * FROM Incidents WHERE type LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + criteria + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Incidents incident = new Incidents();
                incident.setIncidentID(rs.getInt("incidentID"));
                incident.setVictimID(rs.getInt("victimID"));
                incident.setSuspectID(rs.getInt("suspectID"));
                incident.setType(rs.getString("type"));
                incident.setStatus(rs.getString("status"));
                incident.setDate(rs.getDate("date"));
                incident.setLocation(rs.getString("location"));
                incident.setDescription(rs.getString("description"));
                result.add(incident);
            }

        } catch (SQLException e) {
            System.out.println("Error in searching incidents: " + e.getMessage());
        }

        return result;
    }

    @Override
    public Reports generateIncidentReport(Incidents inputIncident) {
        Reports report = null;

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM Reports WHERE incidentID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, inputIncident.getIncidentID());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                report = new Reports();
                report.setReportID(rs.getInt("reportID"));
                report.setIncidentID(rs.getInt("incidentID"));
                report.setReportingOfficer(rs.getInt("reportingOfficer"));
                report.setReportDate(rs.getDate("reportDate"));
                report.setReportDetails(rs.getString("reportDetails"));
                report.setStatus(rs.getString("status"));
            } else {
                System.out.println("No report found for the given incident ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error fetching report: " + e.getMessage());
        }

        return report;
    }

    @Override
    public Cases createCase(int caseId, String caseDescription, Collection<Incidents> incidents) {
        String insertQuery = "INSERT INTO cases (caseId, caseDescription) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            stmt.setInt(1, caseId);
            stmt.setString(2, caseDescription);
            stmt.executeUpdate();

            Cases newCase = new Cases(caseId, caseDescription, new ArrayList<>(incidents));
            casesList.add(newCase);
            return newCase;

        } catch (SQLException e) {
            System.out.println("Error inserting case: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Cases getCaseDetails(int caseId) {
        for (Cases c : casesList) {
            if (c.getCaseId() == caseId) {
                return c;
            }
        }
        return null;
    }

    @Override
    public boolean updateCaseDetails(Cases caseObj) {
        for (int i = 0; i < casesList.size(); i++) {
            if (casesList.get(i).getCaseId() == caseObj.getCaseId()) {
                casesList.set(i, caseObj);
                return true;
            }
        }
        System.out.println("Cannot update. No case found with ID: " + caseObj.getCaseId());
        return false;
    }

    @Override
    public List<Cases> getAllCases() {
        List<Cases> allCases = new ArrayList<>();

        String query = "SELECT * FROM cases";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("caseId");
                String desc = rs.getString("caseDescription");

                
                Cases c = new Cases(id, desc, new ArrayList<>()); 
                allCases.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving all cases: " + e.getMessage());
        }

        return allCases;
    }


    @Override
    public Incidents getIncidentById(int incidentId) {
        String query = "SELECT * FROM Incidents WHERE incidentID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, incidentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Incidents incident = new Incidents();
                incident.setIncidentID(rs.getInt("incidentID"));
                incident.setVictimID(rs.getInt("victimID"));
                incident.setSuspectID(rs.getInt("suspectID"));
                incident.setType(rs.getString("type"));
                incident.setStatus(rs.getString("status"));
                incident.setDate(rs.getDate("date"));
                incident.setLocation(rs.getString("location"));
                incident.setDescription(rs.getString("description"));
                return incident;
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving incident by ID: " + e.getMessage());
        }

        return null;
    }
}
