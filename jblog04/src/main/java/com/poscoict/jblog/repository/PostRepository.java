package com.poscoict.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.CategoryVo;
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

	public List<PostVo> getPostByCategoryNo(Long no) {
		return sqlSession.selectList("post.getPostByCategoryNo", no);
	}

	public PostVo getPostOneByNo(Long no) {
		return sqlSession.selectOne("post.getPostOneByNo", no);
	}

	public PostVo getPostByNo(Long postNo) {
		return sqlSession.selectOne("post.getPostByNo", postNo);
	}

}
