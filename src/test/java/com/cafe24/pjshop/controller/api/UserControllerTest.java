package com.cafe24.pjshop.controller.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.core.IsNull;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.pjshop.config.AppConfig;
import com.cafe24.pjshop.config.TestWebConfig;
import com.cafe24.pjshop.dto.JSONResult;
import com.cafe24.pjshop.vo.UserVo;
import com.google.gson.Gson;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class,TestWebConfig.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Rollback(value = true)
@Transactional
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
	
	public void insertData() throws Exception{
		
		mockMvc
		.perform(post("/api/user/join")
		.param("name", "양승준")
		.param("id","test1")
		.param("password", "test123!!")
		.param("phoneNumber", "010-1111-2222")
		.param("age", "28")
		.param("address", "경기도 남양주시 호평동")
		.param("email","yyg0825@naver.com")
		.param("gender", "male")
		.param("role", "ROLE_USER")
		.contentType(MediaType.APPLICATION_JSON));
	}

	//중복확인 테스트
	@Test
	public void testACheckid() throws Exception{
		
		//DB 데이터 삽입
		insertData();
		ResultActions resultActions;
		//=====================아이디가 중복체크 된 case==================
		
		resultActions = 
		mockMvc
		.perform(get("/api/user/checkid")
		.param("id", "test1") //test1 == 생성되어있는 아이디
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
		
		//=====================중복은 아니지만 valid에 걸린 case==================
		
		resultActions = 
		mockMvc.perform(get("/api/user/checkid")
		.param("id", "1test2")
		.contentType(MediaType.APPLICATION_JSON));
				
		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")))
		.andDo(print());
		
		
		resultActions = 
		mockMvc.perform(get("/api/user/checkid")
		.param("id", "te2")
		.contentType(MediaType.APPLICATION_JSON));
				
		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")))
		.andDo(print());
		
		//=====================사용가능 case==================
		
		resultActions = 
		mockMvc.perform(get("/api/user/checkid")
		.param("id", "test2")
		.contentType(MediaType.APPLICATION_JSON));
				
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")))
		.andDo(print());
	}
	
	@Test
	public void testBJoin() throws Exception{
		
		insertData();
		ResultActions resultActions;
		
		//1. 기본 join 가능 Data
		resultActions =
		mockMvc
		.perform(post("/api/user/join")
		.param("name", "신현준")
		.param("id","test2")
		.param("password", "test123!!!")
		.param("phoneNumber", "010-3330-8969")
		.param("age", "28")
		.param("address", "경기도 남양주시 호평동")
		.param("email","burgom92@naver.com")
		.param("gender", "male")
		.param("role", "ROLE_USER")		
		.contentType(MediaType.APPLICATION_JSON));
	
		resultActions
		.andDo(print())
		.andExpect(status().isOk());
		
		//2. id valid 걸리는 data
		resultActions =
		mockMvc
		.perform(post("/api/user/join")
		.param("name", "신현준")
		.param("id","3test2")
		.param("password", "test123!!!")
		.param("phoneNumber", "010-3330-8969")
		.param("age", "28")
		.param("address", "경기도 남양주시 호평동")
		.param("email","burgom92@naver.com")
		.param("gender", "male")
		.param("role", "ROLE_USER")		
		.contentType(MediaType.APPLICATION_JSON));
	
		resultActions
		.andDo(print())
		.andExpect(status().isBadRequest());	
		
		//3. password valid 걸리는 data
		resultActions =
		mockMvc
		.perform(post("/api/user/join")
		.param("name", "신현준")
		.param("id","test2")
		.param("password", "test123")
		.param("phoneNumber", "010-3330-8969")
		.param("age", "28")
		.param("address", "경기도 남양주시 호평동")
		.param("email","burgom92@naver.com")
		.param("gender", "male")
		.param("role", "ROLE_USER")		
		.contentType(MediaType.APPLICATION_JSON));
	
		resultActions
		.andDo(print())
		.andExpect(status().isBadRequest());
		
		//4. email valid 걸리는 data
		resultActions =
		mockMvc
		.perform(post("/api/user/join")
		.param("name", "신현준")
		.param("id","test2")
		.param("password", "test123!!")
		.param("phoneNumber", "010-3330-8969")
		.param("age", "28")
		.param("address", "경기도 남양주시 호평동")
		.param("email","1burgom92@naver.com")
		.param("gender", "male")
		.param("role", "ROLE_USER")		
		.contentType(MediaType.APPLICATION_JSON));
	
		resultActions
		.andDo(print())
		.andExpect(status().isBadRequest());
	}
	
	// 로그인 test
	@Test
	public void testCLogin() throws Exception{
		insertData();
		ResultActions resultActions;
		UserVo userVo = new UserVo();		
		
		//1. 로그인 성공 케이스==================================
		userVo.setId("test1");
		userVo.setPassword("test123!!");
		
		resultActions =
		mockMvc
		.perform(get("/api/user/login")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(userVo)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.id", is("test1")))
		.andExpect(jsonPath("$.data.gender", is("male")));
		
		//2. 로그인 실패 케이스=================================
		userVo.setId("test1");
		userVo.setPassword("test123");
		
		resultActions =
		mockMvc
		.perform(get("/api/user/login")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(userVo)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
		
	}
	// 로그아웃 test
	
	// 회원정보수정
	
	// 카트담기
	
	// 카트삭제
	
	
	
}
