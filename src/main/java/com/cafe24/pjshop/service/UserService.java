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
	
	public int checkId(String id) {
		
		return userDao.countById(id);
	}

	public int join(UserVo userVo) {
		
		return userDao.insertVo(userVo);	
	}

	public UserVo login(UserVo userVo) {

		return userDao.getByIdAndPassword(userVo);
	}

	
	public int modify(UserVo userVo) {
		return userDao.updateVo(userVo);
	}
	
	public String findId(UserVo userVo) {
		return userDao.findId(userVo);
	}
	
	public int findPassword(UserVo userVo) {
		return userDao.findPassword(userVo);
	}





}
