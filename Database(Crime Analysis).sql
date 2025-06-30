drop database crimeanalysisdb;
 create database crimeanalysisdb;
 use crimeanalysisdb;
 
 -- 1. Victims Table
CREATE TABLE victims (
    victimID INT PRIMARY KEY,
    firstName VARCHAR(100),
    lastName VARCHAR(100),
    dateOfBirth DATE,
    gender VARCHAR(10),
    contactInfo VARCHAR(150)
);

-- 2. Suspects Table
CREATE TABLE suspects (
    suspectID INT PRIMARY KEY,
    firstName VARCHAR(100),
    lastName VARCHAR(100),
    dateOfBirth DATE,
    gender VARCHAR(10),
    contactInfo VARCHAR(150)
);

-- 3. Law Enforcement Agencies Table
CREATE TABLE law_enforcement_agencies (
    agencyID INT PRIMARY KEY,
    agencyName VARCHAR(100),
    jurisdiction VARCHAR(100),
    contactInfo VARCHAR(150)
);

-- 4. Officers Table
CREATE TABLE officers (
    officerID INT PRIMARY KEY,
    firstName VARCHAR(100),
    lastName VARCHAR(100),
    badgeNumber INT,
    rankk VARCHAR(50),
    contactInfo VARCHAR(150),
    agencyID INT,
    FOREIGN KEY (agencyID) REFERENCES law_enforcement_agencies(agencyID)
);

-- 5. Incidents Table
CREATE TABLE incidents (
    incidentID INT PRIMARY KEY,
    victimID INT,
    suspectID INT,
    type VARCHAR(100),
    status VARCHAR(50),
    date DATE,
    location VARCHAR(150),
    description TEXT,
    FOREIGN KEY (victimID) REFERENCES victims(victimID),
    FOREIGN KEY (suspectID) REFERENCES suspects(suspectID)
);

-- 6. Evidence Table
CREATE TABLE evidence (
    evidenceID INT PRIMARY KEY,
    description TEXT,
    locationFound VARCHAR(150),
    incidentID INT,
    FOREIGN KEY (incidentID) REFERENCES incidents(incidentID)
);

-- 7. Reports Table
CREATE TABLE reports (
    reportID INT PRIMARY KEY,
    incidentID INT,
    reportingOfficer INT,
    reportDate DATE,
    reportDetails TEXT,
    status VARCHAR(50),
    FOREIGN KEY (incidentID) REFERENCES incidents(incidentID),
    FOREIGN KEY (reportingOfficer) REFERENCES officers(officerID)
);

-- 8. Cases Table
CREATE TABLE Cases(
    caseID INT PRIMARY KEY,
    caseDescription TEXT
);

-- 9. Case-Incident Mapping (Many-to-Many)
CREATE TABLE case_incidents (
    caseID INT,
    incidentID INT,
    PRIMARY KEY (caseID, incidentID),
    FOREIGN KEY (caseID) REFERENCES cases(caseID),
    FOREIGN KEY (incidentID) REFERENCES incidents(incidentID)
);

INSERT INTO victims (victimID, firstName, lastName, dateOfBirth, gender, contactInfo) VALUES
(1, 'Ravi', 'Kumar', '1990-03-12', 'Male', 'ravi.kumar@example.com'),
(2, 'Priya', 'Sharma', '1985-07-21', 'Female', 'priya.sharma@example.com'),
(3, 'Amit', 'Verma', '1993-11-10', 'Male', 'amit.verma@example.com'),
(4, 'Sneha', 'Patel', '1998-01-05', 'Female', 'sneha.patel@example.com'),
(5, 'Karan', 'Joshi', '1987-04-25', 'Male', 'karan.joshi@example.com'),
(6, 'Deepika', 'Reddy', '1991-08-30', 'Female', 'deepika.reddy@example.com'),
(7, 'Rahul', 'Mehta', '1994-06-15', 'Male', 'rahul.mehta@example.com'),
(8, 'Anjali', 'Singh', '1989-10-20', 'Female', 'anjali.singh@example.com'),
(9, 'Vikram', 'Desai', '1995-02-14', 'Male', 'vikram.desai@example.com'),
(10, 'Neha', 'Bose', '1992-12-01', 'Female', 'neha.bose@example.com');

