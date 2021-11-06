package com.zoho.task.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zoho.task.model.TaskList;

@WebServlet("/registerBackend")
public class CreateAccount extends HttpServlet {
	private TaskList task;
	private String registerName;
	private String registerUserName;
	private String registerPassword;

public void doPost(HttpServletRequest req,HttpServletResponse res)    throws IOException, ServletException
    {
	    
	    res.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		ServletOutputStream out=res.getOutputStream();
		out.println("Successfully login");
		
		String createname=req.getParameter("createname");
		String createusername=req.getParameter("createusername");
		String createpassword=req.getParameter("createpassword");
		out.println(createname+" "+createusername+" "+createpassword);
		
		
		TaskList task=new TaskList();
		task.adduser(createname,createusername,createpassword);


    }
}