package com.GB.Product.service;
import java.sql.*;

import com.GB.Product.model.*;
import com.GB.Product.util.*;



public class ProductsService {
	
Connection con = null;
	
	public ProductsService() {
		
		con = DBconnection.connecter();
	}
	
	 //A common method to connect to the DB
	
	
	public String insertProducts(Products Products)
	 {
		String query = " insert into Products(`ProductId`,`ProductName`,`ProductDescription`,`ProductType`)"
				  + " values (?,?, ?, ?)";
		  
	 String output;
		try {	
				PreparedStatement preparedStatement = con.prepareStatement(query); 
				preparedStatement.setString(1, Products.getProductId());
				preparedStatement.setString(2, Products.getProductName());
				preparedStatement.setString(3, Products.getProductDescription());
				preparedStatement.setString(4,  Products.getProductType());
				preparedStatement.execute();
				 con.close();
			  output = "Inserted successfully";
			
		} catch (SQLException e) {
		    output = "Error while inserting the Product.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	public String readProducts()
	 {
	 String output = "";
	 try
	 {
	
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr><th>Product ID</th><th>Producting Agency</th><th>Prject ID</th><th>Product Amount</th></tr>";
	 String query = "select * from Products";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
		 String ProductId = rs.getString("ProductId");
		 String ProductName = rs.getString("ProductName");
		 String ProductDescription = rs.getString("ProductDescription");
		 String ProductType = rs.getString("ProductType");

	 // Add into the html table
		 output += "<tr><td>" + ProductId + "</td>";
		 output += "<td>" + ProductName + "</td>";
		 output += "<td>" + ProductDescription + "</td>";
		 output += "<td>" + ProductType + "</td>";

	 
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the Products.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	public String updateProducts(Products Products){
	
	 String query = "UPDATE Products SET ProductName=?,ProductDescription=?,ProductType=?,FProjectId=?,Product=? WHERE ProductId=?";
	 String output = "";
	 try
	 {
	 
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 PreparedStatement preparedStatement = con.prepareStatement(query);
	 
		preparedStatement.setString(1, Products.getProductName());
		preparedStatement.setString(2, Products.getProductDescription());
		preparedStatement.setString(3, Products.getProductType());
		preparedStatement.setString(4, Products.getProductId());
		
		
		preparedStatement.execute();
		con.close();
		output = "Updated successfully";
	}
	 catch (Exception e)
	 {
	 output = "Error while updating the Products.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	public String deleteProducts(Products Products){
		String query = "delete from Products where ProductId=?";
		String output;
		
		
		try {
			
	 if (con == null){
		 return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 
	 PreparedStatement preparedStatement = con.prepareStatement(query);
	 // binding values
	 preparedStatement.setString(1,Products.getProductId() );
	 // execute the statement
	 if(preparedStatement.execute()) {
		 output = "Deleted successfully";
	 }else {
		 output = "id not found";
	 }
		
	 con.close();
	 
	 }catch (Exception e){
	 output = "Error while deleting the Products.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

	

	
	 

}

