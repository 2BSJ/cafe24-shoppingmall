package com.cafe24.pjshop.controller.admin.api;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.pjshop.dto.JSONResult;
import com.cafe24.pjshop.service.AdminProductService;
import com.cafe24.pjshop.vo.CategoryVo;
import com.cafe24.pjshop.vo.ProductVo;
import com.cafe24.pjshop.vo.UserVo;

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

			
			int result = adminProductService.addProduct(productVo);
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
				
		}
		
	@ApiOperation(value="상품 목록 조회", notes ="상품 전체목록 조회API")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public JSONResult list() {
		
		return null;
			
	}
}
