package com.aws.handson.iaac.dto;

import org.springframework.stereotype.Component;



@Component
public class ProductData {

	private Long productId;
	private String product;
	private String productImage;
	protected double unitPrice;
	private int productQty;
	private String productDescription;
	public ProductData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductData(Long productId, String product, String productImage, double unitPrice, int productQty,
			String productDescription) {
		super();
		this.productId = productId;
		this.product = product;
		this.productImage = productImage;
		this.unitPrice = unitPrice;
		this.productQty = productQty;
		this.productDescription = productDescription;
	}
	public ProductData(String product, String productImage, double unitPrice, int productQty,
			String productDescription) {
		super();
		this.product = product;
		this.productImage = productImage;
		this.unitPrice = unitPrice;
		this.productQty = productQty;
		this.productDescription = productDescription;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getProductQty() {
		return productQty;
	}
	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	
	
}

