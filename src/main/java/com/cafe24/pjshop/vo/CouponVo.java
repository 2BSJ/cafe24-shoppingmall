package com.cafe24.pjshop.vo;

import lombok.Data;

@Data
public class CouponVo {

	private Long no;
	private String name;
	private int discountPer;
	private int discountVal;
	private String period;
	private Long memberNo;

	
}
