package model;

import java.sql.Date;

public class Application {

    private int id;
    private int userId;
    private String company;
    private String role;
    private String location;
    private Double salary;
    private String status;
    private Date appliedDate;
    private String notes;
    private Date nextFollowupDate;

    public Application() {}

    public Application(int userId, String company, String role, String location,
                       Double salary, String status, Date appliedDate,
                       String notes, Date nextFollowupDate) {

        this.userId = userId;
        this.company = company;
        this.role = role;
        this.location = location;
        this.salary = salary;
        this.status = status;
        this.appliedDate = appliedDate;
        this.notes = notes;
        this.nextFollowupDate = nextFollowupDate;
    }
    
    public Application(int id, int userId, String company, String role,
            String location, Double salary, String status,
            Date appliedDate, String notes, Date nextFollowupDate) {

			this.id = id;
			this.userId = userId;
			this.company = company;
			this.role = role;
			this.location = location;
			this.salary = salary;
			this.status = status;
			this.appliedDate = appliedDate;
			this.notes = notes;
			this.nextFollowupDate = nextFollowupDate;
	}
    
    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", \ncompany='" + company + '\'' +
                ", \nrole='" + role + '\'' +
                ", \nlocation='" + location + '\'' +
                ", \nsalary=" + salary +
                ", \nstatus='" + status + '\'' +
                ", \nappliedDate=" + appliedDate +
                ", \nfollowup=" + nextFollowupDate +
                ", \nNotes="+notes+
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Date getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(Date appliedDate) {
        this.appliedDate = appliedDate;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public Date getNextFollowupDate() {
        return nextFollowupDate;
    }

    public void setNextFollowupDate(Date nextFollowupDate) {
        this.nextFollowupDate = nextFollowupDate;
    }
}