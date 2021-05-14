package com.GB.Product.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.GB.Product.model.*;
import com.GB.Product.service.ProductsService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



@Path("/Products")
public class ProductsResource {
	
	Products ProductObj = new Products();
	// Read API
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readProducts() {
			ProductsService ProductObj = new ProductsService();

			return ProductObj.readProducts();

		}
		
		// Insert API
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertProducts(String ProductData) {
			JsonObject ProductObject = new JsonParser().parse(ProductData).getAsJsonObject();

			String ProductId = ProductObject.get("ProductId").getAsString();
			String ProductName = ProductObject.get("ProductName").getAsString();
			String ProductDescription = ProductObject.get("ProductDescription").getAsString();
			String ProductType = ProductObject.get("ProductType").getAsString();
		
			ProductsService ProductObject2 = new ProductsService();

			ProductObj.setProductId(ProductId);
			ProductObj.setProductName(ProductName);
			ProductObj.setProductDescription(ProductDescription);
			ProductObj.setProductType(ProductType);
			

			String output =ProductObject2.insertProducts(ProductObj);
			return output;
		}
		
		// Update API
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateProducts(String ProductData) {
			// Convert the input string to a JSON object
			JsonObject ProductObject = new JsonParser().parse(ProductData).getAsJsonObject();
			// Read the values from the JSON object
			String ProductId = ProductObject.get("ProductId").getAsString();
			String ProductName = ProductObject.get("ProductName").getAsString();
			String ProductDescription = ProductObject.get("ProductDescription").getAsString();
			String ProductType = ProductObject.get("ProductType").getAsString();

			ProductsService ProductObject1 = new ProductsService();

			ProductObj.setProductId(ProductId);
			ProductObj.setProductName(ProductName);
			ProductObj.setProductDescription(ProductDescription);
			ProductObj.setProductType(ProductType);

			String output = ProductObject1.updateProducts(ProductObj);
			return output;
		}
		
		// Delete API
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteProducts(String ProductData) {

			JsonObject ProductObject = new JsonParser().parse(ProductData).getAsJsonObject();

			String ProductId = ProductObject.get("ProductId").getAsString();

			ProductsService ProductObject2 = new ProductsService();
			ProductObj.setProductId(ProductId);

			String output = ProductObject2.deleteProducts(ProductObj);
			return output;
		}
	}
