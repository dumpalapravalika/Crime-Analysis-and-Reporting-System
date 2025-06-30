package com.hexaware.crimeanalysis.entity;

import java.sql.Date;

public class Incidents {

    private int incidentID;
    private int victimID;
    private int suspectID;
    private String type;
    private String status;
    private Date date;
    private String location;
    private String description;

    // Constructor
    public Incidents() {
    }

    public Incidents(int incidentID, int victimID, int suspectID, String type, String status, Date date, String location, String description) {
        this.incidentID = incidentID;
        this.victimID = victimID;
        this.suspectID = suspectID;
        this.type = type;
        this.status = status;
        this.date = date;
        this.location = location;
        this.description = description;
    }

    // Getters and Setters
    public int getIncidentID() {
        return incidentID;
    }

    public void setIncidentID(int incidentID) {
        this.incidentID = incidentID;
    }

    public int getVictimID() {
        return victimID;
    }

    public void setVictimID(int victimID) {
        this.victimID = victimID;
    }

    public int getSuspectID() {
        return suspectID;
    }

    public void setSuspectID(int suspectID) {
        this.suspectID = suspectID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "Incident {" +
                "incidentID=" + incidentID +
                ", victimID=" + victimID +
                ", suspectID=" + suspectID +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    
}
