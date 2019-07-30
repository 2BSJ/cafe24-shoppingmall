package com.cafe24.pjshop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class CategoryVo {

	private Long no;
	
	@NotEmpty
	@Length(min=1)
	private String name;
	
	private int depth;
	private int groupNo;
	
	public CategoryVo(String name) {
		this.name = name;
	}
	public CategoryVo() {
		
	}

}
