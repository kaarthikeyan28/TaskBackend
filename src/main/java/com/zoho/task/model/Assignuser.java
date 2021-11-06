package com.zoho.task.model;

//import com.localhost.queryoperation.*;
import java.io.*;  
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;  
import java.sql.*;  
import org.json.*;
import org.json.simple.*;

import com.zoho.task.JDBC.JDBC;

@WebServlet("/assignUser")
public class Assignuser extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
        res.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        res.addHeader("Access-Control-Allow-credentials", "true");
       Connection conn = JDBC.connect();
       Statement stmt=null;
       ResultSet rs=null;
       PrintWriter out = res.getWriter();  
       res.setContentType("application/json"); 
       
       try {
           stmt = conn.createStatement();
			rs = stmt.executeQuery("select createUserName from newUser;");
			
			JSONArray jarray=new JSONArray(); 
			while(rs.next()) {
			        	String createusername=rs.getString("createUserName");
			        	System.out.println(createusername+"***");
			        	JSONObject jobj=new JSONObject();
						jobj.put("username",createusername);
				        jarray.add(jobj);
			 }
	           out.println(jarray.toString()); 
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
}
