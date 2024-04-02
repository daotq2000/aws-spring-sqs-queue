package com.aws.handson.iaac.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aws.handson.iaac.entity.Orders;




	@Repository
	public interface IOrderRepository extends JpaRepository<Orders, Long> {
		
	}

