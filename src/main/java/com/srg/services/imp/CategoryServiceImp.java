package com.srg.services.imp;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.srg.domain.Category;
import com.srg.dto.CategoryDTO;
import com.srg.repositories.CategoryRepository;
import com.srg.services.CategoryService;
import com.srg.services.exceptions.DataIntegrityException;
import com.srg.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryServiceImp implements CategoryService{

	@Autowired
	private CategoryRepository repository;
	
	public Category findId(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
	}
	
	@Transactional
	public Category insert(Category obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	
	public Category update(Category obj) {
		Category newObj = findId(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	public void delete(Long id) {
		findId(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que existe produtos!");
		}
		repository.deleteById(id);
	}
	
	@Transactional
	public List<Category> findAll(){
		List<Category> list = repository.findAll();
		return list;
	}
	
	public Category fromDTO(CategoryDTO objDTO) {
		Category obj = new Category();
		obj.setName(objDTO.getName());
		return obj;
	}
	
	public void updateData(Category newObj, Category obj) {
		newObj.setName(obj.getName());
	}
}
