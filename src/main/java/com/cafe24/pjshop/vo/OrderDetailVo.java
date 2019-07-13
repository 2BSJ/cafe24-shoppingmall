package com.cafe24.pjshop.vo;

public class OrderDetailVo {

	private Long no;
	private String name;
	private int price;
	private Long order_no;
	private Long option_no;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Long getOrder_no() {
		return order_no;
	}
	public void setOrder_no(Long order_no) {
		this.order_no = order_no;
	}
	public Long getOption_no() {
		return option_no;
	}
	public void setOption_no(Long option_no) {
		this.option_no = option_no;
	}
	
	
}
