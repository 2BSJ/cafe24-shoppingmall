package com.cafe24.pjshop.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.pjshop.dto.JSONResult;
import com.cafe24.pjshop.service.OrderService;
import com.cafe24.pjshop.vo.OrderDetailVo;
import com.cafe24.pjshop.vo.OrderVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/api/order")
@RestController("OrderAPIController")
@Api(value = "OrderController", description ="주문 컨트롤러")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	/*
	 * 
	 * 주문상세의 경우 외래키로 상품정보와 상품옵션을 참조하는데
	 * 상품을 삭제했을때 어떻게 오더의 외래키를 유지시킬지 고민했었다.
	 * 그 고민을 상품삭제를 update로 status를 바꿔주는 방식으로 함으로써 해결하게 되었다.
	 */
	
	
	//상품 주문
	//optionNo를 가져오는이유는 외래키로 상품번호와 옵션이름번호를 가지고있어서 주문한 옵션,상품정보를 다 볼 수있음.
	//장바구니에서 주문하는순간 cart에 체크한상품들이 list형태로 담겨서 orderVo로 컨트롤러로 넘어감.
	//주문과동시에 구매한 수량 만큼 상품의 재고에서 차감됨.
	@ApiOperation(value="상품 주문", notes ="상품 주문API")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> addOrder(
			@RequestBody OrderVo orderVo){

		int status = orderService.addOrder(orderVo);
		
		if(status == 1)
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
		else if(status == 400)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("잘못된 요청입니다."));
		else
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("상품주문에 실패하였습니다"));
		
	}
	
	//주문 내역
	@ApiOperation(value="상품 주문 내역", notes ="상품 주문 내역API")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> getList(
			@RequestParam Long userNo){

		if(userNo == 0L)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("잘못된 요청입니다."));
		else {
			List<OrderVo> orderList = orderService.getList(userNo);
			
			if(orderList==null)
				return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("주문내역 불러오기를 실패했습니다."));
			
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(orderList));
			
		}		
	}
	
	@ApiOperation(value="상품 상세 주문 내역", notes ="상품 상세 주문 내역API")
	@RequestMapping(value = "/{no}", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> getDetailList(
			@PathVariable(value="no") Long orderNo){
				
		List<OrderDetailVo> detailOrderList = orderService.getDetailList(orderNo);
		
		if(detailOrderList != null)
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(detailOrderList));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("잘못된 요청으로 상품상세정보 조회에 실패하였습니다"));
				
	}
	
	//환불 및 배송메시지만 수정가능
	//반품은 상품이오고나서 다시 회사로보내면 회사에서 관리자로 수정
	@ApiOperation(value="상품 주문 수정하기", notes ="상품 주문 수정하기 API")
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<JSONResult> modifyOrder(
			@RequestBody OrderVo orderVo){
				
		int status = orderService.modifyOrder(orderVo);
		
		if(status == 1)
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("잘못된 요청입니다."));
				
	}
	
}
