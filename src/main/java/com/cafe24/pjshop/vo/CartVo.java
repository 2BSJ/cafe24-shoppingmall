package com.cafe24.pjshop.vo;

import lombok.Data;

@Data
public class CartVo {

	private Long no;
	private int amount;
	private int nonMemberNo;
	private Long optionNo;
	private Long memberNo;

}
