package com.zoho.task.model;
//import com.localhost.dbconnect.*;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import javax.servlet.annotation.WebServlet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.zoho.task.JDBC.JDBC;

//import org.json.JSONArray;
//import org.json.JSONObject;

import java.time.LocalDateTime;  


public class TaskList{   
	public Connection conn;
	
	public TaskList(){
		 conn = JDBC.connect();
	}
	

	
//	public Connection connect() {
//		System.out.println("Connect called ");
//		
//   	    final String url = "jdbc:postgresql://localhost:5432/TaskList";
//        final String user = "postgres";
//        final String password = "admin";
//      
//       try {
//	       Class.forName("org.postgresql.Driver"); 
//         //  conn = DriverManager.getConnection(url, user, password);
//           conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TaskList","postgres", "admin");
//           System.out.println(conn);
//           
//           System.out.println("Connected to the PostgreSQL server successfully.");
//           
//           }
//       catch (Exception e) {
//           System.out.println(e.getMessage());
//       }
//
//       return conn;
//   }
	
	
	public void adduser(String createname,String createusername,String createpassword){
//		//connect();
//		if(conn==null) {
//			System.out.println("DB connection not available");
//	        }
//		else {
		System.out.println("Add User called !");
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt = conn.createStatement();
			String sql = "INSERT INTO newuser (createName,createUserName,createPassword) "
					+ "VALUES ('"+createname+"','"+createusername+"',"+"crypt('"+createpassword+"',gen_salt('bf')"+"));";
	        stmt.executeUpdate(sql);
	        rs=stmt.executeQuery("select * from newuser");
	        
	        while(rs.next()) {
	        	int userid=rs.getInt("userId");
			String name=rs.getString("createName");
	        	String username=rs.getString("createUserName");
	        	String userpassword=rs.getString("createPassword");
	        	System.out.println( "ID = " + userid );
	            System.out.println( "NAME = " + name);
			    System.out.println( "USERNAME = " + username);
	            System.out.println( "PASSWORD = " + userpassword );
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
			finally {
				try {
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			}
		}
	
	
	public String logincheck(String loginUsername,String loginPassword){
		//connect(); 
		String result = " ";
		if(conn==null) {
			System.out.println("DB connection not available");
	     }
		else {
			Statement stmt=null;
			ResultSet rw=null;
			try {
				stmt = conn.createStatement();
                 System.out.println(loginUsername);
				 System.out.println(loginPassword);
				  rw=stmt.executeQuery("select createUserName,(createPassword=crypt('"+loginPassword+"',createPassword)) as password from newuser where createUsername='"+loginUsername+"'");
				 if(!rw.next()) {
					 result="Userinvalid";
				 }
				 else {
					 Boolean passmatch=rw.getBoolean("password");
					 if(passmatch) {
						 result="Successfullogin";
					 }
					 else {
						 result="Passinvalid";
					 }
					
				 }
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					if(rw!=null) {
						rw.close();
					}
					if(stmt!=null) {
						stmt.close();
					}
					if(conn!=null) {
						conn.close();
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		
	}
		return result;
	}
	
	
	public void addtask(JSONArray jarr,String taskname,String taskdescription,String taskassign,String userN) {
		
	//	connect();
		
		if(conn==null) {
			System.out.println("DB connection not available");
	     }
		
		else {
			Statement stmt=null;
			ResultSet rs=null;
			
			try {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();  
				stmt = conn.createStatement();
				rs=stmt.executeQuery("select userId from newUser where createusername='"+taskassign+"';");
			
                if(rs.next()) {                
				int uid=rs.getInt("userId");
				String sql = "INSERT INTO taskDetails (taskName,taskCreatedby,taskStatus,taskDescription,taskCreatedTime,userId) "
		                + "VALUES ('"+taskname+"','"+userN+"','nottaken','"+taskdescription+"','"+dtf.format(now)+"','"+uid+"');";
				stmt.executeUpdate(sql);
				         JSONObject jobj=new JSONObject();
				         jobj.put("result", "taskadded");
				         jarr.add(jobj);
                }
                                
			}
			
			catch(Exception e){
				e.printStackTrace();
			}
			
			finally {
				try {
					if(rs!=null) {
						rs.close();
					}
					if(stmt!=null) {
						stmt.close();
					}
					if(conn!=null) {
						conn.close();
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		
		}
	}
	
	public void updatetask(int idtask,String tstatus) {
		//connect();
		if(conn==null) {
			System.out.println("DB connection not available");
	     }
		else {
			Statement stmt=null;
			try {
				stmt = conn.createStatement();
				String sql = "update taskDetails set taskstatus='"+tstatus+"' where taskid='"+idtask+"';";  
		        stmt.executeUpdate(sql);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			}
		}
	}
}