package com.cafe24.pjshop.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.pjshop.dto.JSONResult;
import com.cafe24.pjshop.service.CartService;
import com.cafe24.pjshop.vo.CartVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/api/cart")
@RestController("CartAPIController")
@Api(value = "CartController", description ="장바구니 컨트롤러")
public class CartController {

	@Autowired
	private CartService cartService;
	
	//카트
	//
	//
	/*
	 * CartVo를 담는 이유는 카트에 외래키로 user번호, 상품옵션 번호가 있어서 필요한정보를 cartvo에 담아서 올수있다.
	 */
	@ApiOperation(value="장바구니에 상품추가", notes ="장바구니에 상품추가 API")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> addProduct(
			@RequestBody CartVo cartVo
			) {	
		int result = cartService.addProduct(cartVo);
		
		if(result == 1)
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("장바구니에 상품추가를 실패했습니다."));
	}
	
	//장바구니 리스트 불러오기
	@ApiOperation(value="장바구니 리스트 불러오기", notes ="장바구니 리스트 불러오기 API")
	@RequestMapping(value = "/{no}", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> getCartList(
			@PathVariable(value="no") Long userNo) {
		
		List<CartVo> cartList = cartService.getList(userNo);
		
		if(cartList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("장바구니 리스트 불러오기가 실패했습니다."));
		}
		else
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(cartList));
		
	}
	//장바구니 모든 리스트 불러오기
	@ApiOperation(value="장바구니 모든리스트 불러오기", notes ="장바구니 모든리스트 불러오기 API")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> getAllCartList() {
		
		
		List<CartVo> cartList = cartService.getAllList();
		if(cartList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("장바구니 리스트 불러오기가 실패했습니다."));
		}
		else
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(cartList));
		
	}
	
	@ApiOperation(value="장바구니 상품 수량수정", notes ="장바구니 상품 수량 수정 API")
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<JSONResult> modifyCart(
			@RequestBody CartVo cartVo) {
		

		int status = cartService.modifyCart(cartVo);
		if(status == 1)
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
		else if(status == 400)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("잘못된 요청입니다."));
		else
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("상품수량수정에 실패하였습니다"));
	}
	
	@ApiOperation(value="장바구니 상품 삭제", notes ="장바구니 상품 삭제 API")
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.DELETE)
	public ResponseEntity<JSONResult> deleteCart(
			@PathVariable(value="no")Long no) {
		

		int status = cartService.deleteProduct(no);
		if(status == 1)
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
		else if(status == 400)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("잘못된 요청입니다."));
		else
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("상품삭제에 실패하였습니다"));
	}
	

}
