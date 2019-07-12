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
		
		if("testid".equals(id))
			return true;
		else
			return false;
	}

	public boolean join(UserVo userVo) {
		
		//
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
		// TODO Auto-generated method stub
		return true;
	}

	public boolean deletecart(Long no) {
		// TODO Auto-generated method stub
		return true;
	}

}
