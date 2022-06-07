package com.srg.services;

import java.util.List;

import com.srg.domain.Product;
import com.srg.dto.ProductDTO;

public interface ProductService{
	
	public Product findId(Long id);

	public Product insert(Product obj);
	
	public Product update(Product obj);
	
	public void delete(Long id);
	
	public List<Product> findAll();
	
	public Product fromDTO(ProductDTO objDTO);
}
