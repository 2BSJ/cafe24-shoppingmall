package com.cafe24.pjshop.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.pjshop.dto.JSONResult;
import com.cafe24.pjshop.service.OrderService;
import com.cafe24.pjshop.service.ProductService;
import com.cafe24.pjshop.vo.ProductVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/api/product")
@RestController("ProductAPIController")
@Api(value = "ProductController", description ="상품 컨트롤러")
public class ProductController {


	/*
	 * 
	 *  --- required param ---
	 *  sales_status = "y"
	 *  display_stats = "y" 인부분 select
	 *  
	 *  --- 선택적 param ---
	 *  name 선택
	 *  special_status 선택
	 *  title_status 선택
	 *  
	 *  처음 getList -- category 상관없이 전체 List get
	 *  category 선택했을때 groupno join으로 똑같은 groupno를 가져오되
	 *  depth는 작은것들로 가져와야된다 ex) 상의 선택했으면 상의 종류를 가져오되 밑에 반팔,
	 *  긴팔도 get해서 가져오게
	 */
	
	@Autowired
	private ProductService productService;
	
	//상품 리스트
	@ApiOperation(value="유저 상품 리스트 불러오기", notes ="유저 상품 리스트 불러오기 API")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> getProductList(
			@RequestParam(required=false) String name,
			@RequestParam(required=false, defaultValue="0") Long categoryNo,
			@RequestParam(required=false) String specialStatus,
			@RequestParam(required=false) String titleStatus) {
		
		List<ProductVo> productList = productService.getList(name,categoryNo,specialStatus,titleStatus);
		if(!productList.isEmpty())
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productList));
		else
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("상품목록 조회에 실패하였습니다"));
	}
	
	@ApiOperation(value="유저 상품 상세 정보 보기", notes ="유저 상품 상세 정보 조회API")
	@RequestMapping(value = "/{no}", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> getProductDetail(
			@PathVariable(value="no") Long no) {
		
		
		ProductVo productVo = productService.getProductDetail(no);
		
		if(productVo != null)
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productVo));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("잘못된 요청으로 상품상세정보 조회에 실패하였습니다"));
	}
	
}
