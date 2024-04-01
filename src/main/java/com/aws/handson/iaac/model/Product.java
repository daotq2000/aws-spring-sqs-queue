package com.aws.handson.iaac.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "Product")
@Component
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;
	private String product;
	private String productImage;
	protected double unitPrice;
	private int productQty;
	private String productDescription;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(Long productId, String product, String productImage, double unitPrice, int productQty,
			String productDescription) {
		super();
		this.productId = productId;
		this.product = product;
		this.productImage = productImage;
		this.unitPrice = unitPrice;
		this.productQty = productQty;
		this.productDescription = productDescription;
	}
	public Product(String product, String productImage, double unitPrice, int productQty, String productDescription) {
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
	@Override
	public int hashCode() {
		return Objects.hash(productId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(productId, other.productId);
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", product=" + product + ", productImage=" + productImage
				+ ", unitPrice=" + unitPrice + ", productQty=" + productQty + ", productDescription="
				+ productDescription + "]";
	}
	
	
}

