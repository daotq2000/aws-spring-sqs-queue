package com.aws.handson.iaac.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aws.handson.iaac.dao.IProductRepository;
import com.aws.handson.iaac.dto.ProductData;
import com.aws.handson.iaac.model.Product;





@Service
public class ProductServices implements IProductServices {
	
	@Autowired
    private IProductRepository productRepository;

	private Product getProductEntity(ProductData productData) {
        Product product = new Product();
        product.setProductId(productData.getProductId());
        product.setProduct(productData.getProduct());
        product.setProductImage(productData.getProductImage());
        product.setUnitPrice(productData.getUnitPrice());
        product.setProductQty(productData.getProductQty());
        product.setProductDescription(productData.getProductDescription());
        return product;
    }

 
    private ProductData getProductData(Product product) {
        ProductData productData = new ProductData();
        productData.setProductId(product.getProductId());
        productData.setProduct(product.getProduct());
        productData.setProductImage(product.getProductImage());
        productData.setUnitPrice(product.getUnitPrice());
        productData.setProductQty(product.getProductQty());
        productData.setProductDescription(product.getProductDescription());
        return productData;
    }


	@Override
	public List<ProductData> findAll() {
		List<ProductData> productDataList=new ArrayList<>();
        List<Product> products=productRepository.findAll();
        products.forEach(product->{productDataList.add(getProductData(product));});
        return productDataList;
	}




	@Override
	public ProductData findById(Long id) {
		Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional == null) {
            new EntityNotFoundException("Product Not Found");
        }
        return getProductData(productOptional.get());
	}




	@Override
	public ProductData create(ProductData productData) {
		Product product=getProductEntity(productData);
        return getProductData(productRepository.save(product));
	}




	@Override
	public boolean delete(Long id) {
		boolean test=findAll().remove(findById(id));
        productRepository.deleteById(id);        
        return test;
	}
	


	  public ProductData update( Long productId,ProductData productData) {
	        Product product=productRepository.findById(productId).get();
	        if(product!=null) {
	            product.setProduct(productData.getProduct());
	            product.setProductImage(productData.getProductImage());
	            product.setUnitPrice(productData.getUnitPrice());
	            product.setProductQty(productData.getProductQty());
	            product.setProductDescription(productData.getProductDescription());

	 

	            productRepository.save(product);

	 

	            return  getProductData(product);
	        }
	        else {
	            return null;
	        }

	 

	 

	    }


	
	
}

