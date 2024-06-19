package com.pst.ohc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
public class onlineHealthCare {
	private static final String INSERT_QUERY = "insert into onlinehealthcare values(?,?,?,?,?,?)";
	private static final String VIEW_QUERY = "select * from  onlinehealthcare where appointment_id=?";
	static onlineHealthCare ohs=new onlineHealthCare();
	static Random random=new Random();
static Scanner scanner=new Scanner(System.in);
	public static void main(String[] args) {
		String choose="no";
		do{
		System.out.println("Welcome to PST online health care");
		System.out.println("1.Book Appointment");
		System.out.println("2.View Appointment");
		System.out.println("Choose your option:");
		int option=scanner.nextInt();
switch (option) {
case 1: 
ohs.bookAppointment();
break;
case 2:
ohs.viewAppointment();
break;
default:
System.out.println("Error.....");
}
System.out.println("Thank you for booking your appointment......");
scanner.nextLine();
System.out.println("Do you want do to continue ?(Y/N):");
	choose =scanner.nextLine();
	
		}
while(choose.equals("yes"));
	
	}	
public void bookAppointment() {
	System.out.println("Enter patient id:");
	int patientId=scanner.nextInt();
	scanner.nextLine();
	System.out.println("Enter patient Name:");
	String patientName=scanner.nextLine();
	System.out.println("Enter Gender:");
     String  gender=scanner.nextLine();
    System.out.println("Enter Age:");
    int age=scanner.nextInt();
    scanner.nextLine();
    System.out.println("Enter Disease:");
    String disease=scanner.nextLine();
    int appointmentId=random.nextInt(10000,999999);
    System.out.println("Appointment booked,Thid is your appointment Id:"+appointmentId);
try {
	Connection con=ohs.getOracleConnection();
	PreparedStatement ps=con.prepareStatement(INSERT_QUERY);
	ps.setInt(1, appointmentId);
	ps.setInt(2,patientId);
	ps.setString(3, patientName);
	ps.setString(4, gender);
	ps.setInt(5, age);
	ps.setString(6,disease);
	int i=ps.executeUpdate();
	con.close();
} catch (Exception e) {
	e.printStackTrace();
}
}
public void viewAppointment() {
    System.out.println("Enter the appointmentId:");
    int appointmentId=scanner.nextInt();
try {
	Connection con=ohs.getOracleConnection();
    PreparedStatement ps=con.prepareStatement(VIEW_QUERY);
    ps.setInt(1, appointmentId);
    ResultSet rs=ps.executeQuery();
    System.out.println("___________________________________________________________");
    System.out.println("patient id     patient name     Gender     Age      Deceise");
    System.out.println("___________________________________________________________");
while (rs.next()) {
	int a=rs.getInt(2);
	String b=rs.getString(3);
	String c=rs.getString(4);
	int d=rs.getInt(5);
	String e=rs.getString(6);
	System.out.println(a+"      "+b+"    "+c+"      "+d+"     "+e);
    System.out.println("_______________________________________________________");
    System.out.println("appointment id:                         "+appointmentId);
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date=new Date();
    System.out.println("Date:                                     "+sdf.format(date));
System.out.println("____________________________________________________________");
  }
} catch (Exception e) {
	e.printStackTrace();
     }
   }
Connection con=null;
private Connection getOracleConnection() {
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
 DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jdbc","jdbc");
	} catch (Exception e) {
		e.printStackTrace();
	}
return con;
}
}



