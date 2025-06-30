package com.hexaware.crimeanalysis.entity;

import java.util.Collection;

public class Cases {
    private int caseId;
    private String caseDescription;
    private Collection<Incidents> incidents;

    public Cases() {}

    public Cases(int caseId, String caseDescription, Collection<Incidents> incidents) {
        this.caseId = caseId;
        this.caseDescription = caseDescription;
        this.incidents = incidents;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }

    public Collection<Incidents> getIncidents() {
        return incidents;
    }

    public void setIncidents(Collection<Incidents> incidents) {
        this.incidents = incidents;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Case ID: ").append(caseId).append("\n");
        sb.append("Description: ").append(caseDescription).append("\n");
        sb.append("Associated Incidents:\n");
        if (incidents != null) {
            for (Incidents incident : incidents) {
                sb.append(" - ").append(incident).append("\n");
            }
        } else {
            sb.append(" No incidents linked.\n");
        }
        return sb.toString();
    }


}
