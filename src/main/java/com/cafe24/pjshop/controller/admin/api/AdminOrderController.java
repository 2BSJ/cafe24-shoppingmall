package com.cafe24.pjshop.controller.admin.api;

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
import com.cafe24.pjshop.service.AdminOrderService;
import com.cafe24.pjshop.vo.OrderDetailVo;
import com.cafe24.pjshop.vo.OrderVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * 
 * 	private String csStatus; // 교환 c, 반품 rc , 환불 r , 기본 b 
	private String payStatus; // 입금전 f, 입금완료 t
	private String orderStatus; // 배송준비중  b , 배송보류 f, 배송대기 r, 배송중 p , 배송완료 t 
 * 
 */

@RequestMapping("/api/admin/order")
@RestController("AdminOrderAPIController")
@Api(value = "AdminOrderController", description ="관리자 주문관리 컨트롤러")
public class AdminOrderController {


	@Autowired
	private AdminOrderService adminOrderService;

	
	//주문 내역
	@ApiOperation(value="주문 목록 내역", notes ="주문 목록 내역API")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> getAdminOrderList(
			@RequestParam(required=false, defaultValue="0") Long memberNo,
			@RequestParam(required=false) String csStatus,
			@RequestParam(required=false) String payStatus,
			@RequestParam(required=false) String orderStatus,
			@RequestParam(required=false) String memberStatus){

		//getList 처음에 파라미터 안넘어 오는 경우 -> 관리자가 처음에 주문조회를했을경우 -> 처음 주문목록에 들어왔을때.
		//getList 메서드에 파라메타값이 존재할경우 -> 조회옵션을 설정하였을경우
		List<OrderVo> orderList = adminOrderService.getAdminOrderList(memberNo,csStatus,payStatus,orderStatus,memberStatus);
		if(orderList.isEmpty())
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("주문내역 조회에 실패하였습니다"));
		else
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(orderList));
		}		
	
	
	@ApiOperation(value="상품 상세 주문 내역", notes ="상품 상세 주문 내역API")
	@RequestMapping(value = "/{no}", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> getDetailList(
			@PathVariable(value="no") Long orderNo){
				
		List<OrderDetailVo> detailOrderList = adminOrderService.getDetailList(orderNo);
		
		if(detailOrderList != null)
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(detailOrderList));
		else
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("잘못된 요청으로 상품상세정보 조회에 실패하였습니다"));
				
	}
	
	//반품은 상품이오고나서 다시 회사로보내면 회사에서 관리자로 수정
	@ApiOperation(value="상품 주문 수정하기", notes ="상품 주문 수정하기 API")
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<JSONResult> modifyOrder(
			@RequestBody OrderVo orderVo){
		System.out.println(orderVo.getOrderStatus());
		int status = adminOrderService.modifyOrder(orderVo);
		
		if(status == 1)
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("잘못된 요청입니다."));
				
	}
}
