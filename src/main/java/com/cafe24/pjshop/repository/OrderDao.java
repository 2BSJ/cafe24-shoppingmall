package com.cafe24.pjshop.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.pjshop.vo.OrderVo;

@Repository
public class OrderDao {

	@Autowired
	private SqlSession sqlSession;
	public int addOrder(OrderVo orderVo) {

		return sqlSession.insert("order.insertVo",orderVo);
	}
	public int addOrderDetail(OrderVo orderVo) {
		return sqlSession.insert("order.insertDetailVo",orderVo);
		
	}
	
	public int reduceStock(OrderVo orderVo) {
		return sqlSession.update("option.reduceStock",orderVo);
	}
	public List<OrderVo> getList(Long userNo) {
		
		return sqlSession.selectList("order.getVoByUserNo",userNo);
	}
	public OrderVo getDetailList(Long orderNo) {
		return sqlSession.selectOne("order.getVoByOrderNo",orderNo);
	}
	public int modifyOrder(OrderVo orderVo) {

		return sqlSession.update("order.modifyVo",orderVo);
	}
	public List<OrderVo> getAdminOrderList(Map<String,Object> map) {
		return sqlSession.selectList("order.getVoByOption",map);
	}
	public void deleteCart(OrderVo orderVo) {
		
		sqlSession.delete("order.deleteCart",orderVo);
	}

}
