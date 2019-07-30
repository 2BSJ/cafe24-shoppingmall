package com.cafe24.pjshop.vo;

import com.cafe24.pjshop.validator.constraints.ValidEmail;
import com.cafe24.pjshop.validator.constraints.ValidId;
import com.cafe24.pjshop.validator.constraints.ValidName;
import com.cafe24.pjshop.validator.constraints.ValidPassword;
import com.cafe24.pjshop.validator.constraints.ValidPhone;

import lombok.Data;

@Data
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

	
	
	
}
