package com.poscoict.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.PostRepository;
import com.poscoict.jblog.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public void insert(PostVo postVo) {
		postRepository.insert(postVo);
	}

	public PostVo getPostOneByNo(Long no) {
		
		return postRepository.getPostOneByNo(no);
	}

	public PostVo getPostByNo(Long postNo) {
		return postRepository.getPostByNo(postNo);
	}

}
