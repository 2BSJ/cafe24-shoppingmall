package com.cafe24.pjshop.controller.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.pjshop.config.TestAppConfig;
import com.cafe24.pjshop.config.TestWebConfig;
import com.cafe24.pjshop.controller.admin.api.AdminProductControllerTest;
import com.cafe24.pjshop.vo.ImageVo;
import com.cafe24.pjshop.vo.OptionVo;
import com.cafe24.pjshop.vo.ProductVo;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class,TestWebConfig.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductControllerTest {

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
	
	//관리자에서 테스트용 상품 추가
	@Test
	public void testAAddProduct() throws Exception{
		ResultActions resultActions;		
		ProductVo productVo1 = new ProductVo();
		List<OptionVo> optionList = new ArrayList<OptionVo>();
		List<ImageVo> imageList = new ArrayList<ImageVo>();
		
		//옵션의 경우엔 프론트에서 에이잭스처리한후 병합된값이 들어간 VO를 보내준다.
		OptionVo optionVo1 = new OptionVo(null,"검흰/260",2000,100,null);
		OptionVo optionVo2 = new OptionVo(null,"검흰/265",2000,100,null);
		
		optionList.add(optionVo1);
		optionList.add(optionVo2);
		
		ImageVo imageVo1 = new ImageVo(null,"201907211500.jpg","/img","y",null);
		ImageVo imageVo2 = new ImageVo(null,"201907211501.jpg","/img","n",null);
		
		imageList.add(imageVo1);
		imageList.add(imageVo2);

		//1. 상품 추가 성공 case ==================================
		productVo1.setName("아디다스 티셔츠");
		productVo1.setSimpleDescription("아디다스 제품입니다.");
		productVo1.setDescription("<div><span>아디다스 모라모라</span></div>");
		productVo1.setManufacture("영국");
		productVo1.setPrice(66000);
		productVo1.setDeliverycost(10000);
		productVo1.setDisplayStatus("y");
		productVo1.setSalesStatus("y");
		productVo1.setSpecialStatus("n");
		productVo1.setTitleStatus("n");
		productVo1.setCouponStatus("n");
		productVo1.setCategoryNo(1L);
		
		productVo1.setOptionList(optionList);
		productVo1.setImageList(imageList);
			
		resultActions =
		mockMvc
		.perform(post("/api/admin/product")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(productVo1)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
	
	}
	@Test
	public void testBGetProductList() throws Exception{
		ResultActions resultActions;		
	// 1. 성공 case==================================
	//1.1 처음 유저가 상품목록을 들어왔을때
		resultActions =
		mockMvc
		.perform(get("/api/product")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data[0].name",is("나이키 저스트두잇 티셔츠")));
	//1.2 유저가 대표상품만 보기 옵션 선택했을때
		resultActions =
		mockMvc
		.perform(get("/api/product")
		.param("titleStatus","y")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data[0].name",is("나이키 저스트두잇 티셔츠")));
	//1.3 유저가 스페셜 상품만 보기 옵션 선택했을때
		resultActions =
		mockMvc
		.perform(get("/api/product")
		.param("specialStatus", "y")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	@Test
	public void testCGetProductDetail() throws Exception{
		ResultActions resultActions;
	// 1. 성공 case==================================
	//1.1  유저가 목록을 보다가 상품하나 클릭했을때
		resultActions =
		mockMvc
		.perform(get("/api/product/1")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
	// 2. 실패 case ===================
	// 2.1 잘못된 값이 넘어갔을 경우
		resultActions =
		mockMvc
		.perform(get("/api/product/0")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
		
	}
	
	
}
