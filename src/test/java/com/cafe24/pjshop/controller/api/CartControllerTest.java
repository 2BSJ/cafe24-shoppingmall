package com.cafe24.pjshop.controller.api;

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
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.pjshop.config.TestAppConfig;
import com.cafe24.pjshop.config.TestWebConfig;
import com.cafe24.pjshop.vo.CartVo;
import com.google.gson.Gson;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class,TestWebConfig.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CartControllerTest {

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

	//장바구니에 상품 추가
	@Test
	public void testAAddProduct() throws Exception{
		
		ResultActions resultActions;
	// 1. 회원이 상품을 장바구니에 담았을때 성공 case
	// 1.1 정상적 성공 case
		CartVo cartVo = new CartVo();
		cartVo.setAmount(2);
		cartVo.setNonMemberNo(0);
		cartVo.setOptionNo(2L);
		cartVo.setMemberNo(1L);
		
		resultActions = 
		mockMvc
		.perform(post("/api/cart")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(cartVo)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
	
	}
	
	@Test
	public void testBGetList() throws Exception{
		
		ResultActions resultActions;
	// 1. 회원이 장바구니 리스트를 불러왔을때 성공 case..
	// 1.1 정상적 성공 case
		resultActions = 
		mockMvc
		.perform(get("/api/cart/1")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
		
	// 2. 실패 case
	// 2.1 유저정보로된 카트리스트가 없을때
		resultActions = 
		mockMvc
		.perform(get("/api/cart/2")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	@Test
	public void testCModifyVo() throws Exception{
		
		ResultActions resultActions;
	// 1. 성공 case
	// 1.1 회원이 장바구니 상품중 두개의 상품을 삭제했을때 case.
		
		List<CartVo> cartList = new ArrayList<CartVo>();
		CartVo cartVo1 = new CartVo();
		cartVo1.setAmount(5);
		cartVo1.setNo(1L);
		cartList.add(cartVo1);
		
		resultActions = 
		mockMvc
		.perform(put("/api/cart")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(cartList)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
		
	// 2. 실패 case
	// 2.1 리스트의 값이 없이 그냥 넘어갔을 때 case.
		
		List<CartVo> failcartList = new ArrayList<CartVo>();

		
		resultActions = 
		mockMvc
		.perform(put("/api/cart")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(failcartList)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
	// 2.2 리스트는 넘어갔지만 카트의 번호가 유효하지 않을때 case.
		cartList.clear();
		cartVo1.setAmount(5);
		cartVo1.setNo(5L);
		cartList.add(cartVo1);
		
		resultActions = 
		mockMvc
		.perform(put("/api/cart")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(cartList)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
		
		

	}
	
	@Test
	public void testDDeleteVo() throws Exception{
		
		ResultActions resultActions;
	// 1. 성공 case
	// 1.1 회원이 장바구니 상품중 두개의 상품을 삭제했을때 case.
		
		List<Long> cartNoList = new ArrayList<Long>();
		Long long1 = 1L;
		Long long2 = 2L;
		cartNoList.add(long1);
		cartNoList.add(long2);
		
		resultActions = 
		mockMvc
		.perform(delete("/api/cart")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(cartNoList)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
		
	// 2. 실패 case
	// 2.1 리스트의 값이 없이 그냥 넘어갔을 때 case.
		
		List<Long> cartNoNumberList = new ArrayList<Long>();

		
		resultActions = 
		mockMvc
		.perform(delete("/api/cart")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(cartNoNumberList)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));

	}
	
	
}
