package com.aws.handson.iaac.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aws.handson.iaac.entity.Customer;



@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long>{

	

}
