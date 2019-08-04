/* User Test Data */
/* 기본 유저값 추가*/
INSERT 
	INTO user(no,name,id,password,reg_date,phone_number,age,address,email,gender,point,ordercount)
		VALUES( null, 
				'양승준',
		   		'test1', 
		   		SHA2('test123!!',512), 
		   		now(), 
		  		'010-9136-4365', 
		   		28, 
		   		AES_ENCRYPT('경기도 남양주시 호평동',SHA2('cafe',512)), 
		   		AES_ENCRYPT('yyg0825@naver.com',SHA2('cafe',512)), 
		   		'male',
		   		0,
		   		0);

/* Admin Category Data */
/* 기본 상위 카테고리값 추가 */
INSERT 
	INTO category
		VALUES(null, '상의', 1, (select 
								ifnull(MAX(tmp.group_no)+1, 1) 
								from category tmp));

/* Admin Product Data*/
/* 기본 상품 값 추가 */
INSERT
	INTO product
		VALUES(null,
			   '나이키 저스트두잇 티셔츠',
			   '나이키X양승준 콜라보 제품입니다',
			   '<div><span>나이키 두잇 X 양승준 콜라보 어쩌고저쩌고 전체 설명</span></div>',
			   '미국',
			   0,
			   56000,
			   5000,
			   'y',
			   'y',
			   'n',
			   'y',
			   'n',
			   now(),
			   now(),
			   'y',
			   1
			   );
/*상품의 옵션*/
INSERT
	INTO cafe24.option
		VALUES(null,
			   '빨강/S',
			   2000,
			   100,
			   1
			   );
INSERT
	INTO cafe24.option
		VALUES(null,
			   '빨강/M',
			   2000,
			   100,
			   1
			   );
INSERT
	INTO cafe24.option
		VALUES(null,
			   '빨강/L',
			   2000,
			   100,
			   1
			   );
			   
/*상품의 이미지*/
INSERT
	INTO cafe24.image
		VALUES(null,
			   '201907201400.jpg',
			   '/img',
			   'y',
			   1);
			   
INSERT
	INTO cafe24.image
		VALUES(null,
			   '201907201401.jpg',
			   '/img',
			   'n',
			   1);
			   
/* cart 데이터 */
			   
INSERT
	INTO cart
		VALUES(null,
			   3,
			   0,
			   1,
			   1
			   );

/* 주문 데이터 */
INSERT
	INTO order_t
		VALUES(null,
			   'b',
			   't',
			   'b',
			   AES_ENCRYPT('호평',SHA2('cafe',512)),
			   AES_ENCRYPT('031-511-0365',SHA2('cafe',512)),
			   AES_ENCRYPT('010-9136-4365',SHA2('cafe',512)),
			   '경비실에 맡겨요',
			   't',
			   SHA2('0',512),
			   now(),
			   1
			   );		   
								
