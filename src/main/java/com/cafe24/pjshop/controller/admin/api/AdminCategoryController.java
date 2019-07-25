package com.cafe24.pjshop.controller.admin.api;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.pjshop.dto.JSONResult;
import com.cafe24.pjshop.service.AdminCategoryService;
import com.cafe24.pjshop.vo.CategoryVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/api/admin/category")
@RestController("AdminCategoryAPIController")
@Api(value = "AdminCategoryController", description ="관리자 카테고리 관리 컨트롤러")
public class AdminCategoryController {

	
	@Autowired
	private AdminCategoryService adminCategoryService;	
	
	@ApiOperation(value="카테고리 리스트 불러오기", notes ="카테고리 리스트 불러오기 API")
	@RequestMapping(value={"","/{groupNo}"} ,method = RequestMethod.GET)
	public ResponseEntity<JSONResult> getList(
			@PathVariable Optional<Integer> groupNo) {
		List<CategoryVo> categoryList = adminCategoryService.getList(groupNo);
		
		
		if(categoryList == null) {	
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("카테고리 리스트를 가져오지 못했습니다."));
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(categoryList));	
		}
			
	}	
	
	@ApiOperation(value="카테고리 이름 중복체크", notes ="카테고리 이름 중복 체크 API")
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> checkName(
			@PathVariable(value="name") String name) {
		
		Validator validator = 
				Validation.buildDefaultValidatorFactory().getValidator();
		
		Set<ConstraintViolation<CategoryVo>> validatorResults = 
				validator.validateProperty(new CategoryVo(name), "name"); 
		
		if (validatorResults.isEmpty() == false) {
			System.out.println(name+"!!!!!!!!!!!!!!!!");
			for (ConstraintViolation<CategoryVo> validatorResult : validatorResults) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(validatorResult.getMessage()));
			}
		}
		int countName = adminCategoryService.checkName(name);
		
		if(countName == 1) {	
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("카테고리 이름이 존재합니다."));
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));	
		}
			
	}
	
	@ApiOperation(value="상위카테고리 추가", notes ="카테고리 추가 API")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> addCategory(
			@RequestBody CategoryVo categoryVo) {
		int result;
		
		if(categoryVo.getName()==null) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("카테고리 이름 값이 null값 입니다."));
		}
		result = adminCategoryService.addTopCategory(categoryVo);
		
		if(result == 1){
				
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
		}
		else
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("Insert 실패!"));
	}
			
}

