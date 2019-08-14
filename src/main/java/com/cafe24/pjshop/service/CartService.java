package com.cafe24.pjshop.service;

import java.util.List;

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

	public List<CartVo> getList(Long userNo) {
		return cartDao.getList(userNo);
	}
	
	public int deleteProduct(Long no) {
		
		
		
			int result = cartDao.deleteProduct(no);
			return result;
		

	}

	public int modifyCart(CartVo cartVo) {


			int result = cartDao.modifyProduct(cartVo);
			return result;

	}

	public List<CartVo> getAllList() {

		return cartDao.getAllList();
	}

	public List<CartVo> searchCartList(String userName, String productName) {
		return cartDao.searchCartList(userName,productName);
	}
}
