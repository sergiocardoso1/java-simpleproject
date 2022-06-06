package com.srg.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srg.domain.Category;
import com.srg.repositories.CategoryRepository;
import com.srg.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
	}
	
	@Transactional
	public List<Category> findAll(){
		List<Category> list = repository.findAll();
		return list;
	}
	
	public Category insert(Category obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Category update(Long id, Category obj) {
		Category newObj = findById(id);
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public void updateData(Category newObj, Category obj) {
		newObj.setName(obj.getName());
	}
}
