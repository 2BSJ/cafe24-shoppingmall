package com.cafe24.pjshop.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.pjshop.repository.OrderDao;
import com.cafe24.pjshop.vo.OrderDetailVo;
import com.cafe24.pjshop.vo.OrderVo;

@Service
public class AdminOrderService {
	
	@Autowired
	private OrderDao orderDao;

	public List<OrderDetailVo> getDetailList(Long orderNo) {
		return orderDao.getDetailList(orderNo);
	}

	public int modifyOrder(OrderVo orderVo) {

		return orderDao.modifyOrder(orderVo);
	}

	public List<OrderVo> getAdminOrderList(Long memberNo, String csStatus, String payStatus, String orderStatus,
			String memberStatus) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("memberNo", memberNo);
		map.put("csStatus", csStatus);
		map.put("payStatus", payStatus);
		map.put("orderStatus",orderStatus);
		
		return orderDao.getAdminOrderList(map);
	}

}
