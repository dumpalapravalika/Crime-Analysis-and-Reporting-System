package com.hexaware.crimeanalysis.entity;

public class Officers {
    private int officerID;
    private String firstName;
    private String lastName;
    private int badgeNumber;
    private String rankk;
    private String contactInfo;
    private int agencyID;

    public Officers() {}

    public Officers(int officerID, String firstName, String lastName, int badgeNumber,
                   String rankk, String contactInfo, int agencyID) {
        this.officerID = officerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.badgeNumber = badgeNumber;
        this.rankk = rankk;
        this.contactInfo = contactInfo;
        this.agencyID = agencyID;
    }

	public int getOfficerID() {
		return officerID;
	}

	public void setOfficerID(int officerID) {
		this.officerID = officerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getBadgeNumber() {
		return badgeNumber;
	}

	public void setBadgeNumber(int badgeNumber) {
		this.badgeNumber = badgeNumber;
	}

	public String getRank() {
		return rankk;
	}

	public void setRank(String rankk) {
		this.rankk = rankk;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public int getAgencyID() {
		return agencyID;
	}

	public void setAgencyID(int agencyID) {
		this.agencyID = agencyID;
	}

    
    
}
