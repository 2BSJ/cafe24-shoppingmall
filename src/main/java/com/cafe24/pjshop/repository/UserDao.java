package com.cafe24.pjshop.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.pjshop.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<UserVo> getAllVo(){
		return sqlSession.selectList("user.getAllVo");
	}
	
	public int countById(String id) {
		return sqlSession.selectOne("user.countById",id);
	}
	
	public int insertVo(UserVo userVo) {
		return sqlSession.insert("user.insertVo",userVo);
		
	}

	public UserVo getByIdAndPassword(UserVo userVo) {
		return sqlSession.selectOne("user.getByIdAndPassword",userVo);
	}

	public int updateVo(UserVo userVo) {
		return sqlSession.update("user.updateVo",userVo);
	}

	public String findId(UserVo userVo) {
		return sqlSession.selectOne("user.getIdByNameAndEmail",userVo);
	}

	public int findPassword(UserVo userVo) {
		return sqlSession.selectOne("user.getPasswordByIdAndEmailAndPhone",userVo);
	}

	public int deleteUser(Long no) {

		return sqlSession.update("user.deleteByNo",no);
	}

	public UserVo userDetail(Long no) {

		return sqlSession.selectOne("user.getVo",no);
	}



	
	
	
}
