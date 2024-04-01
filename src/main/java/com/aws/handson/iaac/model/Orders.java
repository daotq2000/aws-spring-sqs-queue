package com.aws.handson.iaac.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;
	
	private Date date;
	
	@OneToOne
	private Customer customer;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<OrderItem> orderItem;
	
	@Transient
	private double finalPrice;

	public Orders() {
		super();
		
	}
	
	private int calculateTotalOrderPrice(List<OrderItem> orderItem) {
		 int total = 0;
		  for (final OrderItem item : orderItem) {
		 total += item.getItemTotalPrice() + item.getQuantity();
		 }
		  return total;
	}
	
	public Orders(Long orderId, Date date, Customer customer, List<OrderItem> orderItem, double finalPrice) {
        super();
		this.orderId = orderId;
		this.date = date;
		this.customer = customer;
	    this.orderItem = orderItem;
		this.finalPrice = this.calculateTotalOrderPrice(orderItem);
     }
	
	
	public Orders(Date date, Customer customer, List<OrderItem> orderItem, double finalPrice) {
		super();
		this.date = date;
		this.customer = customer;
		this.orderItem = orderItem;
		this.finalPrice = this.calculateTotalOrderPrice(orderItem);;
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
		return finalPrice;
	}
	
	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}
	
	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", date=" + date + ", customer=" + customer + ", orderItem=" + orderItem
				+ ", finalPrice=" + finalPrice + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(orderId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		return Objects.equals(orderId, other.orderId);
	}
	
	
	
}

