package com.cafe24.pjshop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageVo {

	private Long no;
	private String name;
	private String directory;
	private String titleStatus;
	private Long productNo;

}
