package com.aws.handson.iaac.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aws.handson.iaac.dao.IOrderItemRepository;
import com.aws.handson.iaac.dto.OrderItemData;
import com.aws.handson.iaac.model.OrderItem;




	@Service
	public class OrderItemServices implements IOrderItemServices {
		
		@Autowired
	    private IOrderItemRepository orderitemRepository;

		private OrderItem getOrderItemEntity(OrderItemData orderitemData) {
	        OrderItem orderItem = new OrderItem();
	        orderItem.setItemId(orderitemData.getItemId());
	        orderItem.setProduct(orderitemData.getProduct());
	        orderItem.setQuantity(orderitemData.getQuantity());
	        orderItem.setItemTotalPrice(orderitemData.getItemTotalPrice());
	        return orderItem ;
	    }

	 
	    private OrderItemData getOrderItemData(OrderItem orderItem) {
	        OrderItemData orderItemData= new OrderItemData();
	        orderItemData.setItemId(orderItem.getItemId());
	        orderItemData.setProduct(orderItem.getProduct());
	        orderItemData.setQuantity(orderItem.getQuantity());
	        orderItemData.setItemTotalPrice(orderItem.getItemTotalPrice());
	        return orderItemData;
	    }

	
		
		@Override
		public List<OrderItemData> findAll() {
			List<OrderItemData> orderItemDataList=new ArrayList<>();
	        List<OrderItem> orderitems=orderitemRepository.findAll();
	        orderitems.forEach(orderitem->{orderItemDataList.add(getOrderItemData(orderitem));});
	        return orderItemDataList;
		}
		
		

		@Override
		public OrderItemData findById(Long id) {
			Optional<OrderItem> orderitemOptional = orderitemRepository.findById(id);
	        if (orderitemOptional == null) {
	            new EntityNotFoundException("Item Not Found");
	        }
	        return getOrderItemData(orderitemOptional.get());
		}




		@Override
		public OrderItemData create(OrderItemData orderItemData) {
			OrderItem orderItem=getOrderItemEntity(orderItemData);
	        return getOrderItemData(orderitemRepository.save(orderItem));
		}




		@Override
		public boolean delete(Long id) {
			boolean test=findAll().remove(findById(id));
	        orderitemRepository.deleteById(id);        
	        return test;
		}
		

		@Override
	    public OrderItemData update( Long itemId,OrderItemData orderItemData) {
		      OrderItem orderItem=orderitemRepository.findById(itemId).get();
		        if(orderItem!=null) {
		        	orderItem.setProduct(orderItemData.getProduct());
		            orderItem.setQuantity(orderItemData.getQuantity());
		            orderItem.setItemTotalPrice(orderItemData.getItemTotalPrice());
		            orderitemRepository.save(orderItem);

		 

		            return  getOrderItemData(orderItem);
		        }
		        else {
		            return null;
		        }
	 

		    }


		
		
	}



