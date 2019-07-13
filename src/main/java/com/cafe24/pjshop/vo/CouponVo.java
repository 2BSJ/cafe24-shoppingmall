package com.cafe24.pjshop.vo;

public class CouponVo {

	private Long no;
	private String name;
	private int discount_per;
	private int discount_val;
	private String period;
	private Long member_no;
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
	public int getDiscount_per() {
		return discount_per;
	}
	public void setDiscount_per(int discount_per) {
		this.discount_per = discount_per;
	}
	public int getDiscount_val() {
		return discount_val;
	}
	public void setDiscount_val(int discount_val) {
		this.discount_val = discount_val;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public Long getMember_no() {
		return member_no;
	}
	public void setMember_no(Long member_no) {
		this.member_no = member_no;
	}
	
	
}
