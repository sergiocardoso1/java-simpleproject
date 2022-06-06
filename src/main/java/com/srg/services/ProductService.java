package com.srg.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srg.domain.Category;
import com.srg.domain.Product;
import com.srg.dto.ProductDTO;
import com.srg.repositories.CategoryRepository;
import com.srg.repositories.ProductRepository;
import com.srg.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryRepository categoryRepository;


	public Product findId(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Product.class.getName()));
	}

	@Transactional
	public Product insert(Product obj) {
		obj.setId(null);
		obj = repository.save(obj);
		categoryRepository.save(obj.getCategory());
		return obj;
	}

	@Transactional
	public Product fromDTO(ProductDTO objDTO) {
		Category category = categoryService.findById(objDTO.getCategoryId());
		Product product = new Product(null,objDTO.getName(),objDTO.getPrice(),category);
		category.getListaProduct().add(product);
		return product;
	}
}
