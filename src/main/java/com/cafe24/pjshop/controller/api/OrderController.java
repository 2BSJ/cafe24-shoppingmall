package com.cafe24.pjshop.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.pjshop.dto.JSONResult;
import com.cafe24.pjshop.service.OrderService;
import com.cafe24.pjshop.vo.ProductVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@RequestMapping("/api/order")
//@RestController("OrderAPIController")
//@Api(value = "OrderController", description ="주문 컨트롤러")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	//상품 주문
	//optionNo를 가져오는이유는 외래키로 상품번호와 옵션이름번호를 가지고있어서 주문한 옵션,상품정보를 다 볼 수있음.
	//OrderController를 따로 만들어야할까? 주문내역도 결국 회원번호와 이어져있고 상품주문또한 상품컨트롤러로 엮어도 될텐데.
	@ApiOperation(value="상품 주문", notes ="상품 주문API")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public JSONResult order(
			@RequestParam(value="optionNo",required=true) Long optionNo
			
			) {	
		boolean result = orderService.order(optionNo);
		if(result) {
			JSONResult.success("상품주문성공");
		}
		else
			JSONResult.fail("주문실패");
		
		
		return null;
	}
	
	//주문 내역
	@ApiOperation(value="주문 내역", notes ="주문 내역API")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONResult list(
			@RequestParam(value="no",required=true) Long userNo) {	
			
			return null;
		}
}
