package com.aws.handson.iaac.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aws.handson.iaac.model.Product;


@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

	
	
}
