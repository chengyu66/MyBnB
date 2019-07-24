package database;


import java.sql.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class JDBC_mybnb {

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        initiatetables();
        while(! line.equalsIgnoreCase("exit")) {
            System.out.println("Please enter which operations to perform:"
            		+ "user(User operation), reports(Admin operations)"
            		+ "or exit(exit the program).");
            line = br.readLine();
            switch(line) {
            	case "users":
            		break;
            	case "reports":
            		break;
            	default:
            		break;
            }
        }
        
        br.close();
    }
    
    public static Connection Connect() throws Exception{
        try {
        	String dbClassName = "com.mysql.jdbc.Driver";
        	String CONNECTION = "jdbc:mysql://localhost:3306/cscc43db";
        	Class.forName(dbClassName);
            String user = "root";
            String password = "root";
            Connection conn = DriverManager.getConnection(CONNECTION, user, password);
            System.out.println("Successfully connected to MySQL!");
            return conn;
            
        } catch (SQLException e) {
            System.err.println("Connection error occured!");
            return null;
        }
    }
    
    public static void createtable(String query)throws Exception{
    	Connection conn = null;
    	PreparedStatement table = null;
    	try {
    		conn = Connect();
	    	table = conn.prepareStatement(query);
	    	table.executeUpdate();
	    	
	    } catch (SQLException e) {
	    	System.err.println(e);
	        System.err.println("Connection error occured!");
	        
	    } finally {
	    	table.close();
	    	conn.close();
	    }
    		
    }
    
    public static void initiatetables() throws Exception {
    		String listing, user, booking, calendar;
    		listing = "CREATE TABLE IF NOT EXISTS "
	    			+ "listings(lid int NOT NULL AUTO_INCREMENT, "
	    			+ "name varchar(255), "
	    			+ "hid int, "
	    			+ "ltype varchar(255), "
	    			+ "adress varchar(255), "
	    			+ "city varchar(255), "
	    			+ "postcode int, "
	    			+ "country varchar(255), "
	    			+ "latitude float, "
	    			+ "longitude float, "
	    			+ "aid int, "
	    			+ "PRIMARY KEY (lid))";
    		
    		user = "CREATE TABLE IF NOT EXISTS "
    				+ "user(uid int NOT NULL AUTO_INCREMENT, "
    				+ "password varchar(16), "
    				+ "name varchar(255), "
    				+ "utype bool, "
    				+ "uaddress varchar(255), "
    				+ "birth date, "
    				+ "ocupation varchar(255), "
    				+ "sin int(9), "
    				+ "payment varchar(255), "
    				+ "PRIMARY KEY (uid))";
    				
    		booking = "CREATE TABLE IF NOT EXISTS "
	    			+ "booking(bid int NOT NULL AUTO_INCREMENT, "
    				+ "lid int, "
    				+ "uid int, "
    				+ "checkin datetime, "
    				+ "checkout datetime, "
    				+ "cancelation bool, "
    				+ "hostcomment varchar(255), "
    				+ "rentercomment varchar(255), "
    				+ "PRIMARY KEY (bid))";
    		
    		calendar = "CREATE TABLE IF NOT EXISTS "
	    			+ "calendar(lid int, "
    				+ "startdate date, "
	    			+ "enddate date, "
    				+ "price float)";
    		
    		createtable(listing);
    		createtable(user);
    		createtable(booking);
    		createtable(calendar);
    }

}