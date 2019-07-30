package com.cafe24.pjshop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionVo {

	private Long no;
	private String value;
	private int price;
	private int stock;
	
	private Long productNo;
	

}
