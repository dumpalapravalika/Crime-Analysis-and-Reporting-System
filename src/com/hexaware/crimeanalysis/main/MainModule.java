package com.hexaware.crimeanalysis.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import com.hexaware.crimeanalysis.dao.CrimeAnalysisImpl;
import com.hexaware.crimeanalysis.entity.Cases;
import com.hexaware.crimeanalysis.entity.Incidents;
import com.hexaware.crimeanalysis.entity.Reports;
import com.hexaware.crimeanalysis.service.ICrimeAnalysisService;

public class MainModule {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ICrimeAnalysisService service = new CrimeAnalysisImpl();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n====== CRIME ANALYSIS SYSTEM ======");
            System.out.println("1. Create Incident");
            System.out.println("2. Update Incident Status");
            System.out.println("3. Get Incidents in Date Range");
            System.out.println("4. Search Incidents by Type");
            System.out.println("5. Generate Incident Report");
            System.out.println("6. Create Case");
            System.out.println("7. Get Case Details");
            System.out.println("8. Update Case Details");
            System.out.println("9. Get All Cases");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    Incidents newIncident = new Incidents();
                    System.out.print("Enter Incident ID: ");
                    newIncident.setIncidentID(sc.nextInt());
                    System.out.print("Enter Victim ID: ");
                    newIncident.setVictimID(sc.nextInt());
                    System.out.print("Enter Suspect ID: ");
                    newIncident.setSuspectID(sc.nextInt());
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Type: ");
                    newIncident.setType(sc.nextLine());
                    System.out.print("Enter Status: ");
                    newIncident.setStatus(sc.nextLine());
                    System.out.print("Enter Date (yyyy-mm-dd): ");
                    newIncident.setDate(java.sql.Date.valueOf(sc.nextLine()));
                    System.out.print("Enter Location: ");
                    newIncident.setLocation(sc.nextLine());
                    System.out.print("Enter Description: ");
                    newIncident.setDescription(sc.nextLine());
                    boolean created = service.createIncident(newIncident);
                    System.out.println(created ? "Incident Created" : "Failed to Create Incident");
                    break;

                case 2:
                    System.out.print("Enter Incident ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter New Status: ");
                    String status = sc.nextLine();
                    boolean updated = service.updateIncidentStatus(status, id);
                    System.out.println(updated ? "Status Updated" : "Update Failed");
                    break;

                case 3:
                    System.out.print("Enter Start Date (yyyy-mm-dd): ");
                    String start = sc.nextLine();
                    System.out.print("Enter End Date (yyyy-mm-dd): ");
                    String end = sc.nextLine();
                    Collection<Incidents> rangeList = service.getIncidentsInDateRange(start, end);
                    System.out.println("Incidents Found:");
                    rangeList.forEach(System.out::println);
                    break;

                case 4:
                    System.out.print("Enter Type to Search: ");
                    String type = sc.nextLine();
                    Collection<Incidents> result = service.searchIncidents(type);
                    System.out.println("Matching Incidents:");
                    result.forEach(System.out::println);
                    break;

                case 5:
                    System.out.print("Enter Incident ID to Generate Report: ");
                    int reportId = sc.nextInt();
                    Incidents incidentReport = new Incidents();
                    incidentReport.setIncidentID(reportId);
                    Reports report = service.generateIncidentReport(incidentReport);
                    System.out.println("Generated Report:");
                    System.out.println(report);
                    break;

                case 6:
                    System.out.print("Enter Case ID: ");
                    int caseId = sc.nextInt();
                    sc.nextLine(); 

                    System.out.print("Enter Case Description: ");
                    String caseDesc = sc.nextLine();

                    System.out.print("Enter Incident IDs to associate with the case: ");
                    String idLine = sc.nextLine();
                    String[] idParts = idLine.split(",");

                    List<Incidents> associatedIncidents = new ArrayList<>();
                    for (String idStr : idParts) {
                        try {
                            int incidentId = Integer.parseInt(idStr.trim());
                            Incidents fetchedIncident = service.getIncidentById(incidentId); 
                            if (fetchedIncident != null) {
                                associatedIncidents.add(fetchedIncident);
                            } else {
                                System.out.println("Incident ID " + incidentId + " not found.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid incident ID: " + idStr);
                        }
                    }

                    if (!associatedIncidents.isEmpty()) {
                        Cases newCase = service.createCase(caseId, caseDesc, associatedIncidents); 
                        System.out.println("Case created successfully:\n" + newCase);
                    } else {
                        System.out.println("No valid incidents provided. Case not created.");
                    }
                    break;


                case 7:
                    System.out.print("Enter Case ID to view: ");
                    int cid = sc.nextInt();
                    Cases viewCase = service.getCaseDetails(cid);

                    if (viewCase != null) {
                        System.out.println("Case Details:");
                        System.out.println("ID: " + viewCase.getCaseId());
                        System.out.println("Description: " + viewCase.getCaseDescription());
                        System.out.println("Associated Incidents:");
                        for (Incidents inc : viewCase.getIncidents()) {
                            System.out.println(inc);
                        }
                    } else {
                        System.out.println("No case found with ID: " + cid);
                    }
                    break;

                case 8:
                    System.out.print("Enter Case ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Enter new case description: ");
                    String newDesc = sc.nextLine();
                    Cases updateCase = new Cases();
                    updateCase.setCaseId(updateId);
                    updateCase.setCaseDescription(newDesc);
                    boolean updatedCase = service.updateCaseDetails(updateCase);
                    System.out.println(updatedCase ? "Case updated" : "Failed to update case");
                    break;

                case 9:
                    List<Cases> allCases = service.getAllCases();
                    System.out.println("All Cases:");
                    allCases.forEach(c -> {
                        System.out.println("Case ID: " + c.getCaseId() + ", Description: " + c.getCaseDescription());
                    });
                    break;

                case 0:
                    exit = true;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option");
            }
        }

        sc.close();
    }
}
