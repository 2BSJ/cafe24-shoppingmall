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
	
	
	@ApiOperation(value="아이디 존재 여부", notes ="아이디체크 API, checkid=success")
	@RequestMapping(value = "/checkid", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> checkid(
			@RequestParam(value="id", required=true) String id) {
		
		System.out.println(id.length());
		Validator validator = 
				Validation.buildDefaultValidatorFactory().getValidator();
		
		Set<ConstraintViolation<UserVo>> validatorResults = 
				validator.validateProperty(new UserVo(id), "id"); // 어떤 validate를 만들지 모르니까 스캔을 하게 만들어줌
		
		if (validatorResults.isEmpty() == false) {
			for (ConstraintViolation<UserVo> validatorResult : validatorResults) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(validatorResult.getMessage()));
			}
		}
		int countId = userService.checkId(id);
		
		if(countId == 1) {	
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("아이디가 존재합니다."));
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("아이디 사용가능"));	
		}
			
	}
	
	@ApiOperation(value="회원가입", notes ="회원가입 API")
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> join(@ModelAttribute @Valid UserVo userVo,BindingResult bindingResult) {
		//@valid이외 정규식 필요한 UserVo
		if( bindingResult.hasErrors()) {
			List<ObjectError> list = bindingResult.getAllErrors();
			for(ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		int result = userService.join(userVo);
		//return JSONResult.success(result+", redirect /api/user/joinsuccess");
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("성공"));

	}
	
	@ApiOperation(value="회원 로그인", notes ="로그인 API")
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> login(
			@RequestBody UserVo userVo){
		//@valid이외 정규식 필요한 UserVo
		
		UserVo authUserVo;

		
		
		if((authUserVo=userService.login(userVo)) != null) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(authUserVo));
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("login실패!"));
		}
		

		
		
	}
	
	@ApiOperation(value="회원 로그아웃", notes ="로그아웃 API")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public JSONResult logout(HttpSession session){
		
		if(session.getAttribute("authUser") != null) {
			session.removeAttribute("authUser");
			return JSONResult.success("logout success");
		}		
		else {
			return JSONResult.fail("logoutFail");
		}
		
	}
	
	@ApiOperation(value="회원정보수정", notes ="회원정보수정 API")
	@RequestMapping(value = "/modify", method = RequestMethod.PUT)
	public JSONResult modify(@ModelAttribute @Valid UserVo userVo) {
		
		boolean result = userService.modify(userVo);
		if(result) {
			return JSONResult.success("modify success");
		}
		else {
			return JSONResult.fail("modify fail");
		}
		
	}
	
	@ApiOperation(value="장바구니담기", notes ="장바구니담기 API")
	@RequestMapping(value = "/cart/{no}", method = RequestMethod.POST)
	public JSONResult addcart(
			@PathVariable(value="no") Long no) {
		
		boolean result = userService.addcart(no);
		if(result) {
			return JSONResult.success("addcart success");
		}
		else {
			return JSONResult.fail("addcart fail");
		}
		
	}
	
	@ApiOperation(value="장바구니삭제", notes ="장바구니삭제 API")
	@RequestMapping(value = "/cart/{no}", method = RequestMethod.DELETE)
	public JSONResult deletecart(
			@PathVariable(value="no") Long no) {
		
		boolean result = userService.deletecart(no);
		if(result) {
			return JSONResult.success("deletecart success");
		}
		else {
			return JSONResult.fail("deletecart fail");
		}
		
	}
	
	
	
	
	
	
	
	
}
