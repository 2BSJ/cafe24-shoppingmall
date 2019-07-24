/* User Test Data */
INSERT 
	INTO user 
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
		   		0,
		   		'ROLE_USER');
