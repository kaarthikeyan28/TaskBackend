package com.zoho.task.model;

//package com.localhost.javaservlet;
//import com.localhost.queryoperation.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;
import org.json.simple.*;

import com.zoho.task.controller.LoginAccount;

@WebServlet("/addtask")
public class taskadd extends HttpServlet {

public void doPost(HttpServletRequest req,
                      HttpServletResponse res)
        throws IOException, ServletException
    {
    res.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
    res.addHeader("Access-Control-Allow-credentials", "true");
		LoginAccount logacc=new LoginAccount();
		res.setContentType("application/json"); 
		String userN=logacc.user;
		ServletOutputStream out=res.getOutputStream();
		String taskname=req.getParameter("taskname");
		String taskdescription=req.getParameter("taskdescription");
		String taskassign=req.getParameter("taskassign");
		TaskListBackend task=new TaskListBackend();
		JSONArray jarr=new JSONArray();
        task.addtask(jarr,taskname,taskdescription,taskassign,userN);
		out.println(jarr.toString());

    }
}
