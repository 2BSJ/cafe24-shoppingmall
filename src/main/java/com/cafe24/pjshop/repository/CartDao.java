package com.cafe24.pjshop.repository;

import java.util.List;

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
		// TODO Auto-generated method stub
		return sqlSession.selectList("cart.getVo");
	}

}
