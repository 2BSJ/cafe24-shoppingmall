package com.cafe24.pjshop.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.pjshop.dto.JSONResult;
import com.cafe24.pjshop.service.UserService;
import com.cafe24.pjshop.vo.ProductVo;
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
	public JSONResult join(@ModelAttribute @Valid UserVo userVo) {
		//@valid이외 정규식 필요한 UserVo
		boolean result = userService.join(userVo);
		return JSONResult.success(result+", redirect /api/user/joinsuccess");
		
	}
	
	@ApiOperation(value="회원 로그인", notes ="로그인 API")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JSONResult login(
			@RequestParam(value="id",required=true) String id,
			@RequestParam(value="password",required=true) String password,
			HttpSession session){
		//@valid이외 정규식 필요한 UserVo
		
		UserVo authUserVo = userService.login(id,password);
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("userVo", authUserVo);
		
		if(authUserVo != null) {
			session.setAttribute("authUser", authUserVo);
			data.put("message","loginSuccess");
			return JSONResult.success(data);
		}
		
		else {
			return JSONResult.fail("loginFail");
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
	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	public JSONResult addcart(@RequestParam(value="no",required=true) Long no) {
		
		boolean result = userService.addcart(no);
		if(result) {
			return JSONResult.success("addcart success");
		}
		else {
			return JSONResult.fail("addcart fail");
		}
		
	}
	
	@ApiOperation(value="장바구니삭제", notes ="장바구니삭제 API")
	@RequestMapping(value = "/cart", method = RequestMethod.DELETE)
	public JSONResult deletecart(@RequestParam(value="no",required=true) Long no) {
		
		boolean result = userService.deletecart(no);
		if(result) {
			return JSONResult.success("deletecart success");
		}
		else {
			return JSONResult.fail("deletecart fail");
		}
		
	}
	
	
	
	
	
	
	
	
}
