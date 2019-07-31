package com.cafe24.pjshop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.pjshop.repository.ProductDao;
import com.cafe24.pjshop.vo.ProductVo;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	public List<ProductVo> getList(String name, Long categoryNo, String specialStatus, String titleStatus) {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name",name);
		map.put("categoryNo",categoryNo);
		map.put("specialStatus",specialStatus);
		map.put("titleStatus",titleStatus);
		return productDao.getListFromUser(map);
	}
	
	public ProductVo getProductDetail(Long no) {

		if(productDao.countByNo(no)==0)
			return null;
		else {
			return productDao.getProductDetail(no);
		}
	}

}
