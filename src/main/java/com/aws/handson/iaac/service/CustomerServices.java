package com.aws.handson.iaac.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aws.handson.iaac.dao.ICustomerRepository;
import com.aws.handson.iaac.dto.CustomerData;
import com.aws.handson.iaac.model.Customer;




@Service
public class CustomerServices implements ICustomerService{

	@Autowired
	private ICustomerRepository customerRepository;
	
	private Customer getCustomerEntity(CustomerData customerData) {
		Customer customer = new Customer();
		
		customer.setCustId(customerData.getCustId());
		customer.setCustomerName(customerData.getCustomerName());
		customer.setAddress(customerData.getAddress());
		customer.setPhone(customerData.getPhone());
		customer.setEmail(customerData.getEmail());
		return customer;
	}
	
	private CustomerData getCustomerData(Customer customer) {
		CustomerData customerData = new CustomerData();
		customerData.setCustId(customer.getCustId());
		customerData.setCustomerName(customer.getCustomerName());
		customerData.setAddress(customer.getAddress());
		customerData.setPhone(customer.getPhone());
		customerData.setEmail(customer.getEmail());
		return customerData;
	}
	
	@Override
	public List<CustomerData> findAll(){
		List<CustomerData> customerDataList = new ArrayList<>();
		List<Customer> customers = customerRepository.findAll();
		customers.forEach(customer -> {
			customerDataList.add(getCustomerData(customer));
		});
		return customerDataList;
	}

	@Override
	public CustomerData findById(Long id) {
		Optional<Customer> customerOptional = customerRepository.findById(id);
		if(customerOptional == null) {
			new EntityNotFoundException("Customer Not Found");
		}
		return getCustomerData(customerOptional.get());
	}

	@Override
	public CustomerData create(CustomerData customerData) {
		Customer customer = getCustomerEntity(customerData);
		return getCustomerData(customerRepository.save(customer));
	}
	
	@Override
	public boolean delete(Long id) {
		boolean test = true;
		if(findAll().remove(findById(id))) {
			test = false;
		}
		customerRepository.deleteById(id);
		return test;
	}
	
	@Override
	public CustomerData update(Long id, CustomerData customerData) {
		String name = customerData.getCustomerName();
		String address = customerData.getAddress();
		String phone = customerData.getPhone();
		String email =  customerData.getEmail();
		customerData = findById(id);
		customerData.setCustomerName(name);
		customerData.setAddress(address);
		customerData.setPhone(phone);
		customerData.setEmail(email);
		Customer customer = getCustomerEntity(customerData);
		customer.setCustomerName(customerData.getCustomerName());
		customer.setAddress(customerData.getAddress());
		customer.setPhone(customerData.getPhone());
		customer.setEmail(customerData.getEmail());
		return getCustomerData(customerRepository.save(customer));
	}
	
}