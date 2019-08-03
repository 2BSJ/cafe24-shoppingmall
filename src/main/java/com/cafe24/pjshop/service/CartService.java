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
	
	public int deleteProduct(List<Long> productNoList) {
		
		if(productNoList.isEmpty()) {
			return 400;
		}
		else {
			int result = cartDao.deleteProduct(productNoList);
			return result;
		}

	}

	public int modifyCart(List<CartVo> cartList) {

		if(cartList.isEmpty()) {
			return 400;
		}
		else {
			int result = cartDao.modifyProduct(cartList);
			return result;
		}
	}
}
