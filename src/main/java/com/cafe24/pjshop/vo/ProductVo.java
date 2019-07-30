package com.cafe24.pjshop.vo;

import java.util.List;


import lombok.Data;

@Data
public class ProductVo {

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
	private Long categoryNo;
	
	private List<OptionVo> optionList; // front에서 ajax로 완성된 옵션배합을 value값에 넣어서 같이 보내줌
	private List<ImageVo> imageList;
	private List<ReviewVo> reviewList;
	
	public ProductVo(Long no,String name,String simpleDescription,String description,
					String manufacture, int buycount, int price,int deliverycost,
					String displayStatus,String salesStatus,String specialStatus, String titleStatus,
					String couponStatus,Long categoryNo) {
		this.no = no;
		this.name = name;
		this.simpleDescription = simpleDescription;
		this.description = description;
		this.manufacture = manufacture;
		this.buycount = buycount;
		this.price = price;
		this.deliverycost = deliverycost;
		this.displayStatus = displayStatus;
		this.salesStatus = salesStatus;
		this.specialStatus = specialStatus;
		this.titleStatus = titleStatus;
		this.couponStatus = couponStatus;
		this.categoryNo = categoryNo;
	}
	public ProductVo() {}

	

	
	

	
}
