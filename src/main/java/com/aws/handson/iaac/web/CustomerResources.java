package com.aws.handson.iaac.web;

import java.util.List;

import com.aws.handson.iaac.service.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aws.handson.iaac.dto.CustomerData;


@RestController
@RequestMapping("/customers")
public class CustomerResources {

	@Autowired
	private CustomerServices customerService;

	@GetMapping("/findall")
	public List<CustomerData> findAll() {
		return customerService.findAll();
	}

	@GetMapping("/findone/{id}")
	public CustomerData findById(@PathVariable Long id) {
		return customerService.findById(id);
	}

	@PostMapping("/create")
	public CustomerData create(@RequestBody CustomerData customerData) {
		return customerService.create(customerData);
	}

	@DeleteMapping("/delete/{id}")
	public boolean delete(@PathVariable Long id) {
		return customerService.delete(id);
	}
	
	@PutMapping("/update/{id}")
	public CustomerData update(@PathVariable Long id, @RequestBody CustomerData customerData) {
		return customerService.update(id, customerData);
	}
}