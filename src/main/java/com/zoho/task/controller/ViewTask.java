package com.zoho.task.controller;

//package com.localhost.javaservlet;
import com.zoho.task.JDBC.*;
//import com.localhost.queryoperation.*;
import java.io.*;  
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;  
import java.sql.*;  
import javax.servlet.http.Cookie;
import org.json.*;
import org.json.simple.*;

@WebServlet("/ViewTask")
public class ViewTask extends HttpServlet {
        int id1;
        String name1;
        String smethod;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	   
	   System.out.println("Inside View Task !");
		
       res.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
       res.addHeader("Access-Control-Allow-credentials", "true");
       Connection conn = JDBC.connect();
	
       Statement stmt=null;
       ResultSet rs=null;
       ResultSet rw=null;
       
       PrintWriter out = res.getWriter(); 
	
       req.getRequestDispatcher("Login").include(req, res);
       HttpSession session=req.getSession(false);  
       if(session!=null){  
       String namee=(String)session.getAttribute("username");  
         
       System.out.println("Hello, "+namee+" Welcome to Profile");  
       smethod=req.getParameter("selmethod");
       System.out.println("sel "+smethod); 
        
       LoginAccount logacc=new LoginAccount();
       res.setContentType("application/json"); 
       try {
    	 //  Class.forName("org.postgresql.Driver");
           //conn = DriverManager.getConnection(url, user, password);
       stmt = conn.createStatement();
	   rw = stmt.executeQuery("select userId,createname from newUser where createusername='"+logacc.user+"';");
	   System.out.println(logacc.user);
	   if(rw.next()){
                 id1=rw.getInt("userId");
		 name1=rw.getString("createname");
           }
		System.out.println(id1);

		if(smethod.equals("completed")) {
			rs = stmt.executeQuery("select taskid,taskName,taskCreatedby,taskStatus,taskDescription,taskCreatedTime from taskDetails where userId='"+id1+"'and taskStatus='completed';");
			JSONArray jarray=new JSONArray(); 
			while(rs.next()) {
					    int taskid=rs.getInt("taskid");
			        	String taskName=rs.getString("taskName");
			        	String taskCreatedby=rs.getString("taskCreatedby");
			        	String taskStatus=rs.getString("taskStatus");
			        	String taskDescription=rs.getString("taskDescription");
			        	String taskCreatedTime=rs.getString("taskCreatedTime");
			        	System.out.println(taskid+" "+taskName+" "+taskCreatedby+" "+taskStatus+" "+taskDescription+"***");

			        	JSONObject jobj=new JSONObject();

					    jobj.put("taskid",taskid);
			        	jobj.put("taskname", taskName);
			        	jobj.put("taskcreatedby", taskCreatedby);
			        	jobj.put("taskstatus", taskStatus);
			        	jobj.put("taskdescription", taskDescription);
			        	jobj.put("taskcreatedtime", taskCreatedTime);
		                jobj.put("result","task available");
					    jobj.put("assigned",name1);
			        	jarray.add(jobj);

				
			 }
	           out.println(jarray.toString()); 
	           }
		else {
		if(smethod.equals("all")) {
                        rs=null;
			rs = stmt.executeQuery("select taskid,taskName,taskCreatedby,taskStatus,taskDescription,taskCreatedTime from taskDetails where userId='"+id1+"';");
		System.out.println("time come");
}
		else if(smethod.equals("nottaken")){
			System.out.println("ELSE IF");
           rs = stmt.executeQuery("select taskid,taskName,taskCreatedby,taskStatus,taskDescription,taskCreatedTime from taskDetails where userId='"+id1+"'and taskStatus='nottaken';");
           System.out.println("Resultset");
		}
		JSONArray jarray=new JSONArray();
           if(!rs.next()) {
        	   JSONObject jobj=new JSONObject();
                   jobj.put("result","NOtasksavailable");
		   jobj.put("assigned",name1);
		   jarray.add(jobj);
                   out.println(jarray.toString()); 
                   System.out.println("IM here for jarray");
           }
           else {
           while(rs.next()) {
			int taskid=rs.getInt("taskid");
	        	String taskName=rs.getString("taskName");
	        	String taskCreatedby=rs.getString("taskCreatedby");
	        	String taskStatus=rs.getString("taskStatus");
	        	String taskDescription=rs.getString("taskDescription");
	        	String taskCreatedTime=rs.getString("taskCreatedTime");
	        	System.out.println(taskid+" "+taskName+" "+taskCreatedby+" "+taskStatus+" "+taskDescription+"..........");
	        
	        	JSONObject jobj=new JSONObject();
			jobj.put("taskid",taskid);
	        	jobj.put("taskname", taskName);
	        	jobj.put("taskcreatedby", taskCreatedby);
	        	jobj.put("taskstatus", taskStatus);
	        	jobj.put("taskdescription", taskDescription);
	        	jobj.put("taskcreatedtime", taskCreatedTime);
                 jobj.put("result","task available");
			jobj.put("assigned",name1);
	        	jarray.add(jobj);
           }
           out.println(jarray.toString()); 
           
           System.out.println(jarray.toString());
           }
           
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
       else{  
    	   try {
    	   JSONArray jarray=new JSONArray();
    	   JSONObject jobj=new JSONObject();
    	   jobj.put("result","loginfirst");
    	   jarray.add(jobj);
    	   out.println(jarray.toString());
           System.out.println("Please login first"); 
    	   }catch(Exception e) {
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

}
