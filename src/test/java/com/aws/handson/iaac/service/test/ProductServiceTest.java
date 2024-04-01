package com.aws.handson.iaac.service.test;

import com.aws.handson.iaac.service.ProductServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.aws.handson.iaac.dto.ProductData;

@SpringBootTest
public class ProductServiceTest {

	@Autowired
    private ProductServices productServices;

    @Autowired
    private ProductData productData;

    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    @Disabled
    @BeforeEach()
    public void beforeEach()
    {
    	productData = new ProductData("Vivo V25 Pro","https://m.media-amazon.com/images/I/31fbAkkOCXL._SX300_SY300_QL70_FMwebp_.jpg",34990,1,"64MP+8MP+2MP+32MP Front Camera,4830 mAh Lithium Battery");
    }
    
    @Disabled
    @Test
    public void testCreateProduct() {
    	Assertions.assertNotNull(productServices.create(productData));
    }

    @Disabled
     @Test
     public void testFindAllNotNull() {
         Assertions.assertNotNull(productServices.findAll());
     }

     @Disabled
     @Test
     public void testFindOneNotNull() {
         Long id=(long) 1;
         productData = productServices.findById(id);
         System.out.println("id=1 |" + productData);

         Assertions.assertNotNull(productData);
     }

     @Disabled
     @Test
         public void testFindOneAndDelete() {
         Assertions.assertFalse(productServices.delete((long) 5));
     }

     @Disabled
     @Test
     public void testFindOneAndUpdate() {
         Long id=2l;
         productData.setProduct("Apple iPhone 14 Pro 128GB 5G Deep Purple");
         productData.setProductImage("https://m.media-amazon.com/images/I/61HHS0HrjpL._AC_UY327_FMwebp_QL65_.jpg");
         productData.setUnitPrice(122999);
         productData.setProductQty(2);
         productData.setProductDescription("15.54 cm (6.1-inch) Super Retina XDR display featuring Always-On and ProMotion,48MP Main camera");
         Assertions.assertNotNull(productServices.update( id,productData));
     }
	
}


