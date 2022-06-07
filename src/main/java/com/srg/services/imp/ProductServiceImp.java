package com.srg.services.imp;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srg.domain.Category;
import com.srg.domain.Product;
import com.srg.dto.ProductDTO;
import com.srg.repositories.CategoryRepository;
import com.srg.repositories.ProductRepository;
import com.srg.services.ProductService;
import com.srg.services.exceptions.ObjectNotFoundException;

@Service
public class ProductServiceImp implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private CategoryServiceImp categoryService;

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
	public Product update(Product obj) {
		Product newObj = findId(obj.getId());
		updateData(newObj, obj);
		categoryRepository.save(newObj.getCategory());
		return repository.save(newObj);

	}

	public void delete(Long id) {
		findId(id);
		repository.deleteById(id);
	}

	public List<Product> findAll() {
		return repository.findAll();
	}

	@Transactional
	public Product fromDTO(ProductDTO objDTO) {
		Category category = categoryService.findId(objDTO.getCategoryId());
		Product product = new Product(null, objDTO.getName(), objDTO.getPrice(), category);
		return product;
	}

	@Transactional
	public void updateData(Product newObj, Product obj) {

		if (newObj.getCategory().getId() != obj.getCategory().getId()) {
			Category newCategory = categoryService.findId(obj.getCategory().getId());
			newObj.setCategory(newCategory);
			newObj.setName(obj.getName());
			newObj.setPrice(obj.getPrice());
		} else {
			newObj.setName(obj.getName());
			newObj.setPrice(obj.getPrice());
		}

	}
}
