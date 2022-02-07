package com.poscoict.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.PostVo;

@Repository
public class PostRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public void insert(PostVo postVo) {
		sqlSession.insert("post.insert", postVo);
		
	}
	
	public Long getCategoryCnt(Long no) {
		return sqlSession.selectOne("post.getCategoryCnt", no);
	}

}