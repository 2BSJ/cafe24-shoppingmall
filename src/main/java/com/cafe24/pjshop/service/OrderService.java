package com.cafe24.pjshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.pjshop.repository.OrderDao;
import com.cafe24.pjshop.vo.OrderDetailVo;
import com.cafe24.pjshop.vo.OrderVo;

@Service
public class OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	public int addOrder(OrderVo orderVo) {
		
		int status = orderDao.addOrder(orderVo);
		if(status != 1) {
			return 400;
			}
		else {
			orderDao.reduceStock(orderVo);
			orderDao.deleteCart(orderVo);
			return orderDao.addOrderDetail(orderVo);
		}
		
	}

	public List<OrderVo> getList(Long userNo) {
		List<OrderVo> orderList = orderDao.getList(userNo);
		for(OrderVo orderVo: orderList) {
			orderVo.setKey(null);
		}
		return orderList;
	}

	public List<OrderDetailVo> getDetailList(Long orderNo) {
		return orderDao.getDetailList(orderNo);
	}

	public int modifyOrder(OrderVo orderVo) {

		return orderDao.modifyOrder(orderVo);
	}

}
