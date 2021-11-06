package com.zoho.task.controller;

//package com.localhost.javaservlet;
//import com.localhost.queryoperation.*;
import java.io.*;  
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;  
import java.sql.*;  
import javax.servlet.http.Cookie;
import org.json.*;
import org.json.simple.*;

@WebServlet("/Fetch")

public class Fetch extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
    res.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
    res.addHeader("Access-Control-Allow-credentials", "true");
	ServletOutputStream out=res.getOutputStream();       	
	Cookie ck[]=req.getCookies(); 
	JSONArray jarr=new JSONArray();
	if(ck[0].getValue().equals("true")) {
    System.out.println("Hello "+ck[1].getValue());
    
    try {
    JSONObject jobj=new JSONObject();
    jobj.put("result", "true");
    jobj.put("uname", ck[1].getValue());
    jobj.put("upass", ck[2].getValue());
    jarr.add(jobj);
    out.println(jarr.toString());
    }
    catch(Exception e){ 
 e.printStackTrace();
    }
	}
	
	else {
		System.out.println("No cookies");
		try {
		JSONObject jobj=new JSONObject();
		jobj.put("result", "false");
		jarr.add(jobj);
		out.println(jarr.toString());
		}catch(Exception e){ 
			 e.printStackTrace();
	    }
	}
         
         out.close();
       }
}