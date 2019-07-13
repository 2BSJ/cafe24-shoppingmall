package com.cafe24.pjshop.vo;

public class OptionVo {

	private Long no;
	private String value;
	private int price;
	private int stock;
	private Long product_no;
	private Long fixedoption_no;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Long getProduct_no() {
		return product_no;
	}
	public void setProduct_no(Long product_no) {
		this.product_no = product_no;
	}
	public Long getFixedoption_no() {
		return fixedoption_no;
	}
	public void setFixedoption_no(Long fixedoption_no) {
		this.fixedoption_no = fixedoption_no;
	}
	
	
}
