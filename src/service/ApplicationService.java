package service;

import dao.ApplicationDAO;
import model.Application;

import java.sql.Date;
import java.util.List;

public class ApplicationService {

    private ApplicationDAO applicationDAO = new ApplicationDAO();


    // ADD APPLICATION
    public boolean addApplication(Application app) {

        boolean result = applicationDAO.addApplication(app);

        if(result) {
            System.out.println("Application added successfully!");
        } else {
            System.out.println("Failed to add application.");
        }

        return result;
    }


    // VIEW APPLICATIONS
    public void viewApplications(int userId) {

        List<Application> list = applicationDAO.getApplications(userId);

        if(list.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }

        System.out.println("\nTotal Applications: " + list.size());
        
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.printf("%-4s %-15s %-15s %-12s %-10s %-10s %-15s %-15s%n",
                "ID","Company","Role","Location","Salary","Status","Follow-Up","Notes");
        System.out.println("-------------------------------------------------------------------------------------------------------");

        for(Application app : list){

            System.out.printf("%-4d %-15s %-15s %-12s %-10.0f %-10s %-15s %-15s%n",
                    app.getId(),
                    app.getCompany(),
                    app.getRole(),
                    app.getLocation(),
                    app.getSalary(),
                    app.getStatus(),
                    app.getNextFollowupDate(),
                    app.getNotes());
        }

        System.out.println("-------------------------------------------------------------------------------------------------------");
    }


    // UPDATE STATUS
    public void updateStatus(int id, String status) {

        boolean result = applicationDAO.updateApplicationStatus(id, status);

        if(result) {
            System.out.println("Status updated successfully!");
        } else {
            System.out.println("Failed to update status.");
        }
    }

    public void updateNotes(int id, String notes) {
        applicationDAO.updateNotes(id, notes);
    }

    // DELETE APPLICATION
    public void deleteApplication(int id) {

        boolean result = applicationDAO.deleteApplication(id);

        if(result) {
            System.out.println("Application deleted successfully!");
        } else {
            System.out.println("Failed to delete application.");
        }
    }


    // SEARCH BY COMPANY
    public void searchByCompany(int userId, String company) {

        List<Application> list = applicationDAO.searchByCompany(userId, company);

        if(list.isEmpty()) {
            System.out.println("No applications found for this company.");
            return;
        }

        for(Application app : list) {
            System.out.println(app);
        }
    }


    // FILTER BY STATUS
    public void filterByStatus(int userId, String status) {

        List<Application> list = applicationDAO.filterByStatus(userId, status);

        if(list.isEmpty()) {
            System.out.println("No applications found with this status.");
            return;
        }

        for(Application app : list) {
            System.out.println(app);
        }
    }


    // SHOW DASHBOARD
    public void showDashboard(int userId) {
        applicationDAO.showDashboard(userId);
    }
    
    
    public void showFollowUps(int userId) {
        applicationDAO.showFollowUps(userId);
    }
    
    public void updateFollowUpDate(int id, Date followUpDate) {
        applicationDAO.updateFollowUpDate(id, followUpDate);
    }
    
    public void showStatusAnalytics(int userId) {
        applicationDAO.showStatusAnalytics(userId);
    }
}