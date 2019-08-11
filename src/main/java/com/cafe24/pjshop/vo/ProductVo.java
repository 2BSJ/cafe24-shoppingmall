package com.cafe24.pjshop.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVo {

	/*
	 * 
	 *  displayStatus = "y","n"
	 *  salesStatus = "y","n"
	 *  specialStatus ="y","n"
	 *  titleStatus ="y","n"
	 *  couponStatus ="y","n"
	 *  
	 * 
	 */
	 	private Long no;
		private String name;
		private String simpleDescription;
		private String description;
		private String manufacture;
		private int buycount;
		private int price;
		private int deliverycost;
		private String displayStatus;
		private String salesStatus;
		private String specialStatus;
		private String titleStatus;
		private String couponStatus;
		private String regDate;
		private String modifyRegDate;
		private String productStatus;
		private Long categoryNo;
		private String categoryName;
		private String titleImageName;
		
		private List<OptionVo> optionList; // front에서 ajax로 완성된 옵션배합을 value값에 넣어서 같이 보내줌
		private List<ImageVo> imageList;
		private List<ReviewVo> reviewList;

	
	
	

	
	

	
}
