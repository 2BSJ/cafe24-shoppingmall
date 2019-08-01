package com.cafe24.pjshop.repository;

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

}
