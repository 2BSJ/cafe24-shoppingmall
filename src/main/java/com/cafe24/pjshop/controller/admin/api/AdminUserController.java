package com.cafe24.pjshop.controller.admin.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.pjshop.dto.JSONResult;
import com.cafe24.pjshop.service.AdminUserService;
import com.cafe24.pjshop.vo.UserVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/api/admin/user")
@RestController("AdminUserAPIController")
@Api(value = "AdminUserController", description ="관리자 유저관리 컨트롤러")
public class AdminUserController {

	@Autowired
	private AdminUserService adminUserService;
	
	@ApiOperation(value="유저 리스트 불러오기", notes ="유저 리스트 불러오기API")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> getList() {
		List<UserVo> userList = adminUserService.getAllVo();
		if(!userList.isEmpty())
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(userList));
		else
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("유저목록 조회에 실패하였습니다"));
			
	}
	
	@ApiOperation(value="유저 삭제하기", notes ="유저 삭제하기 API")
	@RequestMapping(value = "/{no}", method = RequestMethod.DELETE)
	public ResponseEntity<JSONResult> deleteUser(
			@PathVariable(value="no") Long no) {
		int result = adminUserService.deleteUser(no);
		if(result==1)
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(true));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("유저삭제에 실패했습니다"));
			
	}
	
}
