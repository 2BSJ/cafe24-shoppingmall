package com.cafe24.pjshop.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.pjshop.dto.JSONResult;
import com.cafe24.pjshop.service.UserService;
import com.cafe24.pjshop.vo.UserVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RequestMapping("/api/user")
@RestController("userAPIController") //유저컨트롤러로 두개의 클래스가 만들어져 컨테이너안에서 id값이 중복되기떄문에
@Api(value = "UserController", description ="유저 컨트롤러")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	
	@ApiOperation(value="아이디 중복체크", notes ="아이디 중복 체크 API")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> checkid(
			@PathVariable(value="id") String id) {
		
		Validator validator = 
				Validation.buildDefaultValidatorFactory().getValidator();
		
		Set<ConstraintViolation<UserVo>> validatorResults = 
				validator.validateProperty(new UserVo(id), "id"); // 어떤 validate를 만들지 모르니까 스캔을 하게 만들어줌
		System.out.println("");
		if (validatorResults.isEmpty() == false) {
			for (ConstraintViolation<UserVo> validatorResult : validatorResults) {
				return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail(validatorResult.getMessage()));
			}
		}
		int countId = userService.checkId(id);
		
		if(countId == 1) {	
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("아이디가 존재합니다."));
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));	
		}
			
	}
	
	@ApiOperation(value="회원가입", notes ="회원가입 API")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> join(@RequestBody @Valid UserVo userVo,BindingResult bindingResult) {

		if( bindingResult.hasErrors()) {
			List<ObjectError> list = bindingResult.getAllErrors();
			for(ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		int result = userService.join(userVo);
		if(result == 1)
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("회원가입 실패"));

	}
	
	@ApiOperation(value="회원 로그인", notes ="로그인 API")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> login(
			@RequestBody UserVo userVo){

		UserVo authUserVo;

		if((authUserVo=userService.login(userVo)) != null) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(authUserVo));
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("login실패!"));
		}
		
	}
	
	@ApiOperation(value="회원정보수정", notes ="회원정보수정 API")
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<JSONResult> modify(
			@RequestBody UserVo userVo){
		
		//password,email,address,phoneNumber 변경가능
//		if( bindingResult.hasErrors()) {
//			List<ObjectError> list = bindingResult.getAllErrors();
//			for(ObjectError error : list) {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
//			}
//		}
		
		if(userService.modify(userVo)==1) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("수정에 실패하였습니다."));
		}
		
	}
	
	@ApiOperation(value="회원 아이디 찾기", notes ="회원아이디찾기 API")
	@RequestMapping(value = "/find/id", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> findId(
			@RequestParam(value="name", required=true) String name,
			@RequestParam(value="email", required=true) String email){
		
		UserVo userVo = new UserVo();
		userVo.setName(name);
		userVo.setEmail(email);
		String findId;
		
		if((findId = userService.findId(userVo)) != null) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(findId));
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("아이디를 찾지 못하였습니다."));
		}
		
	}
	
	@ApiOperation(value="회원 패스워드 찾기", notes ="회원 패스워드 찾기 API")
	@RequestMapping(value = "/find/password", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> findPassword(
			@RequestParam(value="id", required=true) String id,
			@RequestParam(value="email", required=true) String email,
			@RequestParam(value="phone", required=true) String phone){
		
		UserVo userVo = new UserVo();
		userVo.setId(id);
		userVo.setEmail(email);
		userVo.setPhoneNumber(phone);
		
		if(userService.findPassword(userVo) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("비밀번호를 찾지 못하였습니다."));
		}
		
	}
	

	
	
	
	
	
	
	
	
}
