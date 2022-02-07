package com.poscoict.jblog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.CategoryRepository;
import com.poscoict.jblog.repository.PostRepository;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private PostRepository postRepository;

	public void delete(Long categoryNo) {
		categoryRepository.delete(categoryNo);
		
	}

	public List<CategoryVo> getCategoryById(String id) {
		return categoryRepository.getCategoryById(id);
	}

	public boolean insertNew(CategoryVo categoryVo) {
		return categoryRepository.insertNew(categoryVo);
		
	}

	public List<Long> getCategoryCnt(String id) {
		List<CategoryVo> categoryList = categoryRepository.getCategoryById(id);
		List<Long> categoryCntList = new ArrayList<Long>();
		
		for(CategoryVo category : categoryList) {
			Long categoryNum = category.getNo();
			categoryCntList.add(postRepository.getCategoryCnt(categoryNum));
		}
		
		return categoryCntList;
	}

	public List<PostVo> getPostByCategoryNo(Long no) {
		
		return postRepository.getPostByCategoryNo(no);
	}

	public CategoryVo getDefaultCategoryNoById(String id) {
		
		return categoryRepository.getDefaultCategoryNoById(id);
	}
	
}
