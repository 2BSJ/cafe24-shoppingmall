package com.cafe24.pjshop.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.pjshop.vo.CartVo;

@Repository
public class CartDao {

	@Autowired
	private SqlSession sqlSession;
	public int addProduct(CartVo cartVo) {
		return sqlSession.insert("cart.insertVo",cartVo);
	}
	public List<CartVo> getList(Long userNo) {
		return sqlSession.selectList("cart.getVoByUserNo",userNo);
	}
	public int deleteProduct(Long no) {
		return sqlSession.delete("cart.deleteVo",no);
		
	}
	public int modifyProduct(CartVo cartVo) {

		return sqlSession.update("cart.modifyVo",cartVo);
	}
	public List<CartVo> getAllList() {
		return sqlSession.selectList("cart.getVo");
	}
	public List<CartVo> searchCartList(String userName, String productName) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("username",userName);
		map.put("productname",productName);
		return sqlSession.selectList("cart.searchVo",map);
	}

}
