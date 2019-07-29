package com.cafe24.pjshop.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.pjshop.vo.ProductVo;

@Repository
public class ProductDao {

	
	@Autowired
	private SqlSession sqlSession;
	
	public int addProduct(ProductVo productVo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("product.insertVo",productVo);
	}

}
