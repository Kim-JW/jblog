package com.poscoict.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.vo.BlogVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;

	public BlogVo findById(String id) {
		
		return blogRepository.findById(id);
	}

	public void update(BlogVo blogVo) {
		blogRepository.update(blogVo);
		
	}

}
