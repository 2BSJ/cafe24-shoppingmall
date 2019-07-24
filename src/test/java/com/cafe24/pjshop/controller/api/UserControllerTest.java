package com.cafe24.pjshop.controller.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.pjshop.config.AppConfig;
import com.cafe24.pjshop.config.TestWebConfig;
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

	//중복확인 테스트
	@Test
	public void testACheckid() throws Exception{
		
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
		ResultActions resultActions;
		
		//1. 기본 join 가능 Data
		UserVo userVo1 = new UserVo();		
		userVo1.setId("test2");
		userVo1.setPassword("test123!!!");
		userVo1.setName("신현준");
		userVo1.setPhoneNumber("010-3330-8969");
		userVo1.setAge(28);
		userVo1.setAddress("경기도 남양주시 호평동");
		userVo1.setEmail("burgom92@naver.com");
		userVo1.setGender("male");
		userVo1.setRole("ROLE_USER");
		
		resultActions =
		mockMvc
		.perform(post("/api/user/join")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(userVo1)));
	
		resultActions
		.andDo(print())
		.andExpect(status().isOk());
		
		//2. id valid 걸리는 data
		UserVo userVo2 = new UserVo();		
		userVo2.setId("3test2");
		userVo2.setPassword("test123!!!");
		userVo2.setName("신현준");
		userVo2.setPhoneNumber("010-3330-8969");
		userVo2.setAge(28);
		userVo2.setAddress("경기도 남양주시 호평동");
		userVo2.setEmail("burgom92@naver.com");
		userVo2.setGender("male");
		userVo2.setRole("ROLE_USER");
		
		resultActions =
		mockMvc
		.perform(post("/api/user/join")	
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(userVo2)));
	
		resultActions
		.andDo(print())
		.andExpect(status().isBadRequest());	
		
		//3. password valid 걸리는 data
		UserVo userVo3 = new UserVo();		
		userVo3.setId("test2");
		userVo3.setPassword("test123");
		userVo3.setName("신현준");
		userVo3.setPhoneNumber("010-3330-8969");
		userVo3.setAge(28);
		userVo3.setAddress("경기도 남양주시 호평동");
		userVo3.setEmail("burgom92@naver.com");
		userVo3.setGender("male");
		userVo3.setRole("ROLE_USER");
		
		resultActions =
		mockMvc
		.perform(post("/api/user/join")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(userVo3)));
	
		resultActions
		.andDo(print())
		.andExpect(status().isBadRequest());
		
		//4. email valid 걸리는 data
		UserVo userVo4 = new UserVo();		
		userVo4.setId("test2");
		userVo4.setPassword("test123!!");
		userVo4.setName("신현준");
		userVo4.setPhoneNumber("010-3330-8969");
		userVo4.setAge(28);
		userVo4.setAddress("경기도 남양주시 호평동");
		userVo4.setEmail("1burgom92@naver.com");
		userVo4.setGender("male");
		userVo4.setRole("ROLE_USER");
		
		resultActions =
		mockMvc
		.perform(post("/api/user/join")	
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(userVo4)));
	
		resultActions
		.andDo(print())
		.andExpect(status().isBadRequest());
	}
	
	// 로그인 test
	@Test
	public void testCLogin() throws Exception{
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
	
	// 회원정보수정
	@Test
	public void testDUpdate() throws Exception{
		ResultActions resultActions;
		UserVo userVo = new UserVo();		
		
		//1. 업데이트 성공 케이스==================================
		//수정불가 데이터
		userVo.setNo(1L);
		userVo.setName("양승준");
		userVo.setId("yyg0825");
		userVo.setGender("남");
		
		//수정가능 데이터
		userVo.setAddress("경기도 남양주시 평내동");
		userVo.setEmail("h3tmdwns@gmail.com");
		userVo.setPhoneNumber("010-9136-4365");
		userVo.setPassword("test123!!!!");
		
		resultActions =
		mockMvc
		.perform(put("/api/user/modify")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(userVo)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk());
		
		//2. 업데이트 실패 케이스====================================
		//2.1 valid password 실패케이스
		userVo.setPassword("test123");
		
		resultActions =
		mockMvc
		.perform(put("/api/user/modify")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(userVo)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isBadRequest());
		
		//2.2 valid email 실패케이스
		userVo.setEmail("123.com");
		
		resultActions =
		mockMvc
		.perform(put("/api/user/modify")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(userVo)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isBadRequest());
	}	
	
	// 아이디 찾기
	@Test
	public void testEFindId() throws Exception{

		ResultActions resultActions;		
		
		//1. Id찾기 성공 케이스==================================

		resultActions =
		mockMvc
		.perform(get("/api/user/find/id")
		.param("name", "양승준")
		.param("email", "yyg0825@naver.com")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is("test1")));
		
		//2. Id찾기 실패 케이스 ======================================
		
		resultActions =
		mockMvc
		.perform(get("/api/user/find/id")
		.param("name", "양승준")
		.param("email", "default@naver.com")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
		
		
	}	
	
	// 패스워드 찾기
	@Test
	public void testFFindPassword() throws Exception{

		ResultActions resultActions;		
		
		//1. Password 찾기 성공 케이스==================================

		resultActions =
		mockMvc
		.perform(get("/api/user/find/password")
		.param("id", "test1")
		.param("email", "yyg0825@naver.com")
		.param("phone", "010-9136-4365")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
		
		//2. Password 찾기 실패 케이스 ======================================
		
		resultActions =
		mockMvc
		.perform(get("/api/user/find/password")
		.param("id", "test12")
		.param("email", "yyg0825@naver.com")
		.param("phone", "010-9136-4365")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
		
		
	}	
	// 로그아웃 test - Oauth
	

	// 카트담기
	
	// 카트삭제
	
	
	
}
