package com.cafe24.pjshop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailVo {

	private Long no;
	private String name;
	private String url;
	private int price;
	private Long order_no;
	private Long option_no;

	
	
}
