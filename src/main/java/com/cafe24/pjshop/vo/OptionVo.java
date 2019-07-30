package com.cafe24.pjshop.vo;

import lombok.Data;

@Data
public class OptionVo {

	private Long no;
	private String value;
	private int price;
	private int stock;
	
	private Long productNo;
	private Long fixedoptionNo;
	

}
