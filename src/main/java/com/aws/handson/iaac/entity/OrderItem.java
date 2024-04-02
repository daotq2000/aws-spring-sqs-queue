package com.aws.handson.iaac.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "OrderItem")
@Component

public class OrderItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;
	
 	@OneToOne
    private Product product;
 	private int quantity;
   
    
 	@Transient
     private double itemTotalPrice;


	public OrderItem() {
		super();
		
	}


	public OrderItem(Long itemId, Product product, int quantity) {
		super();
		this.itemId = itemId;
		this.product = product;
		this.quantity = quantity;
		this.itemTotalPrice = product.getUnitPrice() * quantity;
	}


	public OrderItem(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.itemTotalPrice = product.getUnitPrice() * quantity;
	}


	public Long getItemId() {
		return itemId;
	}


	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getItemTotalPrice() {
		this.itemTotalPrice = this.product.getUnitPrice()*quantity;
		return this.itemTotalPrice;
	}


	public void setItemTotalPrice(double itemTotalPrice) {
		this.itemTotalPrice = itemTotalPrice;
	}


	@Override
	public String toString() {
		return "OrderItem [itemId=" + itemId + ", product=" + product + ", quantity=" + quantity + ", itemTotalPrice="
				+ this.getItemTotalPrice() + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(itemId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(itemId, other.itemId);
	}
	
	
	


 	
	
}