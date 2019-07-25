package com.cafe24.pjshop.controller.admin.api;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

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

import com.cafe24.pjshop.config.TestAppConfig;
import com.cafe24.pjshop.config.TestWebConfig;
import com.cafe24.pjshop.vo.CategoryVo;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class,TestWebConfig.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@Rollback(value = true)
//@Transactional
public class AdminCategoryControllerTest {
	
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
	public void testAgetList() throws Exception{
		ResultActions resultActions;		
		//1. 중복체크 된 case ==================================
		resultActions =
		mockMvc
		.perform(get("/api/admin/category")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data[0].name", is("상의")));

	}		
	
	@Test
	public void testBcheckName() throws Exception{
		ResultActions resultActions;	
		String name;	
		//1. 중복체크 된 case ==================================
		name = "상의";
		resultActions =
		mockMvc
		.perform(get("/api/admin/category/{name}",name)
		.contentType(MediaType.APPLICATION_JSON)
		.characterEncoding("UTF-8"));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
		
		//2. 중복없는 사용가능 case=================================
		name="사용가능";
		resultActions =
		mockMvc
		.perform(get("/api/admin/category/{name}",name)
		.contentType(MediaType.APPLICATION_JSON)
		.characterEncoding("UTF-8"));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
		
	}		
	
	// 상위 카테고리 추가
	@Test
	public void testCaddTopCategory() throws Exception{
		ResultActions resultActions;
		CategoryVo categoryVo = new CategoryVo();		
			
		//1. 업데이트 성공 케이스==================================
		//수정불가 데이터
		categoryVo.setName("신발");
		
		resultActions =
		mockMvc
		.perform(post("/api/admin/category")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(categoryVo)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk());
		
	}	
}
