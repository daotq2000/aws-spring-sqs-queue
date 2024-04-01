package com.aws.handson.iaac.controller;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aws.handson.iaac.dto.OrdersData;
import com.aws.handson.iaac.service.OrdersService;



@RestController
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;

	@GetMapping
	public List<OrdersData> findAll() {
		return ordersService.findAll();
	}
	
	@GetMapping("/orders/{id}")
	public OrdersData findById(@PathVariable Long id) {
		return ordersService.findById(id);
	}

	@PostMapping("/order")
	public OrdersData create(@RequestBody OrdersData ordersData) {
		return ordersService.create(ordersData);
	}

	@DeleteMapping("/orders/{id}")
	public boolean delete(@PathVariable Long id) {
		return ordersService.delete(id);
	}
	
	@PutMapping("/orders/{id}")
		public OrdersData update(@PathVariable Long id, @RequestBody OrdersData ordersData) {
		return ordersService.update(id, ordersData);
	}


}
