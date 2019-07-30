package com.cafe24.pjshop.controller.admin.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
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
import com.cafe24.pjshop.dto.JSONResult;
import com.cafe24.pjshop.vo.ImageVo;
import com.cafe24.pjshop.vo.OptionVo;
import com.cafe24.pjshop.vo.ProductVo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class,TestWebConfig.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminProductControllerTest { 
	
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
	//상품추가
	@Test
	public void testAAddProduct() throws Exception{
		ResultActions resultActions;		
		ProductVo productVo1 = new ProductVo();
		List<OptionVo> optionList = new ArrayList<OptionVo>();
		List<ImageVo> imageList = new ArrayList<ImageVo>();
		
		//옵션의 경우엔 프론트에서 에이잭스처리한후 병합된값이 들어간 VO를 보내준다.
		OptionVo optionVo1 = new OptionVo(null,"검흰/260",2000,100,null);
		OptionVo optionVo2 = new OptionVo(null,"검흰/265",2000,100,null);
		OptionVo optionVo3 = new OptionVo(null,"빨노/260",2000,100,null);
		OptionVo optionVo4 = new OptionVo(null,"빨노/265",2000,100,null);
		
		optionList.add(optionVo1);
		optionList.add(optionVo2);
		optionList.add(optionVo3);
		optionList.add(optionVo4);
		
		ImageVo imageVo1 = new ImageVo(null,"201907211500.jpg","/img","y",null);
		ImageVo imageVo2 = new ImageVo(null,"201907211501.jpg","/img","n",null);
		ImageVo imageVo3 = new ImageVo(null,"201907211502.jpg","/img","n",null);
		ImageVo imageVo4 = new ImageVo(null,"201907211503.jpg","/img","n",null);
		
		imageList.add(imageVo1);
		imageList.add(imageVo2);
		imageList.add(imageVo3);
		imageList.add(imageVo4);

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
		
		//2. 상품 등록 실패 case=====================================
		//2.1 카테고리no가 유효하지 않는 값일때
		productVo1.setCategoryNo(15L);
		
		resultActions =
		mockMvc
		.perform(post("/api/admin/product")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(productVo1)));
				
				
		resultActions
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
		

	}	
	
	@Test
	public void testBGetProductList() throws Exception{
		ResultActions resultActions;		
	// 1. 성공 case==================================
	//1.1 처음 관리자 상품목록 들어왔을때
		resultActions =
		mockMvc
		.perform(get("/api/admin/product")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data[0].name",is("나이키 저스트두잇 티셔츠")))
		.andExpect(jsonPath("$.data[1].name",is("아디다스 티셔츠")));
	//1.2 상품목록에서 검색어와 category를 선택했을때
		resultActions =
		mockMvc
		.perform(get("/api/admin/product")
		.param("name", "아디다스") // like % % 
		.param("categoryNo", "1")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
	//1.3 판매중이지 않은 상품만 조회했을때
		resultActions =
		mockMvc
		.perform(get("/api/admin/product")
		.param("salesStatus", "n")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data[0].salesStatus",is("n")));
	//2. 상품목록 조회 실패 case==================
	//2.1 데이터가 없을경우
		resultActions =
		mockMvc
		.perform(get("/api/admin/product")
		.param("categoryNo", "5")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	// 상품목록조회중에 하나를 클릭했을때
	@Test
	public void testCGetProductDetail() throws Exception{
		ResultActions resultActions;
	// 1. 성공 case==================================
	//1.1 처음 관리자 상품목록 들어왔을때
		resultActions =
		mockMvc
		.perform(get("/api/admin/product/1")
		.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
		
//		String content = resultActions.andReturn().getResponse().getContentAsString();
//		Gson gson = new Gson();
//		JSONResult jsonResult = gson.fromJson(content, JSONResult.class);
//		ProductVo productVo = (ProductVo) jsonResult.getData();
//		System.out.println(productVo.getDeliverycost() + "!!!!!!!!!!!");

		

	}
}