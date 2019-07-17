package com.cafe24.pjshop.controller.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.pjshop.config.AppConfig;
import com.cafe24.pjshop.config.TestWebConfig;
import com.cafe24.pjshop.vo.UserVo;
import com.google.gson.Gson;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class,TestWebConfig.class})
@WebAppConfiguration

public class UserControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before	
	public void setup() {
		mockMvc = MockMvcBuilders.
			webAppContextSetup(webApplicationContext).
			build();
		
		//테스트 데이터베이스의 테이블들 초기화시켜주고 값 세팅시키기.
	}
	
	@Test
	public void AtestCheckid() throws Exception{
		
		//=====================아이디가 중복체크 된 case==================
		ResultActions resultActions;
		resultActions = 
		mockMvc.perform(get("/api/user/checkid")
		.param("id", "test_can_not_use_id") //testid == 있는아이디
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
		
		//=====================실패case==================
		
		resultActions = 
		mockMvc.perform(get("/api/user/checkid")
		.param("id", "test_can_use_id")
		.contentType(MediaType.APPLICATION_JSON));
				
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andDo(print());
		
	}
	
	@Test
	public void BtestJoin() throws Exception{
		UserVo userVo = new UserVo();
		userVo.setName("양승준");
		userVo.setId("ysj825");
		userVo.setEmail("yyg0825@naver.com");
		userVo.setPassword( "1234##5a6" );
		userVo.setPhoneNumber("010-1111-2222");
		
		ResultActions resultActions =
		mockMvc
			.perform(post("/api/user/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(userVo)));
	
		resultActions
		.andDo(print())
		.andExpect(status().isOk());
		
		//1. Normal User's join data
		
	}
	
	
	
}
