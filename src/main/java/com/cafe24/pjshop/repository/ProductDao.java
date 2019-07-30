package com.cafe24.pjshop.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.pjshop.vo.ImageVo;
import com.cafe24.pjshop.vo.OptionVo;
import com.cafe24.pjshop.vo.ProductVo;

@Repository
public class ProductDao {

	
	@Autowired
	private SqlSession sqlSession;
	
	public int checkInvalidCategoryNo(Long categoryNo) {
		return sqlSession.selectOne("category.countByNo",categoryNo);
	}
	
	public int insertProduct(ProductVo productVo) {
		return sqlSession.insert("product.insertVo",productVo);
	}
	
	public List<ProductVo> getList(Map<String,Object> map) {
		return sqlSession.selectList("product.getListBySetting",map);
	}
	
	public int countByNo(Long no) {
		return sqlSession.selectOne("product.countByNo",no);
	}
	
	public int insertOption(OptionVo optionVo) {
		return sqlSession.insert("option.insertVo",optionVo);
	}
	
	public int insertImage(ImageVo imageVo) {
		return sqlSession.insert("image.insertVo",imageVo);
	}

	public ProductVo getProductDetail(Long no) {
		return sqlSession.selectOne("product.getProductVoByNo",no);
	}



}
