package com.hexaware.crimeanalysis.service;

import java.util.Collection;
import java.util.List;
import com.hexaware.crimeanalysis.entity.*;

public interface ICrimeAnalysisService {
    boolean createIncident(Incidents incident);
    boolean updateIncidentStatus(String status, int incidentId);
    Collection<Incidents> getIncidentsInDateRange(String startDate, String endDate);
    Collection<Incidents> searchIncidents(String criteria);
    Reports generateIncidentReport(Incidents incident);
    Cases createCase(int caseId, String caseDescription, Collection<Incidents> incidents); 
    Cases getCaseDetails(int caseId);
    boolean updateCaseDetails(Cases caseObj);
    List<Cases> getAllCases();
    Incidents getIncidentById(int incidentId);
}
