package com.cafe24.pjshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.pjshop.repository.UserDao;
import com.cafe24.pjshop.vo.ProductVo;
import com.cafe24.pjshop.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	//sql Session
	
	public Boolean checkId(String id) {
		
		//get
		if("test_can_not_use_id".equals(id))
			return true;
		else
			return false;
	}

	public boolean join(UserVo userVo) {
		
		//post
		return true;
	}

	public UserVo login(String id, String password) {

		UserVo authUserVo = new UserVo();
		authUserVo.setId("testid");
		authUserVo.setName("testname");
		return authUserVo;
	}

	public boolean modify(UserVo userVo) {
		//
		return true;
	}

	public boolean addcart(Long no) {
		// sqlSession.selectOne("
		// sqlSession.selectOne("user.checkreply",no);
		return true;
	}

	public boolean deletecart(Long no) {

		return true;
	}

}
