package com.cafe24.pjshop.controller.admin.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
		//1. 처음에 들어있던 category Get ==================================
		resultActions =
		mockMvc
		.perform(get("/api/admin/category/list")
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
	public void testCAddCategory() throws Exception{
		ResultActions resultActions;
		CategoryVo categoryVo1 = new CategoryVo();		
			
		//1. 최상위 카테고리 추가 케이스==================================
		//수정불가 데이터
		categoryVo1.setName("신발");
		
		resultActions =
		mockMvc
		.perform(post("/api/admin/category")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(categoryVo1)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data",is(true)));
		
		//2. 하위 카테고리 추가 케이스============================
		CategoryVo categoryVo2 = new CategoryVo();	
		categoryVo2.setName("샌달");
		categoryVo2.setDepth(2);
		categoryVo2.setGroupNo(2);
		
		resultActions =
		mockMvc
		.perform(post("/api/admin/category")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(categoryVo2)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data",is(true)));
		
	}	
	
	//상위 카테고리 추가하고나서 get
	@Test
	public void testDGetListAfterAddCategory() throws Exception{
		ResultActions resultActions;		
		//1. 상위카테고리 추가하고 나서 get ==================================
		resultActions =
		mockMvc
		.perform(get("/api/admin/category/list")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data[0].name", is("상의")))
		.andExpect(jsonPath("$.data[1].name", is("신발")));
		
		resultActions =
		mockMvc
		.perform(get("/api/admin/category/list/2")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data[0].name", is("신발")));

	}	
	
	@Test
	public void testEDeleteCategory() throws Exception{
		ResultActions resultActions;	
		CategoryVo categoryVo1 = new CategoryVo();	
		//1. delete 성공 case ==================================
		//1.1 최상위카테고리인 옷 삭제했을때
		categoryVo1.setNo(1L);
		categoryVo1.setName("옷");
		categoryVo1.setDepth(1);
		categoryVo1.setGroupNo(1);
		
		resultActions =
		mockMvc
		.perform(delete("/api/admin/category")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(categoryVo1)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(true)));
		

		//1.2 최상위 카테고리인 신발 삭제했을때 -- 하위 카테고리인 샌달도 같이 삭제============
		CategoryVo categoryVo2 = new CategoryVo();	
		categoryVo2.setNo(2L);
		categoryVo2.setName("신발");
		categoryVo2.setDepth(1);
		categoryVo2.setGroupNo(2);
		
		resultActions =
		mockMvc
		.perform(delete("/api/admin/category")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(categoryVo2)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(true)));
		
		//2. 실패 case
		//2.1 categoryVo가 이상한 값이 넘어갔을때
		
		CategoryVo categoryVo3 = new CategoryVo();	
		categoryVo3.setNo(0L);
		categoryVo3.setName("");
		categoryVo3.setDepth(0);
		categoryVo3.setGroupNo(0);
		
		resultActions =
		mockMvc
		.perform(delete("/api/admin/category")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(categoryVo3)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));		
	}	
	
	//카테고리가 없을때 getList
	@Test
	public void testFGetListAfterAllCategoryDelete() throws Exception{
		ResultActions resultActions;		
		//1. 상위카테고리 추가하고 나서 get ==================================
		resultActions =
		mockMvc
		.perform(get("/api/admin/category/list")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
		

	}	
}