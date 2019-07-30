package com.cafe24.pjshop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.pjshop.repository.ProductDao;
import com.cafe24.pjshop.vo.ImageVo;
import com.cafe24.pjshop.vo.OptionVo;
import com.cafe24.pjshop.vo.ProductVo;

@Service
public class AdminProductService {
	
	@Autowired
	private ProductDao productDao;

	public int insertProduct(ProductVo productVo) {
		/*
		 * 
		 * productVo
		 */
		//유효한 카테고리no인지 확인
		if(productDao.checkInvalidCategoryNo(productVo.getCategoryNo())!=1)
			return 400;
		
		int result = productDao.insertProduct(productVo);
		List<OptionVo> OptionList = productVo.getOptionList();
		List<ImageVo> imageList = productVo.getImageList();
		
		//상품을 등록하고 데이터베이스에 등록된 상품키로 추가 옵션,image에 상품번호외래키를 설정해준뒤 인서트
		for(OptionVo OptionVo : OptionList) {
			OptionVo.setProductNo(productVo.getNo());
			
			if(productDao.insertOption(OptionVo)!=1)
				return 0;
		}
		for(ImageVo imageVo : imageList) {
			imageVo.setProductNo(productVo.getNo());
			if(productDao.insertImage(imageVo)!=1)
				return 0;
		}
		return 1;
	}
	
	public List<ProductVo> getList(String name, Long categoryNo, String displayStatus, String salesStatus) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name",name);
		map.put("categoryNo",categoryNo);
		map.put("displayStatus",displayStatus);
		map.put("salesStatus",salesStatus);
		return productDao.getList(map);
	}
	


}
