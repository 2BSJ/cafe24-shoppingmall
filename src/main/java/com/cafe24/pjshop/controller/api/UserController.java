package com.cafe24.pjshop.controller.api;

import java.util.HashMap;
import java.util.Map;

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
	
	@ApiOperation(value = "회원가입 페이지 이동", notes = "회원가입 페이지 이동API")
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public JSONResult join_form() {
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("forward", "join/join_form");
		return JSONResult.success(dataMap);
	}
	
	@ApiOperation(value = "회원가입", notes = "회원가입 API")
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public JSONResult join() {
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("forward", "join/join_form");
		return JSONResult.success(dataMap);
	}
	
	@ApiOperation(value="아이디 존재 여부", notes ="아이디체크 API")
	@RequestMapping(value = "/checkid", method = RequestMethod.GET)
	
	public JSONResult checkid(@RequestParam(value="id",required=true) String id) {
		Boolean exist = true; //userService.existEmail(id);
		if(exist == false)
			return JSONResult.fail("아이디가 존재합니다");
		else
			return JSONResult.success(exist);	
	}
	
}
