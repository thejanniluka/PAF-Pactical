package com.GB.Product.model;

public class Products {
	private String ProductId;
	private String ProductName;
	private String ProductDescription;
	private String ProductType;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Products() {
		super();
	}
	public Products(String productId, String productName, String productDescription, String productType) {
		super();
		ProductId = productId;
		ProductName = productName;
		ProductDescription = productDescription;
		ProductType = productType;
	}
	public String getProductId() {
		return ProductId;
	}
	public void setProductId(String productId) {
		ProductId = productId;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public String getProductDescription() {
		return ProductDescription;
	}
	public void setProductDescription(String productDescription) {
		ProductDescription = productDescription;
	}
	public String getProductType() {
		return ProductType;
	}
	public void setProductType(String productType) {
		ProductType = productType;
	}
	
	
	
	
	


}
