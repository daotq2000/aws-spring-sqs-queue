package com.aws.handson.iaac.dto;


import java.util.Objects;

import com.aws.handson.iaac.model.Product;


public class OrderItemData {
	
	private Long itemId;
	private Product product;
	private int quantity;
	private double itemTotalPrice;
	
	public OrderItemData() {
		super();
		
	}

	public OrderItemData(Long itemId, Product product, int quantity) {
		super();
		this.itemId = itemId;
		this.product = product;
		this.quantity = quantity;
		this.itemTotalPrice = product.getUnitPrice() * quantity;
	}

	public OrderItemData(Product product, int quantity) {
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
		return itemTotalPrice;
	}

	public void setItemTotalPrice(double itemTotalPrice) {
		this.itemTotalPrice = itemTotalPrice;
	}

	@Override
	public String toString() {
		return "OrderItemData [itemId=" + itemId + ", product=" + product + ", quantity=" + quantity
				+ ", itemTotalPrice=" + this.getItemTotalPrice() + "]";
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
		OrderItemData other = (OrderItemData) obj;
		return Objects.equals(itemId, other.itemId);
	}
	
	
	
	
}
