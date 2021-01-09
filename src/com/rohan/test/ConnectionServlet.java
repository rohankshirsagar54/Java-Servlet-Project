package com.rohan.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/ConnectionServlet")
public class ConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ConnectionServlet() {
        super();
        
    }
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		 //define the fields
	    
	    String username="root";
	    String password="Ramesh@1994";
	    String jdbcURL="jdbc:mysql://localhost:3306/employeedirectory";
	    String driver="com.mysql.jdbc.Driver";
	    
	    
	   try {
		   //get the printwriter object
		    
		    PrintWriter writer=response.getWriter();
		    writer.println("connecting to database"+jdbcURL);
		    
		    
		    //load the driver
				Class.forName(driver);
			
		   
		    //get the connection
			Connection connection=DriverManager.getConnection(jdbcURL,username, password);
			writer.println("Conection successful");
		   
		    //close the connection
			connection.close();
	   }
	   catch(Exception e) {
		   e.printStackTrace();
	   }
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
