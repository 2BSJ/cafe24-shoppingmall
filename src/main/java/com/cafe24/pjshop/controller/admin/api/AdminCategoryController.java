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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.pjshop.dto.JSONResult;
import com.cafe24.pjshop.service.AdminCategoryService;
import com.cafe24.pjshop.vo.CategoryVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * 
 * 관리자 카테고리 관리 컨트롤러
 * 
 * 	1.기능
 * 		- 카테고리 리스트 불러오기
 * 			- pathVariable 변수가 있을경우 -> 상위 카테고리를 선택했을 경우 -> 선택한 카테고리의 하위카테고리 리스트 보여줌
 * 			- pathVariable 변수가 없을경우 -> 전체 상위 카테고리 리스트를 보여줌.
 * 		- 카테고리 이름 중복검사하기
 * 		- 카테고리 추가하기
 * 			- 상위 카테고리 depth가 0이아니면 하위카테고리 추가
 * 			- 하위 카테고리 depth가 0이면 상위카테고리 추가
 * 		- 카테고리 삭제하기
 * 			- 최상위 카테고리 삭제시 아래 하위 카테고리 전체 삭제
 * 		-
 * 
 */


@RequestMapping("/api/admin/category")
@RestController("AdminCategoryAPIController")
@Api(value = "AdminCategoryController", description ="관리자 카테고리 관리 컨트롤러")
public class AdminCategoryController {

	
	@Autowired
	private AdminCategoryService adminCategoryService;	
	
	/*
	 * 
	 *  카테고리가 없을때는 카테고리를 추가해주세요 view
	 */
	@ApiOperation(value="카테고리 리스트 불러오기", notes ="카테고리 리스트 불러오기 API")
	@RequestMapping(value={"/list","/list/{groupNo}"} ,method = RequestMethod.GET)
	public ResponseEntity<JSONResult> getCategoryList(
			@PathVariable Optional<Integer> groupNo) {
		List<CategoryVo> categoryList = adminCategoryService.getCategoryList(groupNo);
		
		
		if(categoryList.isEmpty()) {	
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
	
	/*
	 *  최상위 카테고리의 경우 depth는 1로 groupNo는 max +1 로 파라미터 넘겨줌
	 *  
	 *  하위 카테고리의 경우 상위카테고리의 groupNo랑 depth+1로 파라미터 넘겨줌
	 */
	@ApiOperation(value="카테고리 추가", notes ="카테고리 추가 API")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<JSONResult> addCategory(
			@RequestBody CategoryVo categoryVo) {
		int result;
		if(categoryVo.getName()==null) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("카테고리 이름 값이 null값 입니다."));
		}
		result = adminCategoryService.addCategory(categoryVo);
		
		if(result == 1){
				
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
		}
		else
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("Insert 실패!"));
	}
	/*
	 * 
	 *  카테고리 삭제 API
	 *  삭제한 카테고리가 최상위 카테고리이면 --
	 *  아래 하위 카테고리까지 모두 다 삭제
	 */
	@ApiOperation(value="카테고리 삭제", notes ="카테고리 삭제 API")
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<JSONResult> deleteCategory(
			@RequestBody CategoryVo categoryVo) {
		if(categoryVo.getNo()==0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("카테고리 no가 없습니다."));
		}
		int result = adminCategoryService.deleteCategory(categoryVo);
		
		if(result == 400){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("카테고리에 상품이 포함되어있습니다."));	
		}
		else
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
	}
		


}

