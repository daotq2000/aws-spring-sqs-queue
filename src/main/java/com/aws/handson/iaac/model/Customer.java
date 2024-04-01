package com.aws.handson.iaac.model;


import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "Customer")
@Component
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long custId;
	private String customerName;
	private String address;
	private String phone;
	private String email;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(Long custId, String customerName, String address, String phone, String email) {
		super();
		this.custId = custId;
		this.customerName = customerName;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	public Customer(String customerName, String address, String phone, String email) {
		super();
		this.customerName = customerName;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", customerName=" + customerName + ", address=" + address + ", phone="
				+ phone + ", email=" + email + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(custId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(custId, other.custId);
	}
	
}