INSERT INTO suspects (suspectID, firstName, lastName, dateOfBirth, gender, contactInfo) VALUES
(1, 'Arjun', 'Malhotra', '1988-09-18', 'Male', 'arjun.malhotra@example.com'),
(2, 'Nikita', 'Gupta', '1990-12-05', 'Female', 'nikita.gupta@example.com'),
(3, 'Raj', 'Choudhary', '1985-03-29', 'Male', 'raj.choudhary@example.com'),
(4, 'Tanya', 'Kapoor', '1993-06-22', 'Female', 'tanya.kapoor@example.com'),
(5, 'Siddharth', 'Yadav', '1987-08-14', 'Male', 'siddharth.yadav@example.com'),
(6, 'Meera', 'Jain', '1991-04-11', 'Female', 'meera.jain@example.com'),
(7, 'Dev', 'Rana', '1996-01-30', 'Male', 'dev.rana@example.com'),
(8, 'Pooja', 'Bajaj', '1992-07-07', 'Female', 'pooja.bajaj@example.com'),
(9, 'Manish', 'Shetty', '1989-05-16', 'Male', 'manish.shetty@example.com'),
(10, 'Shruti', 'Roy', '1994-11-09', 'Female', 'shruti.roy@example.com');

select*from incidents;

INSERT INTO Incidents (incidentID, victimID, suspectID, type, status, date, location, description)
VALUES 
(2, 1, 2, 'Theft', 'Open', '2025-06-20', 'Hyderabad', 'Mobile phone stolen in a crowded market'),
(3, 2, 3, 'Assault', 'Investigating', '2025-06-15', 'Bangalore', 'Physical altercation in a bar'),
(4, 3, 4, 'Fraud', 'Closed', '2025-05-30', 'Chennai', 'Credit card fraud reported'),
(5, 4, 5, 'Robbery', 'Open', '2025-06-10', 'Delhi', 'Jewelry store robbed at night'),
(6, 5, 6, 'Cybercrime', 'Pending', '2025-06-01', 'Mumbai', 'Phishing attack via email'),
(7, 6, 7, 'Kidnapping', 'Investigating', '2025-05-28', 'Pune', 'Child reported missing from school'),
(8, 7, 8, 'Murder', 'Closed', '2025-04-22', 'Kolkata', 'Suspect arrested for homicide'),
(9, 8, 9, 'Domestic Violence', 'Open', '2025-06-05', 'Lucknow', 'Repeated domestic abuse reported'),
(10, 9, 10, 'Burglary', 'Closed', '2025-03-18', 'Jaipur', 'House break-in during daytime'),
(11, 10, 1, 'Arson', 'Open', '2025-06-25', 'Ahmedabad', 'Fire set intentionally in warehouse');

INSERT INTO law_enforcement_agencies (agencyID, agencyName, jurisdiction, contactInfo) VALUES
(1, 'Hyderabad Police Department', 'Hyderabad', 'contact@hydpolice.in'),
(2, 'Mumbai Crime Branch', 'Mumbai', 'crimebranch@mumbaipolice.gov.in'),
(3, 'Delhi Special Cell', 'Delhi NCR', 'specialcell@delhipolice.in'),
(4, 'Bengaluru City Police', 'Bangalore', 'info@bcp.gov.in'),
(5, 'Chennai Central Police', 'Chennai', 'central@chennaipolice.in'),
(6, 'Pune Cyber Crime Unit', 'Pune', 'cybercrime@punepolice.in'),
(7, 'Kolkata Detective Department', 'Kolkata', 'detectives@kolkatapolice.in'),
(8, 'Ahmedabad Law Enforcement', 'Ahmedabad', 'admin@ahmedabadle.gov.in'),
(9, 'Jaipur Police Force', 'Jaipur', 'support@jaipurpolice.in'),
(10, 'Lucknow Criminal Investigation Dept', 'Lucknow', 'cid@lucknowpolice.in');

