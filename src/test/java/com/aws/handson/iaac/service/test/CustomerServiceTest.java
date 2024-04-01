package com.aws.handson.iaac.service.test;


import com.aws.handson.iaac.service.CustomerServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.aws.handson.iaac.dto.CustomerData;


@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	private CustomerData customerData;

	@Autowired
	private CustomerServices customerService;

	 @Disabled
    @BeforeEach
	public void beforeEach() {

		customerData.setCustomerName("Kunal Thakkekar");
		customerData.setAddress("23, Ramvilas Bunglow , Wada , Thane");
		customerData.setPhone("9812561876");
		customerData.setEmail("kunalthakkekar67@gmail.com");
	}

    @Disabled
    @Test
    public void testCreateCustomer() {
    	Assertions.assertNotNull(customerService.create(customerData));
    }

    
	@Disabled
	@Test
	public void testFindOne() {
		boolean test = false;
		if (customerService.findById(16L) != null) {
			test = true;
		}
		Assertions.assertTrue(test);
	}

	@Disabled
	@Test
	public void testFindAll() {
		boolean test = false;
		if (customerService.findAll() != null) {
			test = true;
		}
		Assertions.assertTrue(test);
	}

	@Disabled
	@Test
	public void testDelete() {
		Assertions.assertTrue(customerService.delete(12L));
	}

	 @Disabled
	@Test
	public void testUpdate() {
		customerData = new CustomerData();
		boolean test = false;
		customerData.setCustomerName("Devashish Kapadnis");
		customerData.setAddress("Raj Society Indiranagar Nashik");
		customerData.setPhone("7058204270");
		customerData.setEmail("devashishkapadnis075@gmail.com");
		if (customerService.update(1L, customerData) != null) {
			test = true;
		}
		Assertions.assertTrue(test);
	}
}


