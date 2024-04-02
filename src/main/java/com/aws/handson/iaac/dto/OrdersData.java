package com.aws.handson.iaac.dto;


import java.util.Date;
import java.util.List;

import com.aws.handson.iaac.entity.Customer;
import com.aws.handson.iaac.entity.OrderItem;



public class OrdersData {
	
	 private Long orderId;
	 private Date date;
	 private Customer customer;
	 private List<OrderItem> orderItem;
	 private double finalPrice;
	 
	 public OrdersData() {
		super();
		
	 }
	 
	 private int calculateTotalOrderPrice(List<OrderItem> orderItem) {
		 int total = 0;
		  for (final OrderItem item : orderItem) {
		 total += item.getItemTotalPrice() + item.getQuantity();
		 }
		  return total;
	}

	public OrdersData(Date date, Customer customer, List<OrderItem> orderItem, double finalPrice) {
		super();
		this.date = date;
		this.customer = customer;
		this.orderItem = orderItem;
		this.finalPrice = this.calculateTotalOrderPrice(orderItem);
	}
	
	

	public OrdersData(Long orderId, Date date, Customer customer, List<OrderItem> orderItem, double finalPrice) {
		super();
		this.orderId = orderId;
		this.date = date;
		this.customer = customer;
		this.orderItem = orderItem;
		this.finalPrice = this.calculateTotalOrderPrice(orderItem);
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}

	public double getFinalPrice() {
		this.finalPrice = this.calculateTotalOrderPrice(this.orderItem);
		return finalPrice;
	}


	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	@Override
	public String toString() {
		return "OrdersData [orderId=" + orderId + ", date=" + date + ", customer=" + customer + ", orderItem="
				+ orderItem + ", finalPrice=" + finalPrice + "]";
	}
	   
	 
	
}
