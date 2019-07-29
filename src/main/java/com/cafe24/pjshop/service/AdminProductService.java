package com.cafe24.pjshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.pjshop.repository.ProductDao;
import com.cafe24.pjshop.vo.CategoryVo;
import com.cafe24.pjshop.vo.ProductVo;

@Service
public class AdminProductService {
	
	@Autowired
	private ProductDao productDao;

	public int addProduct(ProductVo productVo) {
		// TODO Auto-generated method stub
		return productDao.addProduct(productVo);
	}
	


}
