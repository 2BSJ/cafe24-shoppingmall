package com.cafe24.pjshop.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.pjshop.vo.CategoryVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int addCategory(CategoryVo categoryVo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("category.insertCategory",categoryVo);
	}

	public int checkName(String name) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("category.countByName",name);
	}

	public List<CategoryVo> getList(int groupNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("category.getVoByGroupNo",groupNo);
	}

	public int deleteCategory(Long no) {
		// TODO Auto-generated method stub
		return sqlSession.delete("category.deleteCategory",no);
	}
	

}
