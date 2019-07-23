package com.cafe24.pjshop.vo;

import com.cafe24.pjshop.validator.constraints.ValidEmail;
import com.cafe24.pjshop.validator.constraints.ValidId;
import com.cafe24.pjshop.validator.constraints.ValidName;
import com.cafe24.pjshop.validator.constraints.ValidPassword;
import com.cafe24.pjshop.validator.constraints.ValidPhone;

public class UserVo {

	private Long no;
	
	@ValidName
	private String name;
	
	@ValidId
	private String id;
	
	@ValidPassword
	private String password;
	
	private String regDate;
	
	@ValidPhone
	private String phoneNumber;
	
	private int age;
	
	private String address;
	
	@ValidEmail
	private String email;
	
	private String gender;
	private int point;
	private int ordercount;
	private String role; // default
	private String key = "cafe";
	
	public UserVo() {}//기본생성자
	public UserVo(String id) {this.id=id;}
	public UserVo(Long no,String name,String id,String password, String regDate,
					String phoneNumber,int age,String address,String email,String gender,
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
		this.gender = gender;
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
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", id=" + id + ", password=" + password + ", regDate=" + regDate
				+ ", phoneNumber=" + phoneNumber + ", age=" + age + ", address=" + address + ", email=" + email
				+ ", gender=" + gender + ", point=" + point + ", ordercount=" + ordercount + ", role=" + role + ", key="
				+ key + "]";
	}
	
	
	
	
	
}
