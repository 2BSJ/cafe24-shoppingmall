package com.cafe24.pjshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.pjshop.repository.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public Boolean checkId(String id) {
		
		if("testId1".equals(id))
			return true;
		else
			return false;
	}

}
