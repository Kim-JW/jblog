package com.poscoict.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.UserVo;

@Repository
public class CategoryRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(UserVo vo) {
		int count = sqlSession.insert("category.insert", vo);
		return count ==1;
	}

	public void delete(Long categoryNo) {
		sqlSession.delete("category.delete", categoryNo);
		
	}

	public List<CategoryVo> getCategoryById(String id) {
		
		return sqlSession.selectList("category.getAllCategory", id);
	}

	public boolean insertNew(CategoryVo categoryVo) {
		
		int count = sqlSession.insert("category.insertNew", categoryVo);
		
		return count == 1;
		
	}

	public CategoryVo getDefaultCategoryNoById(String id) {
		return sqlSession.selectOne("category.getDefaultCategoryNoById", id);
	}

}
