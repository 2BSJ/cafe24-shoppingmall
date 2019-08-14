package com.cafe24.pjshop.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVo {

	private Long no;
	private String csStatus; // 교환 c, 반품 rc , 환불 r , 기본 b 
	private String payStatus; // 입금전 f, 입금완료 t
	private String orderStatus; // 배송준비중  b , 배송보류 f, 배송대기 r, 배송중 p , 배송완료 t  
	private String address;
	private String homeNumber;
	private String phoneNumber;
	private String message;
	private String memberStatus; //회원 t, 비회원 f
	private String password;
	private String regDate;
	private Long memberNo;
	private List<CartVo> cartList;
	private String key = "cafe";
	private String name;
}
