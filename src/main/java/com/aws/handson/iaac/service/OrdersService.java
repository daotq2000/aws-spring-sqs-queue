package com.aws.handson.iaac.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;

import com.amazonaws.services.sqs.model.Message;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.aws.handson.iaac.dao.IOrderItemRepository;
import com.aws.handson.iaac.dao.IOrderRepository;
import com.aws.handson.iaac.dto.OrdersData;
import com.aws.handson.iaac.entity.Orders;


@Service
@AllArgsConstructor
@Slf4j
public class OrdersService implements IOrderService {

    private final IOrderRepository ordersRepository;
    private final IOrderItemRepository orderItemRepository;
    private final MessageQueueService messageQueueService;

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
        Orders order = getOrdersEntity(ordersData);
        return getOrdersData(ordersRepository.save(order));
    }

    @Override
    public boolean delete(Long id) {
        boolean test = findAll().remove(findById(id));
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

            return getOrdersData(orders);
        } else {
            return null;
        }
    }


    @Override
    public String processOrderToSQS(OrdersData ordersData) {
        Orders order = getOrdersEntity(ordersData);
        return messageQueueService.produceMessageToSQS(new Gson().toJson(order));
    }

    @Override
    public void pullOrderFromQSQ() {
		var messageSQS = messageQueueService.consumeMessageFromSQS();
        List<Orders> ordersEntities = new ArrayList<>();
        for (Message messageSQ : messageSQS) {
            var orderItem = new Gson().fromJson(messageSQ.getBody(),Orders.class);
            log.info("received message from SQS with Id={} and payload:{}",messageSQ.getMessageId(),new Gson().toJson(orderItem));
            ordersEntities.add(orderItem);
        }
        ordersRepository.saveAll(ordersEntities);
    }
}
