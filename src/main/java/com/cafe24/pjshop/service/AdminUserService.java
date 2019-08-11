package com.cafe24.pjshop.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.pjshop.repository.UserDao;
import com.cafe24.pjshop.vo.UserVo;
@Service
public class AdminUserService {
	
	@Autowired
	private UserDao userDao;

	public List<UserVo> getAllVo() {
		return userDao.getAllVo();
	}

	public int deleteUser(Long no) {

		return userDao.deleteUser(no);
	}

	public UserVo userDetail(Long no) {
		
		return userDao.userDetail(no);
	}

	


}
