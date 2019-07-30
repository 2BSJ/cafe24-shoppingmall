//package com.cafe24.pjshop.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//import com.cafe24.pjshop.vo.ProductVo;
//
//@Service
//public class ProductService {
//
//	
//	public List<ProductVo> list() {
//	
//		List<ProductVo> list = new ArrayList<ProductVo>();
//		ProductVo p1 = new ProductVo(1L,"아디다스 팔콘","아디다스 신발",
//				"아디다스에서 정성들여 만든 제품입니다...(TEXT)",
//				"아디다스",0,240000,5000,"전시","n","y","y","y",1L);
//		
//		ProductVo p2 = new ProductVo(1L,"에어팟 2버전","블루투스 이어폰",
//				"애플에서 정성들여 만든 제품입니다.....(TEXT)",
//				"애플",0,170000,5000,"전시","y","n","n","n",2L);
//										//sale,special,title,coupon,category
//		
//		ProductVo p3 = new ProductVo(1L,"아디다스 조던1","조던이 신었던 신발입니다",
//				"아디다스에서 전설이라 불리우는 ....(TEXT)",
//				"아디다스",0,150000,5000,"전시","y","y","n","n",1L);
//										//sale,special,title,coupon,category
//		
//		list.add(p1);
//		list.add(p2);
//		list.add(p3);
//		return list;
//	}
//	
//	public List<ProductVo> list(Long category_no) {
//		List<ProductVo> list = new ArrayList<ProductVo>();
//		
//		//ProductVo의 제일 마지막 필드인 categoryNo = no 로 테이블에서 서치
//		if(category_no == 1) {
//		ProductVo p1 = new ProductVo(1L,"아디다스 팔콘","아디다스 신발",
//				"아디다스에서 정성들여 만든 제품입니다...(TEXT)",
//				"아디다스",0,240000,5000,"전시","n","y","y","y",1L);
//		
//		ProductVo p2 = new ProductVo(1L,"아디다스 조던1","조던이 신었던 신발입니다",
//				"아디다스에서 전설이라 불리우는 ....(TEXT)",
//				"아디다스",0,150000,5000,"전시","y","y","n","n",1L);
//										//sale,special,title,coupon,category
//		
//		list.add(p1);
//		list.add(p2);
//		return list;
//		}
//		else if(category_no == 2) {
//			ProductVo p1 = new ProductVo(1L,"에어팟 2버전","블루투스 이어폰",
//					"애플에서 정성들여 만든 제품입니다.....(TEXT)",
//					"애플",0,170000,5000,"전시","y","n","n","n",2L);
//			list.add(p1);
//			return list;
//		}
//		else {
//			return null;
//		}
//	}
//
//	public ProductVo detail(Long no) {
//		if(no==1) {
//		ProductVo productVo = new ProductVo(1L,"아디다스 팔콘","아디다스 신발",
//				"아디다스에서 정성들여 만든 제품입니다...(TEXT)",
//				"아디다스",0,240000,5000,"전시","n","y","y","y",1L);
//		return productVo;
//		}
//		else
//			return null;
//	}
//
//	public List<ProductVo> search(String name) {
//		//String name = 에어팟
//		List<ProductVo> list = new ArrayList<ProductVo>();
//		
//		if("에어팟".equals(name)) {
//			ProductVo p1 = new ProductVo(1L,"에어팟 2버전","블루투스 이어폰",
//				"애플에서 정성들여 만든 제품입니다.....(TEXT)",
//				"애플",0,170000,5000,"전시","y","n","n","n",2L);
//				list.add(p1);
//				return list;
//		}
//		else if("아디다스".equals(name)) {
//			ProductVo p1 = new ProductVo(1L,"아디다스 팔콘","아디다스 신발",
//					"아디다스에서 정성들여 만든 제품입니다...(TEXT)",
//					"아디다스",0,240000,5000,"전시","n","y","y","y",1L);
//			
//			ProductVo p2 = new ProductVo(1L,"아디다스 조던1","조던이 신었던 신발입니다",
//					"아디다스에서 전설이라 불리우는 ....(TEXT)",
//					"아디다스",0,150000,5000,"전시","y","y","n","n",1L);
//			list.add(p1);
//			list.add(p2);
//			return list;
//		}
//		
//		return null;
//	}
//
//
//
//}
