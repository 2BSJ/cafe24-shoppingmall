package com.cafe24.pjshop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

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
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroup(int groupNo) {
		this.groupNo = groupNo;
	}
}
