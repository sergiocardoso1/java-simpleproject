package com.srg.services;

import java.util.List;

import com.srg.domain.Category;
import com.srg.dto.CategoryDTO;

public interface CategoryService {

	public Category findId(Long id);

	public Category insert(Category obj);

	public Category update(Category obj);

	public void delete(Long id);

	public List<Category> findAll();
	
	public Category fromDTO(CategoryDTO objDTO);

}
