package com.cafe24.pjshop.vo;

import lombok.Data;

@Data
public class ReviewVo {

	private Long no;
	private String content;
	private int grade;
	private String imageDirectory;
	private String regDate;
	private Long productNo;
	private Long memberNo;

	
}
