package com.cafe24.pjshop.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.pjshop.dto.JSONResult;
import com.cafe24.pjshop.service.UserService;

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
	public JSONResult checkid(@RequestParam(value="id",required=true) String id) {
		boolean result = userService.checkId(id);
		
		if(result) {	
			return JSONResult.fail("아이디가 존재합니다");
		}
		else {
			return JSONResult.success("아이디 사용가능");	
		}
			
	}
	
	@ApiOperation(value="회원가입", notes ="회원가입 API")
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	
	public JSONResult join()
	
	
}