INSERT INTO officers (officerID, firstName, lastName, badgeNumber, rankk, contactInfo, agencyID) VALUES
(1, 'Arjun', 'Reddy', 101, 'Inspector', 'arjun.reddy@police.gov.in', 1),
(2, 'Meera', 'Sharma', 102, 'Sub-Inspector', 'meera.sharma@police.gov.in', 1),
(3, 'Ravi', 'Verma', 103, 'Constable', 'ravi.verma@police.gov.in', 2),
(4, 'Priya', 'Desai', 104, 'Inspector', 'priya.desai@police.gov.in', 2),
(5, 'Karthik', 'Rao', 105, 'Sub-Inspector', 'karthik.rao@police.gov.in', 3),
(6, 'Sneha', 'Nair', 106, 'Head Constable', 'sneha.nair@police.gov.in', 3),
(7, 'Amit', 'Joshi', 107, 'Constable', 'amit.joshi@police.gov.in', 4),
(8, 'Divya', 'Kapoor', 108, 'Inspector', 'divya.kapoor@police.gov.in', 4),
(9, 'Rajeev', 'Mishra', 109, 'Sub-Inspector', 'rajeev.mishra@police.gov.in', 5),
(10, 'Lalitha', 'Kumari', 110, 'Inspector', 'lalitha.kumari@police.gov.in', 5);


INSERT INTO reports (reportID, incidentID, reportingOfficer, reportDate, reportDetails, status) VALUES
(1, 2, 1, '2024-01-10', 'Robbery at local market. Victim injured. Evidence collected.', 'Open'),
(2, 3, 2, '2024-01-12', 'Cybercrime reported involving phishing scam. Investigation started.', 'Under Investigation'),
(3, 4, 3, '2024-01-14', 'Hit and run case. CCTV footage retrieved. Witness statements pending.', 'Pending'),
(4, 5, 4, '2024-01-18', 'Domestic violence complaint. Medical records verified.', 'Closed'),
(5, 6, 5, '2024-01-20', 'Drug possession. Two suspects detained.', 'Under Investigation'),
(6, 7, 6, '2024-01-22', 'Shoplifting at mall. Suspect fled. Fingerprints collected.', 'Pending'),
(7, 8, 7, '2024-01-25', 'Arson reported in warehouse. Fire department involved.', 'Open'),
(8, 9, 8, '2024-01-27', 'ATM theft attempt. Tools seized. No suspects identified yet.', 'Under Surveillance'),
(9, 10, 9, '2024-01-30', 'Mobile snatching near bus stop. Victim provided description.', 'Open'),
(10, 11, 10, '2024-02-01', 'Forgery of documents found in real estate deal.', 'Closed');

INSERT INTO evidence (evidenceID, description, locationFound, incidentID) VALUES
(1, 'Fingerprint found on wallet', 'Chennai', 1),
(2, 'CCTV footage of theft', 'Hyderabad', 2),
(3, 'Blood stain on broken bottle', 'Bangalore', 3),
(4, 'Fake credit card used', 'Chennai', 4),
(5, 'Broken lock at jewelry store', 'Delhi', 5),
(6, 'Phishing email screenshot', 'Mumbai', 6),
(7, 'School bag with initials', 'Pune', 7),
(8, 'Knife with fingerprints', 'Kolkata', 8),
(9, 'Mobile chat evidence of abuse', 'Lucknow', 9),
(10, 'Pry marks on backdoor', 'Jaipur', 10);

select * from incidents;
select * from cases;
select * from suspects;
select * from victims;
select * from law_enforcement_agencies;
select * from evidence;
select * from reports;
select * from officers;



