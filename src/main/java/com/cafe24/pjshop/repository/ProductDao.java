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
	
	public List<ProductVo> getListFromAdmin(Map<String,Object> map) {
		return sqlSession.selectList("product.getListBySettingFromAdmin",map);
	}
	
	public List<ProductVo> getListFromUser(Map<String,Object> map) {
		return sqlSession.selectList("product.getListBySettingFromUser",map);
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

// modify logic
	public int modifyProduct(ProductVo productVo) {
		return sqlSession.update("product.updateVo",productVo);
	}
	
	public int modifyOption(OptionVo optionVo) {
		return sqlSession.update("option.updateVo",optionVo);
	}
	
	public int modifyImage(ImageVo imageVo) {
		return sqlSession.update("image.updateVo",imageVo);
	}

//delete 지만 update로 상품상태를 n으로 바꿔준다
	public int deleteProduct(List<Long> productNoList) {

		return sqlSession.update("product.deleteVo",productNoList);
	}
	




}
