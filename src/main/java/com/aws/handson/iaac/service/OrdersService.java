package com.aws.handson.iaac.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aws.handson.iaac.dao.IOrderItemRepository;
import com.aws.handson.iaac.dao.IOrderRepository;
import com.aws.handson.iaac.dto.OrdersData;
import com.aws.handson.iaac.model.Orders;



@Service
public class OrdersService implements IOrderService {
	
	@Autowired
	private IOrderRepository ordersRepository;
	@Autowired
     private IOrderItemRepository orderItemRepository;

//	DTO=>Entity
	private Orders getOrdersEntity(OrdersData ordersData) {
		Orders orders = new Orders();
		orders.setOrderId(ordersData.getOrderId());
		orders.setCustomer(ordersData.getCustomer());
		orders.setDate(ordersData.getDate());
		orders.setOrderItem(ordersData.getOrderItem());
		
		return orders;
	}

//	Entity=>DTO
	private OrdersData getOrdersData(Orders orders) {
		OrdersData ordersData = new OrdersData();
		ordersData.setOrderId(orders.getOrderId());
		ordersData.setCustomer(orders.getCustomer());
		ordersData.setDate(orders.getDate());
		ordersData.setOrderItem(orders.getOrderItem());

		return ordersData;
	}
	
	
	@Override
	public List<OrdersData> findAll() {
		
		List<OrdersData> ordersDataList = new ArrayList<>();
		List<Orders> orders = ordersRepository.findAll();
		orders.forEach(orderItem -> {
			ordersDataList.add(getOrdersData(orderItem));
		});
		return ordersDataList;	
	}

	@Override
	public OrdersData findById(Long id) {
		Optional<Orders> ordersOptional = ordersRepository.findById(id);
		if (ordersOptional == null) {
			new EntityNotFoundException("Orders Not Found");
		}
		return getOrdersData(ordersOptional.get());
	}

	@Override
	public OrdersData create(OrdersData ordersData) {
		Orders order=getOrdersEntity(ordersData);
		return getOrdersData(ordersRepository.save(order));
	}

	@Override
	public boolean delete(Long id) {
		boolean test=findAll().remove(findById(id));
		ordersRepository.deleteById(id);		
		return test;
	}

	@Override
	public OrdersData update(Long id, OrdersData ordersData) {
		Orders orders = ordersRepository.findById(id).get();
		if (orders != null) {
			orders.setOrderId(ordersData.getOrderId());
			orders.setDate(ordersData.getDate());
			orders.setCustomer(ordersData.getCustomer());
			orders.setOrderItem(ordersData.getOrderItem());

			ordersRepository.save(orders);
			
			return  getOrdersData(orders);
        }
        else {
            return null;
        }
	}


}
