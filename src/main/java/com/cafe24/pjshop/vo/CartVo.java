package com.cafe24.pjshop.vo;

import lombok.Data;

@Data
public class CartVo {

	private Long no;
	private int amount;
	private int nonMemberNo;
	private Long optionNo;
	private Long memberNo;
	
	private String optionName;
	private int optionPrice;
	
	private String productName;
	private int productPrice;
	
	private String imageName;
	
	private String userName;

}
