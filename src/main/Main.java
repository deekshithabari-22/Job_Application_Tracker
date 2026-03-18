package main;

import model.Application;
import service.AuthService;
import service.ApplicationService;

import java.sql.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AuthService authService = new AuthService();
        ApplicationService applicationService = new ApplicationService();

        int choice;

        while(true){

            System.out.println("\n===== JOB APPLICATION TRACKER =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1){

                System.out.print("Username: ");
                String username = sc.nextLine();

                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Password: ");
                String password = sc.nextLine();

                authService.register(username,password,email);
            }

            else if(choice == 2){

                System.out.print("Username: ");
                String username = sc.nextLine();

                System.out.print("Password: ");
                String password = sc.nextLine();

                int userId = authService.login(username,password);

                if(userId != -1){
                    applicationMenu(sc,applicationService,userId);
                }
            }

            else if(choice == 3){
                System.out.println("Exiting...");
                break;
            }

            else{
                System.out.println("Invalid choice");
            }
        }

        sc.close();
    }


    private static void applicationMenu(Scanner sc, ApplicationService applicationService, int userId){

        int choice;

        while(true){

            System.out.println("\n===== APPLICATION MENU =====");
            System.out.println("1. Add Application");
            System.out.println("2. View Applications");
            System.out.println("3. Update Status");
            System.out.println("4. Update Follow-up Date");
            System.out.println("5. Update Notes");
            System.out.println("6. Delete Application");
            System.out.println("7. Search by Company");
            System.out.println("8. Filter by Status");
            System.out.println("9. Dashboard");
            System.out.println("10. Follow up Remainders");
            System.out.println("11. Status Analytics");
            System.out.println("12. Logout");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1){

                System.out.print("Company: ");
                String company = sc.nextLine();

                System.out.print("Role: ");
                String role = sc.nextLine();

                System.out.print("Location: ");
                String location = sc.nextLine();

                System.out.print("Salary: ");
                double salary = sc.nextDouble();
                sc.nextLine();

                System.out.print("Status: ");
                String status = sc.nextLine();

                System.out.print("Notes: ");
                String notes = sc.nextLine();

                Date appliedDate = new Date(System.currentTimeMillis());
                
                System.out.print("Next Follow-up Date (yyyy-mm-dd): ");
            	String followUp = sc.nextLine();
            	Date followUpDate = Date.valueOf(followUp);
            	
                Application app = new Application();

                app.setUserId(userId);
                app.setCompany(company);
                app.setRole(role);
                app.setLocation(location);
                app.setSalary(salary);
                app.setStatus(status);
                app.setNotes(notes);
                app.setAppliedDate(appliedDate);
                app.setNextFollowupDate(followUpDate);

                applicationService.addApplication(app);
            }

            else if(choice == 2){
                applicationService.viewApplications(userId);
            }

            else if(choice == 3){

                System.out.print("Application ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("New Status: ");
                String status = sc.nextLine();

                applicationService.updateStatus(id,status);
            }
            
            else if(choice == 4){

                System.out.print("Application ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("New Follow-up Date (yyyy-mm-dd): ");
                String date = sc.nextLine();

                Date followUpDate = Date.valueOf(date);

                applicationService.updateFollowUpDate(id, followUpDate);
            }
            
            else if(choice == 5){

                System.out.print("Application ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter new notes: ");
                String notes = sc.nextLine();

                applicationService.updateNotes(id, notes);
            }
            
            else if(choice == 6){

                System.out.print("Application ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                applicationService.deleteApplication(id);
            }

            else if(choice == 7){

                System.out.print("Company Name: ");
                String company = sc.nextLine();

                applicationService.searchByCompany(userId,company);
            }

            else if(choice == 8){

                System.out.print("Status: ");
                String status = sc.nextLine();

                applicationService.filterByStatus(userId,status);
            }

            else if(choice == 9){
                applicationService.showDashboard(userId);
            }
            
            else if(choice == 10){
                applicationService.showFollowUps(userId);
            }
            
            else if(choice == 11){
                applicationService.showStatusAnalytics(userId);
            }
            
            else if(choice == 12){
                System.out.println("Logged out.");
                break;
            }

            else{
                System.out.println("Invalid choice.");
            }
        }
    }
}