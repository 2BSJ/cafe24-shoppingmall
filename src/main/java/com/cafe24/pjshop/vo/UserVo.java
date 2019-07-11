package com.cafe24.pjshop.vo;

import org.hibernate.validator.constraints.*;

public class UserVo {

	private Long no;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	@Length(min = 4, max = 12)
	private String id;
	
	@Length(min = 8, max = 16)
	private String password;
	
	private String regDate;
	@NotEmpty
	private String phoneNumber;
	
	private int age;
	
	@NotEmpty
	private String address;
	
	@NotEmpty
	private String email;
	
	private int point;
	private int ordercount;
	
	public UserVo() {}//기본생성자
	
	public UserVo(Long no,String name,String id,String password, String regDate,
					String phoneNumber,int age,String address,String email,
					int point,int ordercount) {
		this.no = no;
		this.name = name;
		this.id = id;
		this.password = password;
		this.regDate = regDate;
		this.phoneNumber = phoneNumber;
		this.age = age;
		this.address = address;
		this.email = email;
		this.point= point;
		this.ordercount = ordercount;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getOrdercount() {
		return ordercount;
	}

	public void setOrdercount(int ordercount) {
		this.ordercount = ordercount;
	}
	
	
}
