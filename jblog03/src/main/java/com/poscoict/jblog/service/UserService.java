package com.poscoict.jblog.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.repository.CategoryRepository;
import com.poscoict.jblog.repository.UserRepository;
import com.poscoict.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void join(UserVo vo) {
		
		// 유저 새로 만들기
		userRepository.insert(vo);
		
		// default 블로그 만들기
		blogRepository.insert(vo);
		
		// 카테고리 만들어 놓기 (기본)
		categoryRepository.insert(vo);
	}

	public UserVo login(UserVo vo) {
		
		return userRepository.login(vo);
		
	}

}
