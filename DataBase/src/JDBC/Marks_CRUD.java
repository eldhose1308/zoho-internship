package JDBC;

import java.sql.*;
import java.util.*;




class FacultyDesignation{
    private int DesignationID;
    private String Designation;

    public FacultyDesignation() {
   
    }
    
    
    public FacultyDesignation(int DesignationID,String Designation) {
        this.DesignationID = DesignationID;
        this.Designation = Designation;
    }

    public void setDesignationID(int DesignationID) {
        this.DesignationID = DesignationID;
    }
    
    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }
    
    public int getDesignationID() {
        return DesignationID;
    }
    
    public String getDesignation() {
        return Designation;
    }
}



class JDBC_Conn{
	private String hostname,username,database,password;
	private Connection con;
	private String statusMessage;
	
	public JDBC_Conn(){
		hostname = "jdbc:mysql://localhost:3306/";
		username = "root";
		password = "Eldhose*13";
		database = "zoho";
		
		String connectionURL = hostname + database;
		try {
			con = DriverManager.getConnection(connectionURL, username, password); 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			}
	}
	

	
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	public String getStatusMessage() {
		return this.statusMessage;
	}
	

	
	
	public boolean insertData(String Designation) {
		boolean queryStatus = false;
		
		try {
			Statement statement = con.createStatement();
			String insertQuery = "INSERT into FacultyDesignation(Designation) values ('"+ Designation +"')";
			queryStatus = (statement.executeUpdate(insertQuery) > 0) ? true : false;
		} catch (SQLException e) {
			this.setStatusMessage(e.getMessage());
		}
		
		return queryStatus;
	}
	
	public boolean updateData(int DesignationID,String Designation) {
		boolean queryStatus = false;
		try {
			Statement statement = con.createStatement();
			String updateQuery = "UPDATE FacultyDesignation SET Designation ='" + Designation + "' where DesignationID="+ DesignationID;
			queryStatus = (statement.executeUpdate(updateQuery) > 0) ? true : false;
		}catch(SQLException e) {
			this.setStatusMessage(e.getMessage());
		}
		return queryStatus;
	}
	
	public ArrayList<FacultyDesignation> selectData() {
		ArrayList<FacultyDesignation> designationsList = new ArrayList<FacultyDesignation>();
		
		try {
			Statement statement = con.createStatement();
			String selectQuery = "SELECT * from FacultyDesignation";
			ResultSet resultSet = statement.executeQuery(selectQuery);
			
			while(resultSet.next()) {
				FacultyDesignation facultyDesignation = new FacultyDesignation();
				facultyDesignation.setDesignationID(resultSet.getInt("DesignationID"));
				facultyDesignation.setDesignation(resultSet.getString("Designation"));
				
				designationsList.add(facultyDesignation);

			}
		}catch(SQLException e) {
			this.setStatusMessage(e.getMessage());
		}
		
		return designationsList;
	}
	
	public boolean deleteData(int DesignationID) {
		boolean queryStatus = false;
		try {
			Statement statement = con.createStatement();
			String deleteQuery = "DELETE from FacultyDesignation where DesignationID =" + DesignationID;
			queryStatus = (statement.executeUpdate(deleteQuery) > 0) ? true : false;
			
			if(!queryStatus) 
				setStatusMessage("ID DOESN'T EXIST");
				
		}catch(SQLException e) {
			this.setStatusMessage(e.getMessage());
		}
		
	
		
		return queryStatus;
	}
	
}


public class Marks_CRUD {

	public static void main(String[] args) {
		JDBC_Conn jdbc = new JDBC_Conn();
		
		
		Scanner scanner = new Scanner(System.in);
		
		int choice = 1;
		boolean queryStatus;

		String designation;
		int designationID;
		do {
			
			System.out.println("-------------------");
			System.out.println("(1): Insert \n(2): Select \n(3): Update \n(4): Delete \n(5): Exit");
			System.out.println("-------------------");

			try {
				choice = scanner.nextInt();
			}catch(Exception e) {
				choice = 1;
			}
			
			switch(choice) {
				case 1:
					System.out.println("Enter designation to add :");
					designation = scanner.next();
					queryStatus = jdbc.insertData(designation);
					if(queryStatus)
						System.out.println("Designation added successfully");
					else
						System.out.println(jdbc.getStatusMessage());
					break;
					
				case 2:
					ArrayList<FacultyDesignation> designationsList = jdbc.selectData();
					System.out.println("Designations list :");

					designationsList.forEach((designations) -> {
						System.out.println(designations.getDesignationID() + " - " +designations.getDesignation());
					});
					break;
					
				case 3:
					System.out.println("Enter ID to update :");
					designationID = scanner.nextInt();
					System.out.println("Enter designation text :");
					designation = scanner.next();
					
					queryStatus = jdbc.updateData(designationID,designation);
					if(queryStatus)
						System.out.println("Designation updated successfully");
					else
						System.out.println(jdbc.getStatusMessage());
					break;
					
				case 4:
					System.out.println("Enter ID to delete :");
					designationID = scanner.nextInt();
					
					queryStatus = jdbc.deleteData(designationID);
					if(queryStatus)
						System.out.println("Designation deleted successfully");
					else
						System.out.println(jdbc.getStatusMessage());
					break;
					
				case 5:
					System.out.println("Exiting");
					break;
					
				default:
					System.out.println("Invalid Choice");
					break;
					

			}
		}while(choice != 5);
		scanner.close();
		
		
		
		
	}

}
