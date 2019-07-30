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
import com.cafe24.pjshop.service.AdminProductService;
import com.cafe24.pjshop.vo.ProductVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/api/admin/product")
@RestController("AdminProductAPIController")
@Api(value = "AdminProductController", description ="관리자 상품관리 컨트롤러")
public class AdminProductController {

	@Autowired
	private AdminProductService adminProductService;	
		
	/*
	 * 
	 *  
	 */
	@ApiOperation(value="상품등록", notes ="상품등록 API")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<JSONResult> addProduct(
			@RequestBody ProductVo productVo) {
		
			//0 실패 1 성공 400 catetoryNo 잘못된요청
		int status = adminProductService.insertProduct(productVo);
		if(status == 1)
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
		else if(status == 400)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("잘못된 요청입니다."));
		else
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("상품등록에 실패하였습니다"));
				
	}
	/*
	 * 
	 *  관리자 상품 목록조회에서는 image와 option없이 간략한 상품정보들이 List로 쭈욱 보여지게됨
	 *  관리자는 들어간후 option 선택으로 자신이 원하는 셋팅으로 이름이나 판매중인상품 등을 조회할 수 있음.
	 */
	@ApiOperation(value="관리자 상품 목록 조회", notes ="관리자 상품 전체목록 조회API")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> getProductList(
			@RequestParam(required=false) String name,
			@RequestParam(required=false, defaultValue="0") Long categoryNo,
			@RequestParam(required=false) String displayStatus,
			@RequestParam(required=false) String salesStatus) {
		
		//getList 메서드에 전부다 null,0,null,null인 경우 -> 처음에 관리자 상품목록조회를 클릭해서 들어왔을때 전체 목록 조회
		//getList 메서드에 파라메타값이 존재할경우 -> 조회옵션을 설정하였을경우
		List<ProductVo> productList = adminProductService.getList(name,categoryNo,displayStatus,salesStatus);
		if(!productList.isEmpty())
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productList));
		else
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("상품목록 조회에 실패하였습니다"));
	}
	
	@ApiOperation(value="관리자 상품 상세 정보 보기", notes ="관리자 상품 상세 정보 조회API")
	@RequestMapping(value = "/{no}", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> getProductDetail(
			@PathVariable(value="no") Long no) {
		
		
		ProductVo productVo = adminProductService.getProductDetail(no);
		
		if(productVo != null)
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productVo));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("잘못된 요청으로 상품상세정보 조회에 실패하였습니다"));
	}
}
