package transit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;


import jdbc.DBUtils;

/**
 * Main class of Pomona Transit
 * @author Tianyu Wei
 *
 */
public class PomonaTransit {
	
	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);
		int input;
		Connection conn;
		try {
			conn = DBUtils.getConnection();
			Statement stmt = conn.createStatement();
			 
			//Display the menu
			showMenu();
			
			while(true) {
				System.out.println("Please enter your choice...");
				input = kb.nextInt();
				if (input == 1) {
					displayTrip(stmt);
				} else if (input == 2) {
					deleteTripOffering(stmt);
				} else if (input == 3) {
					addTripOffering(stmt);
				} else if (input == 4 || input == 5){
					changeBusOrDriver(stmt);	
				} else if (input == 6) {
					displayTripStopInfo(stmt);
				} else if (input == 7) {
					displayWeeklySchedule(stmt);
				} else if (input == 8) {
					addDriver(stmt);
				} else if (input == 9) {
					addBus(stmt);
				} else if (input == 10) {
					deleteBus(stmt);
				} else if (input == 11) {
					addActualStopInfo(stmt);
				}else if (input == 0){
					kb.close();
					conn.close();
					break;
				}else {
					System.out.println("Invaid input, please try again");
				}
				System.out.println();
			}	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void showMenu() {
		System.out.println("******Pomona Transit System*****");
		System.out.println("1.Display schedule of all trip");
		System.out.println("2.Delete a trip offering");
		System.out.println("3.Add trip offering");
		System.out.println("4.Change the driver for a given trip offering");
		System.out.println("5.Change the bus for a given trip");
		System.out.println("6.Display the stops of a given trip");
		System.out.println("7.Display the weekly schedule of a driver");
		System.out.println("8.Add a driver");
		System.out.println("9.Add a bus");
		System.out.println("10.Delete a bus");
		System.out.println("11.Insert the actual trip data");
		System.out.println("0.Quiz");
		
	}
	/**
	 * Method for display trip information
	 * @param stmt
	 */
	public static void displayTrip(Statement stmt) {
		
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter start location");
		String startLocation = kb.nextLine();
		System.out.println("Please enter destination name");
		String finalDestination = kb.nextLine();
		System.out.println("Please enter date");
		String date = kb.nextLine();
		
		try {
			String sql = "SELECT t.startLocation, t.finalDestination, o.* "
				       + "FROM TripOffering o JOIN Trip t "
				       + "ON o.tripNumber = t.tripNumber "
				       + "WHERE t.startLocation = '" + startLocation 
				       + "' AND t.FinalDestination = '" + finalDestination
				       + "' AND o.date = '" + date + "'";
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsMetaData = rs.getMetaData();
			String varColNames = "";
			int varColCount = rsMetaData.getColumnCount();
			
		
			
			//Print out col name
			for (int col = 1; col <= varColCount; col++) {
				
				varColNames = varColNames + rsMetaData.getColumnName(col) + "\t";
			}
			System.out.println(varColNames);
			
				
			//Display column values
			while (rs.next()) {
				for (int col = 1; col <= varColCount; col++) {
					String str = "";
					if (col == 3) {
						   str = String.format("%-15s", rs.getString(col));
					} else if (col == 6) {
						   str = String.format("%-25s", rs.getString(col));
					} else if (col == 7) {
						   str = String.format("%-16s", rs.getString(col));
					} else {
						   str = String.format("%-20s", rs.getString(col));
					}
					System.out.print(str);
				}
				System.out.println();
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("No schedule from " + startLocation + " to " + finalDestination);
		}
	}
	
	/**
	 * Method for delete trip Offering
	 * @param stmt
	 */
	public static void deleteTripOffering(Statement stmt) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter Trip number");
		String tripNumber = kb.nextLine();
		System.out.println("Please enter Date");
		String date = kb.nextLine();
		System.out.println("Please enter ScheduledStartTime");
		String scheduledStartTime = kb.nextLine();
		
		try {
			String sql = "DELETE FROM TripOffering "
					   + "WHERE TripNumber = '" + tripNumber + "' "
					   + "And Date = '" + date + "' "
					   + "AND ScheduledStartTime = '" + scheduledStartTime + "'";
			int row = stmt.executeUpdate(sql);
			if (row == 1) {
				System.out.println("Delete Trip offering successfully.");
			} else {
				System.out.println("Delete Trip offering unsuccessfully, please check your data format.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("No Trip offering with TripNumber: " + tripNumber + " , Date:" + date + " , ScheduledStartTime: " + scheduledStartTime);
		}
	}
	
	/**
	 *  Method for add a set of trip offering
	 */
	public static void addTripOffering(Statement stmt) {
		Scanner kb = new Scanner(System.in);
		String tripNumber = "";
		String date = "";
		String scheduledStartTime = "";
		String scheduledArrivalTimeDestination = "";
		String driverName = "";
		String busID = "";
		String option = "";
		
		try {
			while (true) {
				System.out.println("Please enter trip number");
				tripNumber = kb.nextLine();
				System.out.println("Please enter date");
				date = kb.nextLine();
				System.out.println("Please enter scheduledStartTime");
				scheduledStartTime = kb.nextLine();
				System.out.println("Please enter scheduled Arrival Destination Time");
				scheduledArrivalTimeDestination = kb.nextLine();
				System.out.println("Please enter driverName");
				driverName = kb.nextLine();
				System.out.println("Please enter busID");
				busID = kb.nextLine();
				
				String sql = "INSERT INTO TripOffering values('" + tripNumber + "','" 
				            + date + "','" + scheduledStartTime + "','" 
						    + scheduledArrivalTimeDestination +"','" + driverName 
						    + "','" + busID + "')";
				int row = stmt.executeUpdate(sql);
				if (row == 1) {
					System.out.println("Insertion successfully.");
				} else {
					System.out.println("Insertion Unsuccessfully, please check your data format.");
				}
				
				while (!option.equals("N") && !option.equals("Y")) {
					System.out.println("Add another trip offering Y/N");
					option = kb.nextLine().toUpperCase();
					if (option.equals("N")){
						return;
					} 
				}
				option = "";
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Exception, please check your data format.");
		}
	}
	
	/**
	 * Method for changing bus or driver
	 * @param stmt
	 */
	public static void changeBusOrDriver(Statement stmt) {
		Scanner kb = new Scanner(System.in);
		String option = "";
		
		while (!option.equals("BUS") && !option.equals("DRIVER")) {
			System.out.println("Do you want to change Bus or Driver bus/driver?");
			option = kb.nextLine().toUpperCase();
		}
		System.out.println("Please enter the "+ option +" information that you want to change");
		String info = kb.nextLine();
		System.out.println("Please enter Trip number");
		String tripNumber = kb.nextLine();
		System.out.println("Please enter Date");
		String date = kb.nextLine();
		System.out.println("Please enter ScheduledStartTime");
		String scheduledStartTime = kb.nextLine();
		
		if (option.equals("BUS")) {
			option = "BUSID";
		} else {
			option = "DriverName";
		}
		try {
			String sql = "UPDATE TripOffering "
					   + "SET " + option + " = '" + info + "' " 	
					   + "WHERE TripNumber = '" + tripNumber + "' "
					   + "And Date = '" + date + "' "
					   + "AND ScheduledStartTime = '" + scheduledStartTime + "'";
			int row = stmt.executeUpdate(sql);
			if (row == 1) {
				System.out.println("Update " + option + " successfully.");
			} else {
				System.out.println("Update " + option + " unsuccessfully.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("No Trip offering with TripNumber: " + tripNumber + " , Date:" + date + " , ScheduledStartTime: " + scheduledStartTime);
		}
	}
	
	/**
	 * Method for showing Trip stop information
	 * @param stmt
	 */
	public static void displayTripStopInfo(Statement stmt) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter trip number");
		String tripNumber = kb.nextLine().trim();
	
		try {
			String sql = "SELECT * "
				       + "FROM TripStopInfo " 
				       + "WHERE TripNumber = '" + tripNumber + "' ORDER BY SequenceNumber asc";
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsMetaData = rs.getMetaData();
			String varColNames = "";
			int varColCount = rsMetaData.getColumnCount();
			
			//If ResultSet is empty it will be return
			
			
			//Print out col name
			for (int col = 1; col <= varColCount; col++) {
				varColNames = varColNames + rsMetaData.getColumnName(col) + "  \t";
			}
			System.out.println(varColNames);
			
				
			//Display column values
			while (rs.next()) {
			 	for (int col = 1; col <= varColCount; col++) {
					String str = "";
					if (col == 3) {
						   str = String.format("%-15s", rs.getString(col));
					} else if (col == 6) {
						   str = String.format("%-25s", rs.getString(col));
					} else if (col == 7) {
						   str = String.format("%-16s", rs.getString(col));
					} else {
						   str = String.format("%-20s", rs.getString(col));
					}  
					str = String.format("%-20s", rs.getString(col));
					System.out.print(str);
				} 
				System.out.println(); 
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("No stops from tripNumber " + tripNumber );
		}
	}
	
	/**
	 * Method for displaying weekly schedule of a given driver and date
	 * @param stmt
	 * @throws ParseException 
	 */
	public static void displayWeeklySchedule(Statement stmt) throws ParseException {
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter driver name");
		String driverName = kb.nextLine().trim();
		System.out.println("Please enter date");
		String date = kb.nextLine().trim();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calDate = new GregorianCalendar();
		calDate.setTime(sdf.parse(date));
		
		try {
			for (int i = 1; i <= 7; i++) {
				date = sdf.format(calDate.getTime());
				System.out.println("#########Day " + i + " ##########");
				String sql = "SELECT TripNumber, Date, ScheduledStartTime, ScheduleArrivalTimeDestination, BusID "
					       + "FROM TripOffering "
					       + "WHERE driverName = '" + driverName 
					       + "' AND date = '" + date + "'";
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsMetaData = rs.getMetaData();
				String varColNames = "";
				int varColCount = rsMetaData.getColumnCount();
			
				//Print out col name
				for (int col = 1; col <= varColCount; col++) {
					varColNames = varColNames + rsMetaData.getColumnName(col) + "\t";
				}
				System.out.println(varColNames);
				
					
				//Display column values
				while (rs.next()) {
					for (int col = 1; col <= varColCount; col++) {
						String str = "";
						if (col == 3 || col == 4) {
							   str = String.format("%-25s", rs.getString(col));
						} else {
							   str = String.format("%-15s", rs.getString(col));
						}
						System.out.print(str);
					}
				}
				System.out.println();
				//add one day 
				calDate.add(Calendar.DATE, 1);
			  
		  }	
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("No schedule from  driver " + driverName);
		}
	}
	
	/**
	 * Add a driver
	 * @param stmt
	 */
	public static void addDriver(Statement stmt) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter driver name");
		String driverName = kb.nextLine();
		System.out.println("Please enter telephone number");
		String telephoneNumber = kb.nextLine();
		try {
			String sql = "INSERT INTO driver values('" + driverName +"','" + telephoneNumber + "')";
			int row = stmt.executeUpdate(sql);
			if (row == 1) {
				System.out.println("Insertion successfully.");
			} else {
				System.out.println("Insertion Unsuccessfully, please check your data format.");
			}
		} catch (Exception e) {
				e.printStackTrace();
				System.out.println("SQL Exception, please check your data format.");
		}	
	}
	
	/**
	 * Add a bus
	 * @param stmt
	 */
	public static void addBus(Statement stmt) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter bus ID");
		String busID = kb.nextLine();
		System.out.println("Please enter model");
		String model = kb.nextLine();
		System.out.println("Please enter year");
		String year = kb.nextLine();
		try {
			String sql = "INSERT INTO bus values('" + busID +"','" + model + "','" + year + "')";
			int row = stmt.executeUpdate(sql);
			if (row == 1) {
				System.out.println("Insertion successfully.");
			} else {
				System.out.println("Insertion Unsuccessfully, please check your data format.");
			}
		} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Insertion unsuccessfully, please check your data format.");
		}
	}
	
	/**
	 * Delete a bus
	 * @param stmt
	 */
	public static void deleteBus(Statement stmt) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter Bus ID that you want to delete");
		String busID = kb.nextLine();
		
		try {
			String sql = "DELETE FROM Bus "
					   + "WHERE BusID = '" + busID + "'";
			int row = stmt.executeUpdate(sql);
			if (row == 1) {
				System.out.println("Delete a bus successfully.");
			} else {
				System.out.println("Delete a bus unsuccessfully, please check your data format.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("No bus info with busID: " + busID);
		}
	}
	
	/**
	 * 
	 */
	public static void addActualStopInfo(Statement stmt) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter trip number");
		String tripNumber = kb.nextLine();
		System.out.println("Please enter date");
		String date = kb.nextLine();
		System.out.println("Please enter schedule start time");
		String scheduleStartTime = kb.nextLine();
		System.out.println("Please enter stop number");
		String stopNumber = kb.nextLine();
		System.out.println("Please enter scheduled arrival time");
		String scheduleArrivalTime = kb.nextLine();
		System.out.println("Please enter actual arriving time");
		String actualArrivalTime = kb.nextLine();
		System.out.println("Please enter Actual start time");
		String actualStartTime = kb.nextLine();
		System.out.println("Please enter number of passenger in");
		String passIn = kb.nextLine();
		System.out.println("Please enter number of passenger out");
		String passOut = kb.nextLine();
		
		try {
			String sql = "INSERT INTO ActualStopInfo values('" + stopNumber 
					    + "','" + date + "','" + scheduleStartTime + "','"
					    + tripNumber + "','" + actualArrivalTime + "','"
					    + scheduleArrivalTime + "','" + actualStartTime + "','"
					    + passIn + "','" + passOut + "')";
			int row = stmt.executeUpdate(sql);
			if (row == 1) {
				System.out.println("Insertion successfully.");
			} else {
				System.out.println("Insertion Unsuccessfully, please check your data format.");
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Insertion unsuccessfully, please check your data format.");
		}
	}
}
