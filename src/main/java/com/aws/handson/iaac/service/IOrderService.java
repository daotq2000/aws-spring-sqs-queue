package com.aws.handson.iaac.service;

import com.aws.handson.iaac.dto.OrdersData;

public interface IOrderService extends IService<OrdersData> {
	String processOrderToSQS(OrdersData ordersData);
    void pullOrderFromQSQ();
}



