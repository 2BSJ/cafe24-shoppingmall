package com.cafe24.pjshop.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.pjshop.dto.JSONResult;
import com.cafe24.pjshop.service.ProductService;
import com.cafe24.pjshop.vo.ProductVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("ProductAPIController") 
@RequestMapping("/api/product")
@Api(value = "ProductController", description ="상품 컨트롤러")

public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	
	//상품 전체 목록 조회
	@ApiOperation(value="상품 전체 목록 조회", notes ="상품 전체목록 조회API")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONResult list() {
		
		List<ProductVo> productList = productService.list();
		
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("productList",productList);
		
		if(productList != null) {	
			return JSONResult.success(data);
		}
		else {
			return JSONResult.fail("가져온 리스트가 없음");	
		}
			
	}
	
	//상품 카테고리 조회
	
	@ApiOperation(value="상품 카테고리 별 목록 조회", notes ="상품 카테고리 별 목록 조회API")
	@RequestMapping(value = "/list/{no}", method = RequestMethod.GET)
	public JSONResult list(
			@PathVariable(value="no") Long no) {
		
		List<ProductVo> productList = productService.list(no);
		
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("productList",productList);
		
		if(productList != null) {	
			return JSONResult.success(data);
		}
		else {
			//페이지에 검색상품없다고 노출
			return JSONResult.fail("가져온 리스트가 없음");	
		}
			
	}
	
	//상품 상세보기
	@ApiOperation(value="상품 상세 조회", notes ="상품상세조회API")
	@RequestMapping(value = "/detail/{no}", method = RequestMethod.GET)
	public JSONResult detail(
			@PathVariable(value="no") Long no) {
		
		ProductVo productVo = productService.detail(no);
		
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("productVo",productVo);
		
		if(productVo != null) {	
			return JSONResult.success(data);
		}
		else {
			return JSONResult.fail("가져온 상품상세정보 없음");	
		}
			
	}
	
	
	//상품 검색하기
	@ApiOperation(value="상품 검색하기", notes ="상품검색API")
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public JSONResult search(
			@RequestParam(value="name",required=true) String name) {
		
		List<ProductVo> productList = productService.search(name);
		
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("productList",productList);
		
		if(productList != null) {	
			return JSONResult.success(data);
		}
		else {
			return JSONResult.fail("가져온 상품상세정보 없음");	
		}
			
	}
	
	
}
