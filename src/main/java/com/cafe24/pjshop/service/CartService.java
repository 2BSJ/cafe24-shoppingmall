package com.cafe24.pjshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.pjshop.repository.CartDao;
import com.cafe24.pjshop.vo.CartVo;

@Service
public class CartService {

	@Autowired
	private CartDao cartDao;
	
	public int addProduct(CartVo cartVo) {

		return cartDao.addProduct(cartVo);
	}

}
