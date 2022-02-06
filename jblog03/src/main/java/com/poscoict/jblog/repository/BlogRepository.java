package com.poscoict.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.UserVo;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;
	
	// 블로그 첫 생성 
	public boolean insert(UserVo vo) {
		int count = sqlSession.insert("blog.insert", vo);
		return count == 1;
	}

	public BlogVo findById(String id) {
		
		return sqlSession.selectOne("blog.findById", id);
	}

	public void update(BlogVo blogVo) {
		sqlSession.update("blog.update", blogVo);
		
	}

}
