package com.zoho.task.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {
	private static Connection connection = null;
	public static Connection connect() {
		System.out.println("Inside JDBC");
		
	      try {
	         Class.forName("org.postgresql.Driver");
	         
	         connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/application","postgres", "admin");
	         
	         //if(connection!=null) System.out.print("Successfull !");
	         
	       //  else System.out.print("JDBC Not working");
	      }
	      catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      
	      //System.out.println("Opened database successfully");
		
		
		return connection;
	}
}
