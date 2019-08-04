package com.cafe24.pjshop.controller.admin.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.pjshop.config.TestAppConfig;
import com.cafe24.pjshop.config.TestWebConfig;
import com.cafe24.pjshop.vo.CartVo;
import com.cafe24.pjshop.vo.OrderVo;
import com.google.gson.Gson;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class,TestWebConfig.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminOrderControllerTest {

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
	public void testAGetListByOption() throws Exception{
		
		ResultActions resultActions;
	// 1.1 정상적 성공 case
		
		resultActions = 
		mockMvc
		.perform(get("/api/admin/order")
		.param("csStatus", "b")
		.param("orderStatus", "b")
		.param("payStatus", "t")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
		
		resultActions = 
		mockMvc
		.perform(get("/api/admin/order")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
	
	}

	
	// 주문한 상세내역 보기
	@Test
	public void testBGetVo() throws Exception{
		
		ResultActions resultActions;
	// 1. 회원이 주문목록을 봤을때 성공 case
	// 1.1 정상적 성공 case
		resultActions = 
		mockMvc
		.perform(get("/api/admin/order/1")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
	}
	
	@Test
	public void testCModifyVo() throws Exception{
		
		ResultActions resultActions;
	// 1. 회원의 주문중 입금을 확인해서 배송을 보냈을때 case 
	// 1.1 정상적 성공 case
		OrderVo orderVo1 = new OrderVo();
		orderVo1.setNo(1L);
		orderVo1.setOrderStatus("p");
		
		resultActions = 
		mockMvc
		.perform(put("/api/admin/order")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(orderVo1)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
	}
	
	
}
