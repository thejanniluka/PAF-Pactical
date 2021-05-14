package com;

import java.sql.*;

public class Products 
{

			//CONNECTION
			public Connection connect()
			{
					Connection con = null;

					try
					{
							Class.forName("com.mysql.jdbc.Driver");
							con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/GB",	"root", "");
			
					}
					catch(Exception e)
					{
							e.printStackTrace();
					}

					return con;
			}
			
			
			
			
			//READ the data
			public String readProducts()
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for reading.";
							}
							
							output = "<table class='table table-dark table-striped table-hover'><tr><th>Product Name</th><th>Researcher Name</th><th>Product Description</th><th>ProductType</th>"
									+"<th>Edit</th><th>Delete</th></tr>";


							String query = "select * from products";
							Statement stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(query);

							// iterate through the rows in the result set
							while (rs.next())
							{
									String ProductId = Integer.toString(rs.getInt("ProductId"));
									String ProductName = rs.getString("ProductName");
									String ResearcherName = rs.getString("ResearcherName");
									String ProductDescription = rs.getString("ProductDescription");
									String ProductType = rs.getString("ProductType");
					


									// Add a row into the HTML table
									output += "<tr><td>" + ProductName + "</td>";
									output += "<td>" + ResearcherName + "</td>";
									output += "<td>" + ProductDescription + "</td>";
									output += "<td>" + ProductType + "</td>";
									
				

									// buttons
									output += "<td><input name='btnUpdate' type='button' value='Edit' class='btnUpdate btn btn-secondary' data-ProductId='" + ProductId + "'></td>"
											+"<td><input name='btnRemove' type='button' value='Delete' class='btnRemove btn btn-danger' data-ProductId='" + ProductId + "'>" + "</td></tr>";
							}

							con.close();

							// Complete the HTML table
							output += "</table>";
					}
					catch (Exception e)
					{
							output = "Error while reading the products.";
							System.err.println(e.getMessage());
					}
					
					return output;
			}
			
			
			
			

			//INSERT the data
			public String insertProducts(String ProductName, String ResearcherName,String ProductDescription,String ProductType)
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for inserting";
							}

							// create a prepared statement
							String query = " insert into products (`ProductId`,`ProductName`,`ResearcherName`,`ProductDescription`,`ProductType')"+ "values (?, ?, ?, ?, ? )";
							
							PreparedStatement preparedStmt = con.prepareStatement(query);

							// binding values
							preparedStmt.setInt(1, 0);
							preparedStmt.setString(2, ProductName);
							preparedStmt.setString(3, ResearcherName);
							preparedStmt.setString(4, ProductDescription);
							preparedStmt.setString(5, ProductType);
						
							
							

							//execute the statement
							preparedStmt.execute();
							con.close();

							String newUser = readProducts();
							output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
			
					}
					catch (Exception e)
					{
								output = "{\"status\":\"error\", \"data\":\"Error while inserting the User.\"}";
								System.err.println(e.getMessage());
					}
					
					return output;
			}
			

			
			//UPDATE
			public String updateProducts(String ProductId,String ProductName, String ResearcherName,String ProductDescription,String ProductType)
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for updating";
							}

							// create a prepared statement
							String query = "UPDATE products SET ProductName=?, ResearcherName=?,ProductDescription=?,ProductType=? WHERE ProductId=?";
							
							PreparedStatement preparedStmt = con.prepareStatement(query);

							// binding values
							preparedStmt.setString(1, ProductName);
							preparedStmt.setString(2, ResearcherName);
							preparedStmt.setString(3, ProductDescription);
							preparedStmt.setString(4, ProductType);
							preparedStmt.setInt(5, Integer.parseInt(ProductId));

							//execute the statement
							preparedStmt.executeUpdate();
							con.close();

							String newproducts = readProducts();
							output = "{\"status\":\"success\", \"data\": \"" + newproducts + "\"}";
			
			
					}
					catch (Exception e)
					{
							output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}";
							System.err.println(e.getMessage());
					}
					
					return output;
			}
			
			

			//DELETE
			public String deleteProducts(String ProductId)
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for deleting";
							}

							// create a prepared statement
							String query = "DELETE from products where ProductId=?";
							
							PreparedStatement preparedStmt = con.prepareStatement(query);

							// binding values
							preparedStmt.setInt(1, Integer.parseInt(ProductId));

							//execute the statement
							preparedStmt.executeUpdate();
							con.close();

							String newproducts = readProducts();
							output = "{\"status\":\"success\", \"data\": \"" + newproducts + "\"}";
					}
					catch (Exception e)
					{
						output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
						System.err.println(e.getMessage());
					}
					
					return output;
					
					
					
					
					
					
			}

	
}
