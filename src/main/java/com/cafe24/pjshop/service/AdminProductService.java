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

	public ProductVo getProductDetail(Long no) {

		if(productDao.countByNo(no)==0)
			return null;
		else {
			return productDao.getProductDetail(no);
		}
	}

	public int modifyProduct(ProductVo productVo) {

		if(productDao.countByNo(productVo.getNo())==0)
			return 400;
		
		if(productDao.modifyProduct(productVo)!=1)
			return 0;
		
		// null로 비교하는 이유는 우선 Vo에 필드가 있는지없는지를 확인하기 위함.
		// List가 있을수도있고 없을수도 있기때문에
		if(productVo.getOptionList()!=null) {
			List<OptionVo> OptionList = productVo.getOptionList();
			
			for(OptionVo OptionVo : OptionList) {
				if(productDao.modifyOption(OptionVo)!=1)
					return 0;
			}
		}
		if(productVo.getImageList()!=null) {
			List<ImageVo> imageList = productVo.getImageList();

			for(ImageVo imageVo : imageList) {
				if(productDao.modifyImage(imageVo)!=1)
					return 0;
			}
		}
		return 1;
	}

	public int deleteProduct(List<Long> productNoList) {
		
		if(!productNoList.isEmpty()) {
			productDao.deleteProduct(productNoList);
		}
		
		return 1;
	}
	


}
