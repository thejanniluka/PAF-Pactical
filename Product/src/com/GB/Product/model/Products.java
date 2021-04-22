package com.GB.Product.model;

public class Products {
	private String ProductId;
	private String ProductName;
	private String ProductDescription;
	private String ProductType;
	
	public Products() {
		
	}
	
	public Products(String ProductId, String ProductName, String ProductDescription, String ProductType) {
		super();
		this.ProductId = ProductId;
		this.ProductName = ProductName;
		this.ProductDescription = ProductDescription;
		this.ProductType = ProductType;
		
	}

	public String getProductId() {
		return ProductId;
	}

	public void setProductId(String ProductId) {
		this.ProductId = ProductId;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}

	public String getProductDescription() {
		return ProductDescription;
	}

	public void setProductDescription(String ProductDescription) {
		this.ProductDescription = ProductDescription;
		
	}

	public String getProductType() {
		return ProductType;
	}

	public void setProductType(String ProductType) {
		this.ProductType = ProductType;
	}
	

	



}
