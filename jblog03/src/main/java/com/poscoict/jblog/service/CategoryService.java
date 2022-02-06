package com.poscoict.jblog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.CategoryRepository;
import com.poscoict.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public void delete(Long categoryNo) {
		categoryRepository.delete(categoryNo);
		
	}

	public List<CategoryVo> getCategoryById(String id) {
		return categoryRepository.getCategoryById(id);
	}

	public boolean insertNew(CategoryVo categoryVo) {
		return categoryRepository.insertNew(categoryVo);
		
	}
	
}
