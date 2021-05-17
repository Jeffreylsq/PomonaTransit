package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;



import jdbc.DBUtils;
import transit.PomonaTransit;

public class TestDemo {
	
	@Test
	public void test(){
		Connection conn ;
		try {
			conn = DBUtils.getConnection();
			System.out.println(conn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void test2(){
		Connection conn;
		try {
			conn = DBUtils.getConnection();
			Statement stmt = conn.createStatement();
			
			PomonaTransit.displayTrip(stmt);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	@Test
	public void test3(){
		Connection conn;
		try {
			conn = DBUtils.getConnection();
			Statement stmt = conn.createStatement();
			PomonaTransit.deleteTripOffering(stmt);
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Test
	public void test4(){
		Connection conn;
		try {
			conn = DBUtils.getConnection();
			Statement stmt = conn.createStatement();
			PomonaTransit.addTripOffering(stmt);
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	@Test
	public void test5(){
		Connection conn;
		try {
			conn = DBUtils.getConnection();
			Statement stmt = conn.createStatement();
			PomonaTransit.changeBusOrDriver(stmt);
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	
	@Test
	public void test6(){
		Connection conn;
		try {
			conn = DBUtils.getConnection();
			Statement stmt = conn.createStatement();
			PomonaTransit.displayTripStopInfo(stmt);
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	@Test
	public void test7(){
		Connection conn;
		try {
			conn = DBUtils.getConnection();
			Statement stmt = conn.createStatement();
			PomonaTransit.deleteBus(stmt);
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Test
	public void test8(){
		Connection conn;
		try {
			conn = DBUtils.getConnection();
			Statement stmt = conn.createStatement();
			PomonaTransit.addActualStopInfo(stmt);
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Test
	public void test9(){
		Connection conn;
		try {
			conn = DBUtils.getConnection();
			Statement stmt = conn.createStatement();
			PomonaTransit.displayWeeklySchedule(stmt);
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
}
