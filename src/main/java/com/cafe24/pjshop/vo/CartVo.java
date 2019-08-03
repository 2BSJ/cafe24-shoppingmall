package com.cafe24.pjshop.vo;

import java.util.List;

import lombok.Data;

@Data
public class CartVo {

	private Long no;
	private int amount;
	private int nonMemberNo;
	private Long optionNo;
	private Long memberNo;
	
	private String value;
	private int optionPrice;
	
	private String name;
	private int price;

}
