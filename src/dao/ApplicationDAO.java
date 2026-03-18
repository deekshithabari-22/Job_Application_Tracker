package dao;

import db.DBConnection;
import model.Application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {

    // ADD APPLICATION
    public boolean addApplication(Application app) {

        String query = "INSERT INTO applications(user_id,company,role,location,salary,status,applied_date,notes,next_followup_date) VALUES(?,?,?,?,?,?,?,?,?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1, app.getUserId());
            ps.setString(2, app.getCompany());
            ps.setString(3, app.getRole());
            ps.setString(4, app.getLocation());
            
            if (app.getSalary() != null) {
                ps.setDouble(5, app.getSalary());
            } else {
                ps.setNull(5, java.sql.Types.DOUBLE);
            }
            
            ps.setString(6, app.getStatus());
            ps.setDate(7, app.getAppliedDate());
            ps.setString(8, app.getNotes());
            ps.setDate(9, app.getNextFollowupDate());

            int rows = ps.executeUpdate();

            return rows > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }



    // VIEW APPLICATIONS
    public List<Application> getApplications(int userId){

        List<Application> list = new ArrayList<>();

        String query = "SELECT * FROM applications WHERE user_id=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                Application app = new Application();

                app.setId(rs.getInt("id"));
                app.setUserId(rs.getInt("user_id"));
                app.setCompany(rs.getString("company"));
                app.setRole(rs.getString("role"));
                app.setLocation(rs.getString("location"));
                
                Double salary = rs.getDouble("salary");

                if(rs.wasNull()) {
                    app.setSalary(null);
                } else {
                    app.setSalary(salary);
                }
                
                app.setStatus(rs.getString("status"));
                app.setAppliedDate(rs.getDate("applied_date"));
                app.setNotes(rs.getString("notes"));
                app.setNextFollowupDate(rs.getDate("next_followup_date"));

                list.add(app);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }



    // UPDATE STATUS
    public boolean updateApplicationStatus(int id,String status){

        String query="UPDATE applications SET status=? WHERE id=?";

        try(Connection conn=DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(query)){

            ps.setString(1,status);
            ps.setInt(2,id);

            int rows=ps.executeUpdate();

            return rows>0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
    
    
    
    public void updateFollowUpDate(int id, Date followUpDate) {

        String query = "UPDATE applications SET next_followup_date=? WHERE id=?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {

            ps.setDate(1, followUpDate);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("Follow-up date updated successfully!");
            } else {
                System.out.println("Application not found.");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void updateNotes(int id, String notes) {

        String query = "UPDATE applications SET notes=? WHERE id=?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, notes);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("Notes updated successfully!");
            } else {
                System.out.println("Application not found.");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }



    // DELETE APPLICATION
    public boolean deleteApplication(int id){

        String query="DELETE FROM applications WHERE id=?";

        try(Connection conn=DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(query)){

            ps.setInt(1,id);

            int rows=ps.executeUpdate();

            return rows>0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }



    // SEARCH BY COMPANY
    public List<Application> searchByCompany(int userId,String company){

        List<Application> list=new ArrayList<>();

        String query="SELECT * FROM applications WHERE user_id=? AND company LIKE ?";

        try(Connection conn=DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(query)){

            ps.setInt(1,userId);
            ps.setString(2,"%"+company+"%");

            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                Application app=new Application();

                app.setId(rs.getInt("id"));
                app.setUserId(rs.getInt("user_id"));
                app.setCompany(rs.getString("company"));
                app.setRole(rs.getString("role"));
                app.setLocation(rs.getString("location"));
                
                Double salary = rs.getDouble("salary");

                if(rs.wasNull()) {
                    app.setSalary(null);
                } else {
                    app.setSalary(salary);
                }
                
                app.setStatus(rs.getString("status"));
                app.setAppliedDate(rs.getDate("applied_date"));
                app.setNotes(rs.getString("notes"));
                app.setNextFollowupDate(rs.getDate("next_followup_date"));

                list.add(app);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }



    // FILTER BY STATUS
    public List<Application> filterByStatus(int userId,String status){

        List<Application> list=new ArrayList<>();

        String query="SELECT * FROM applications WHERE user_id=? AND status=?";

        try(Connection conn=DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(query)){

            ps.setInt(1,userId);
            ps.setString(2,status);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                Application app=new Application();

                app.setId(rs.getInt("id"));
                app.setCompany(rs.getString("company"));
                app.setRole(rs.getString("role"));
                app.setStatus(rs.getString("status"));

                list.add(app);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
    
    
    public void showStatusAnalytics(int userId) {

        String query = "SELECT status, COUNT(*) AS count FROM applications WHERE user_id=? GROUP BY status";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== APPLICATION STATUS ANALYTICS =====");

            while(rs.next()) {
                System.out.println(rs.getString("status") + " : " + rs.getInt("count"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void showFollowUps(int userId) {

        String query = """
            SELECT company, role, next_followup_date
            FROM applications
            WHERE user_id = ?
            AND next_followup_date >= CURDATE()
            ORDER BY next_followup_date
            """;

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== FOLLOW UP REMINDERS =====");

            boolean found = false;

            while(rs.next()) {
                found = true;

                System.out.println(
                    rs.getString("company") + " - " +
                    rs.getString("role") + " - Follow up on " +
                    rs.getDate("next_followup_date")
                );
            }

            if(!found) {
                System.out.println("No upcoming follow ups.");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }



    // DASHBOARD STATISTICS
    public void showDashboard(int userId) {

        try(Connection con = DBConnection.getConnection()) {

            String query = """
            SELECT 
            COUNT(*) as total,
            SUM(CASE WHEN status='Applied' THEN 1 ELSE 0 END) as applied,
            SUM(CASE WHEN status='Interview' THEN 1 ELSE 0 END) as interview,
            SUM(CASE WHEN status='Rejected' THEN 1 ELSE 0 END) as rejected,
            SUM(CASE WHEN status='Offer' THEN 1 ELSE 0 END) as offer,
            AVG(salary) as avgSalary,
            MAX(salary) as maxSalary,
            MIN(salary) as minSalary
            FROM applications
            WHERE user_id = ?
            """;

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                System.out.println("\n===== APPLICATION DASHBOARD =====");

                System.out.println("Total Applications : " + rs.getInt("total"));
                System.out.println("Applied            : " + rs.getInt("applied"));
                System.out.println("Interview          : " + rs.getInt("interview"));
                System.out.println("Rejected           : " + rs.getInt("rejected"));
                System.out.println("Offer              : " + rs.getInt("offer"));

                System.out.println("\nAverage Salary     : " + rs.getDouble("avgSalary"));
                System.out.println("Highest Salary     : " + rs.getDouble("maxSalary"));
                System.out.println("Lowest Salary      : " + rs.getDouble("minSalary"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}