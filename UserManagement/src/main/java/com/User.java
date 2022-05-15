package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {
		//Connect to the Database
		public Connection connect() 
		{
			 Connection con = null;
			 
				 try {
					 Class.forName("com.mysql.jdbc.Driver");
					 Class.forName("com.mysql.cj.jdbc.Driver");
					 
					 //Provide the correct details: DBServer/DBName, username, password 
					 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid",
					 "root", "");
					 //For testing
					 System.out.println("Successfully connected to the database");
					 }
				 catch(Exception e){
						 System.out.println("Connection failed!");
						 e.printStackTrace();
				 }
					 return con;
		}
		
		//Insert user
		public String insertUser(String name, String type, String email, String phone, String password) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for inserting";
				}
				// create a prepared statement
				String query = " insert into user(`userId`,`userName`,`userType`,`userEmail`,`userPhone`,`userPassword`) values (?, ?, ?, ?, ?, ?)";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, name);
					preparedStmt.setString(3, type);
					preparedStmt.setString(4, email);
					preparedStmt.setString(5, phone);
					preparedStmt.setString(6, password);
					//execute the statement
					preparedStmt.execute();
					con.close();
					
					String newUser = readUser();
					output = "{\"status\":\"success\", \"data\": \"" + 
								newUser + "\"}"; 
					
			} catch (Exception e) {
				output = "{\"status\":\"error\", \"data\":\"Error while inserting the user.\"}";
				System.err.println(e.getMessage());
				}
			
		return output;
		}
		
		//Read Users
		public String readUser() {
			
			String output = "";
			try {
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for reading.";
				}
					 // Prepare the html table to be displayed
					 output = "<table border='1'>"
					 		+ "<tr>"
					 		+ "<th>UserName</th>"
					 		+ "<th>UserType</th>"
					 		+ "<th>UserEmail</th>"
					 		+ "<th>UserPhone</th>"
					 		+ "<th>UserPassword</th>"
					 		+ "<th>Update</th>"
					 		+ "<th>Remove</th></tr>";
					 
					 String query = "select * from user";
					 java.sql.Statement stmt = con.createStatement();
					 ResultSet rs = stmt.executeQuery(query);
					 
					 // iterate through the rows in the result set
					 while (rs.next()) {
						 
						 String id = Integer.toString(rs.getInt("userId"));
						 String name = rs.getString("userName");
						 String type = rs.getString("userType");
						 String email = rs.getString("userEmail");
						 String phone = rs.getString("userPhone");
						 String password = rs.getString("userPassword");
						 
						 	// Add a row into the html table
						 	output += "<tr><td><input id='hidUserIdUpdate' name='hidUserIdUpdate' type='hidden' value='" + id + "'>" 
						 					+ name + "</td>";
							output += "<td>" + type + "</td>";
							output += "<td>" + email + "</td>";
							output += "<td>" + phone + "</td>";
							output += "<td>" + password + "</td>";
							 
							 // buttons
							 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-userId='" + id + "'></td>"+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-userId='" + id +"'>" + "</td></tr>";
					}
							 con.close();
							 
							 // Complete the html table
							 output += "</table>";
							 
				} catch (Exception e) {
					output = "Error while reading the user.";
					System.err.println(e.getMessage());
				}
			
			return output;
		}
					
		// Update user 
		public String updateUser(String Id, String name, String type, String email, String phone, String password)
		{
			 String output = "";
			 	try
			 	{
				 Connection con = connect();
					 if (con == null) {
						 return "Error while connecting to the database for updating."; 
					 }
					 // create a prepared statement
					 String query = "UPDATE user SET userName=?,userType=?,userEmail=?,userPhone=?,userPassword=? WHERE userId=?";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 
					 preparedStmt.setString(1, name);
					 preparedStmt.setString(2, type);
					 preparedStmt.setString(3, email);
					 preparedStmt.setString(4, phone);
					 preparedStmt.setString(5, password);
					 preparedStmt.setInt(6, Integer.parseInt(Id));
					 				 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 
					 String newUser = readUser();
					 output = "{\"status\":\"success\", \"data\": \"" + 
					 		 newUser + "\"}"; 
					 
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}";
					System.err.println(e.getMessage());
				}
			 	
				return output;
		}
		
		
		//Delete User /////////////////////
		public String deleteUser(String userId) {
			
			String output = "";
			
			try {
				Connection con = connect();
				
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				
				String query = "delete from user where userId=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt(1, Integer.parseInt(userId));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newUser = readUser();
				output = "{\"status\":\"success\", \"data\": \"" +
							 newUser + "\"}"; 
				
			} catch (Exception e) {
				output = "{\"status\":\"error\", \"data\":"
						+ "\"Error while deleting the user.\"}";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
}
