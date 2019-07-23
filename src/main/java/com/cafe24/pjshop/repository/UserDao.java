package com.cafe24.pjshop.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.pjshop.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	public int countById(String id) {
		
		return sqlSession.selectOne("user.countById",id);
	}
	
	public int insertVo(UserVo userVo) {
		
		return sqlSession.insert("user.insertVo",userVo);
		
	}

	public UserVo getByIdAndPassword(UserVo userVo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("user.getByIdAndPassword",userVo);
	}



	
	
	
}
