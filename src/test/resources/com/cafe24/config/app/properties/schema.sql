-- 상품
ALTER TABLE `product`
	DROP FOREIGN KEY `FK_category_TO_product`; -- 카테고리 -> 상품

-- 상품옵션
ALTER TABLE `option`
	DROP FOREIGN KEY `FK_product_TO_option`; -- 상품 -> 상품옵션

-- 장바구니(담긴상품정보)
ALTER TABLE `cart`
	DROP FOREIGN KEY `FK_option_TO_cart`; -- 상품옵션 -> 장바구니(담긴상품정보)

-- 장바구니(담긴상품정보)
ALTER TABLE `cart`
	DROP FOREIGN KEY `FK_user_TO_cart`; -- 회원 -> 장바구니(담긴상품정보)

-- 주문
ALTER TABLE `order`
	DROP FOREIGN KEY `FK_user_TO_order`; -- 회원 -> 주문

-- 쿠폰
ALTER TABLE `coupon`
	DROP FOREIGN KEY `FK_user_TO_coupon`; -- 회원 -> 쿠폰

-- 후기
ALTER TABLE `review`
	DROP FOREIGN KEY `FK_product_TO_review`; -- 상품 -> 후기

-- 후기
ALTER TABLE `review`
	DROP FOREIGN KEY `FK_user_TO_review`; -- 회원 -> 후기

-- 결제
ALTER TABLE `payment`
	DROP FOREIGN KEY `FK_order_TO_payment`; -- 주문 -> 결제

-- 상품이미지
ALTER TABLE `image`
	DROP FOREIGN KEY `FK_product_TO_image`; -- 상품 -> 상품이미지

-- 고객동의여부
ALTER TABLE `customer_agreement`
	DROP FOREIGN KEY `FK_user_TO_customer_agreement`; -- 회원 -> 고객동의여부

-- 주문상세
ALTER TABLE `order_detail`
	DROP FOREIGN KEY `FK_order_TO_order_detail`; -- 주문 -> 주문상세

-- 주문상세
ALTER TABLE `order_detail`
	DROP FOREIGN KEY `FK_option_TO_order_detail`; -- 상품옵션 -> 주문상세

-- 회원
DROP TABLE IF EXISTS `user` RESTRICT;

-- 상품
DROP TABLE IF EXISTS `product` RESTRICT;

-- 상품옵션
DROP TABLE IF EXISTS `option` RESTRICT;

-- 장바구니(담긴상품정보)
DROP TABLE IF EXISTS `cart` RESTRICT;

-- 주문
DROP TABLE IF EXISTS `order` RESTRICT;

-- 쿠폰
DROP TABLE IF EXISTS `coupon` RESTRICT;

-- 후기
DROP TABLE IF EXISTS `review` RESTRICT;

-- 카테고리
DROP TABLE IF EXISTS `category` RESTRICT;

-- 결제
DROP TABLE IF EXISTS `payment` RESTRICT;

-- 상품이미지
DROP TABLE IF EXISTS `image` RESTRICT;

-- 고객동의여부
DROP TABLE IF EXISTS `customer_agreement` RESTRICT;

-- 주문상세
DROP TABLE IF EXISTS `order_detail` RESTRICT;

-- 고정옵션(필요없나?)
DROP TABLE IF EXISTS `fixedoption` RESTRICT;

-- 회원
CREATE TABLE `user` (
	`no`           INT UNSIGNED                   NOT NULL COMMENT '회원번호', -- 회원번호
	`name`         VARCHAR(20)                    NOT NULL COMMENT '이름', -- 이름
	`id`           VARCHAR(20)                    NOT NULL COMMENT '아이디', -- 아이디
	`password`     VARBINARY(200)                 NOT NULL COMMENT '비밀번호', -- 비밀번호
	`reg_date`     DATETIME                       NOT NULL COMMENT '등록일', -- 등록일
	`phone_number` VARCHAR(30)                    NOT NULL COMMENT '휴대전화', -- 휴대전화
	`age`          VARCHAR(30)                    NOT NULL COMMENT '생년월일', -- 생년월일
	`address`      VARBINARY(200)                 NOT NULL COMMENT '기본배송지주소', -- 기본배송지주소
	`email`        VARBINARY(200)                 NOT NULL COMMENT '이메일', -- 이메일
	`gender`       varchar(20)                    NOT NULL COMMENT '성별', -- 성별
	`point`        INT UNSIGNED                   NULL     COMMENT '적립금', -- 적립금
	`ordercount`   INT UNSIGNED                   NULL     COMMENT '주문횟수', -- 주문횟수
	`role`         enum('ROLE_USER','ROLE_ADMIN') NOT NULL DEFAULT 'ROLE_USER' COMMENT '권한' -- 권한
)
COMMENT '회원';

-- 회원
ALTER TABLE `user`
	ADD CONSTRAINT `PK_user` -- 회원 기본키
		PRIMARY KEY (
			`no` -- 회원번호
		);

ALTER TABLE `user`
	MODIFY COLUMN `no` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- 상품
CREATE TABLE `product` (
	`no`                 INT UNSIGNED  NOT NULL COMMENT '상품번호', -- 상품번호
	`name`               VARCHAR(50)   NOT NULL COMMENT '상품명', -- 상품명
	`simple_description` VARCHAR(60)   NULL     COMMENT '요약설명', -- 요약설명
	`description`        TEXT          NOT NULL COMMENT '상세설명', -- 상세설명
	`manufacture`        VARCHAR(50)   NOT NULL COMMENT '제조사', -- 제조사
	`buycount`           INT UNSIGNED  NULL     COMMENT '구매횟수', -- 구매횟수
	`price`              INT UNSIGNED  NOT NULL COMMENT '대표판매가격', -- 대표판매가격
	`deliverycost`       INT UNSIGNED  NOT NULL COMMENT '배송비', -- 배송비
	`display_status`     VARCHAR(10)   NOT NULL COMMENT '진열상태', -- 진열상태
	`sales_status`       VARCHAR(10)   NOT NULL COMMENT '판매상태', -- 판매상태
	`special_status`     VARCHAR(10)   NOT NULL COMMENT '특별상품여부', -- 특별상품여부
	`title_status`       VARCHAR(10)   NOT NULL COMMENT '대표상품여부', -- 대표상품여부
	`coupon_status`      VARCHAR(10)   NOT NULL COMMENT '쿠폰적용여부', -- 쿠폰적용여부
	`reg_date`           DATETIME      NOT NULL COMMENT '등록일', -- 등록일
	`modify_reg_date`    DATETIME      NULL     COMMENT '수정일', -- 수정일
	`product_status`     enum('y','n') NOT NULL DEFAULT 'y' COMMENT '상태', -- 상태
	`category_no`        INT UNSIGNED  NULL     COMMENT '카테고리번호' -- 카테고리번호
)
COMMENT '상품';

-- 상품
ALTER TABLE `product`
	ADD CONSTRAINT `PK_product` -- 상품 기본키
		PRIMARY KEY (
			`no` -- 상품번호
		);

ALTER TABLE `product`
	MODIFY COLUMN `no` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '상품번호';

-- 상품옵션
CREATE TABLE `option` (
	`no`         INT UNSIGNED NOT NULL COMMENT '옵션번호', -- 옵션번호
	`value`      VARCHAR(100) NOT NULL COMMENT '완성된옵션값(빨강/M,검흰/260)', -- 완성된옵션값(빨강/M,검흰/260)
	`price`      INT UNSIGNED NOT NULL COMMENT '옵션추가가격', -- 옵션추가가격
	`stock`      INT UNSIGNED NOT NULL COMMENT '재고', -- 재고
	`product_no` INT UNSIGNED NOT NULL COMMENT '상품번호' -- 상품번호
)
COMMENT '상품옵션';

-- 상품옵션
ALTER TABLE `option`
	ADD CONSTRAINT `PK_option` -- 상품옵션 기본키
		PRIMARY KEY (
			`no` -- 옵션번호
		);

ALTER TABLE `option`
	MODIFY COLUMN `no` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '옵션번호';

-- 장바구니(담긴상품정보)
CREATE TABLE `cart` (
	`no`            INT UNSIGNED NOT NULL COMMENT '장바구니번호', -- 장바구니번호
	`amount`        INT UNSIGNED NOT NULL COMMENT '수량', -- 수량
	`non_member_no` INT UNSIGNED NULL     COMMENT '비회원번호', -- 비회원번호
	`option_no`     INT UNSIGNED NOT NULL COMMENT '옵션번호', -- 옵션번호
	`member_no`     INT UNSIGNED NULL     COMMENT '회원번호' -- 회원번호
)
COMMENT '장바구니(담긴상품정보)';

-- 장바구니(담긴상품정보)
ALTER TABLE `cart`
	ADD CONSTRAINT `PK_cart` -- 장바구니(담긴상품정보) 기본키
		PRIMARY KEY (
			`no` -- 장바구니번호
		);

ALTER TABLE `cart`
	MODIFY COLUMN `no` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '장바구니번호';

-- 주문
CREATE TABLE `order` (
	`no`            INT UNSIGNED   NOT NULL COMMENT '주문번호', -- 주문번호
	`cs_status`     VARCHAR(20)    NOT NULL COMMENT 'CS주문상태(교환,반품 등)', -- CS주문상태(교환,반품 등)
	`order_status`  VARCHAR(20)    NOT NULL COMMENT '입금/결제 상태', -- 입금/결제 상태
	`address`       VARBINARY(200) NOT NULL COMMENT '배송주소', -- 배송주소
	`home_number`   VARBINARY(200) NULL     COMMENT '연락번호(집전화)', -- 연락번호(집전화)
	`phone_number`  VARBINARY(200) NOT NULL COMMENT '연락번호(핸드폰)', -- 연락번호(핸드폰)
	`message`       VARCHAR(100)   NULL     COMMENT '배송메세지', -- 배송메세지
	`member_status` VARCHAR(20)    NOT NULL COMMENT '회원구분(회원,비회원)', -- 회원구분(회원,비회원)
	`password`      VARBINARY(200) NULL     COMMENT '주문조회비밀번호', -- 주문조회비밀번호
	`member_no`     INT UNSIGNED   NULL     COMMENT '회원번호' -- 회원번호
)
COMMENT '주문';

-- 주문
ALTER TABLE `order`
	ADD CONSTRAINT `PK_order` -- 주문 기본키
		PRIMARY KEY (
			`no` -- 주문번호
		);

ALTER TABLE `order`
	MODIFY COLUMN `no` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '주문번호';

-- 쿠폰
CREATE TABLE `coupon` (
	`no`           INT UNSIGNED NOT NULL COMMENT '쿠폰번호', -- 쿠폰번호
	`name`         VARCHAR(100) NOT NULL COMMENT '쿠폰명', -- 쿠폰명
	`discount_per` INT UNSIGNED NOT NULL COMMENT '할인율', -- 할인율
	`discount_val` INT UNSIGNED NOT NULL COMMENT '할인값', -- 할인값
	`period`       DATETIME     NOT NULL COMMENT '사용기간', -- 사용기간
	`member_no`    INT UNSIGNED NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '쿠폰';

-- 쿠폰
ALTER TABLE `coupon`
	ADD CONSTRAINT `PK_coupon` -- 쿠폰 기본키
		PRIMARY KEY (
			`no` -- 쿠폰번호
		);

ALTER TABLE `coupon`
	MODIFY COLUMN `no` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '쿠폰번호';

-- 후기
CREATE TABLE `review` (
	`no`              INT UNSIGNED NOT NULL COMMENT '후기번호', -- 후기번호
	`content`         VARCHAR(100) NOT NULL COMMENT '내용', -- 내용
	`grade`           INT UNSIGNED NOT NULL COMMENT '평점', -- 평점
	`image_directory` VARCHAR(100) NULL     COMMENT '이미지경로', -- 이미지경로
	`reg_date`        DATETIME     NOT NULL COMMENT '등록일', -- 등록일
	`product_no`      INT UNSIGNED NULL     COMMENT '상품번호', -- 상품번호
	`member_no`       INT UNSIGNED NULL     COMMENT '회원번호' -- 회원번호
)
COMMENT '후기';

-- 후기
ALTER TABLE `review`
	ADD CONSTRAINT `PK_review` -- 후기 기본키
		PRIMARY KEY (
			`no` -- 후기번호
		);

ALTER TABLE `review`
	MODIFY COLUMN `no` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '후기번호';

-- 카테고리
CREATE TABLE `category` (
	`no`       INT UNSIGNED NOT NULL COMMENT '카테고리번호', -- 카테고리번호
	`name`     VARCHAR(20)  NOT NULL COMMENT '카테고리이름', -- 카테고리이름
	`depth`    INT UNSIGNED NOT NULL COMMENT '깊이', -- 깊이
	`group_no` INT UNSIGNED NOT NULL COMMENT '그룹' -- 그룹
)
COMMENT '카테고리';

-- 카테고리
ALTER TABLE `category`
	ADD CONSTRAINT `PK_category` -- 카테고리 기본키
		PRIMARY KEY (
			`no` -- 카테고리번호
		);

ALTER TABLE `category`
	MODIFY COLUMN `no` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '카테고리번호';

-- 결제
CREATE TABLE `payment` (
	`no`       INT UNSIGNED NOT NULL COMMENT '결제번호', -- 결제번호
	`method`   VARCHAR(20)  NOT NULL COMMENT '결제수단', -- 결제수단
	`status`   VARCHAR(20)  NOT NULL COMMENT '결제상태', -- 결제상태
	`price`    INT UNSIGNED NOT NULL COMMENT '결제금액', -- 결제금액
	`order_no` INT UNSIGNED NULL     COMMENT '주문번호' -- 주문번호
)
COMMENT '결제';

-- 결제
ALTER TABLE `payment`
	ADD CONSTRAINT `PK_payment` -- 결제 기본키
		PRIMARY KEY (
			`no` -- 결제번호
		);

ALTER TABLE `payment`
	MODIFY COLUMN `no` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '결제번호';

-- 상품이미지
CREATE TABLE `image` (
	`no`           INT UNSIGNED NOT NULL COMMENT '이미지번호', -- 이미지번호
	`name`         VARCHAR(60)  NOT NULL COMMENT '파일명', -- 파일명
	`directory`    VARCHAR(100) NOT NULL COMMENT '저장경로', -- 저장경로
	`title_status` VARCHAR(10)  NOT NULL COMMENT '대표이미지(true,false)', -- 대표이미지(true,false)
	`product_no`   INT UNSIGNED NOT NULL COMMENT '상품번호' -- 상품번호
)
COMMENT '상품이미지';

-- 상품이미지
ALTER TABLE `image`
	ADD CONSTRAINT `PK_image` -- 상품이미지 기본키
		PRIMARY KEY (
			`no` -- 이미지번호
		);

ALTER TABLE `image`
	MODIFY COLUMN `no` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '이미지번호';

-- 고객동의여부
CREATE TABLE `customer_agreement` (
	`no`    INT UNSIGNED NOT NULL COMMENT '회원번호', -- 회원번호
	`email` char(4)      NOT NULL COMMENT '메일동의여부', -- 메일동의여부
	`sms`   char(4)      NOT NULL COMMENT 'SMS동의여부', -- SMS동의여부
	`phone` char(4)      NOT NULL COMMENT '전화동의여부', -- 전화동의여부
	`name`  char(4)      NOT NULL COMMENT '실명동의여부' -- 실명동의여부
)
COMMENT '고객동의여부';

-- 고객동의여부
ALTER TABLE `customer_agreement`
	ADD CONSTRAINT `PK_customer_agreement` -- 고객동의여부 기본키
		PRIMARY KEY (
			`no` -- 회원번호
		);

-- 주문상세
CREATE TABLE `order_detail` (
	`no`        INT UNSIGNED NOT NULL COMMENT '주문상세번호', -- 주문상세번호
	`name`      VARCHAR(100) NOT NULL COMMENT '(기록용)상품명', -- (기록용)상품명
	`price`     INT UNSIGNED NOT NULL COMMENT '(기록용)가격', -- (기록용)가격
	`order_no`  INT UNSIGNED NOT NULL COMMENT '주문번호', -- 주문번호
	`option_no` INT UNSIGNED NOT NULL COMMENT '옵션번호' -- 옵션번호
)
COMMENT '주문상세';

-- 주문상세
ALTER TABLE `order_detail`
	ADD CONSTRAINT `PK_order_detail` -- 주문상세 기본키
		PRIMARY KEY (
			`no` -- 주문상세번호
		);

ALTER TABLE `order_detail`
	MODIFY COLUMN `no` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '주문상세번호';

-- 고정옵션(필요없나?)
CREATE TABLE `fixedoption` (
	`no`    INT UNSIGNED NOT NULL COMMENT '고정옵션번호', -- 고정옵션번호
	`name`  VARCHAR(100) NOT NULL COMMENT '옵션이름(사이즈,컬러 등)', -- 옵션이름(사이즈,컬러 등)
	`value` VARCHAR(100) NOT NULL COMMENT '옵션값' -- 옵션값
)
COMMENT '고정옵션(필요없나?)';

-- 고정옵션(필요없나?)
ALTER TABLE `fixedoption`
	ADD CONSTRAINT `PK_fixedoption` -- 고정옵션(필요없나?) 기본키
		PRIMARY KEY (
			`no` -- 고정옵션번호
		);

-- 상품
ALTER TABLE `product`
	ADD CONSTRAINT `FK_category_TO_product` -- 카테고리 -> 상품
		FOREIGN KEY (
			`category_no` -- 카테고리번호
		)
		REFERENCES `category` ( -- 카테고리
			`no` -- 카테고리번호
		);

-- 상품옵션
ALTER TABLE `option`
	ADD CONSTRAINT `FK_product_TO_option` -- 상품 -> 상품옵션
		FOREIGN KEY (
			`product_no` -- 상품번호
		)
		REFERENCES `product` ( -- 상품
			`no` -- 상품번호
		);

-- 장바구니(담긴상품정보)
ALTER TABLE `cart`
	ADD CONSTRAINT `FK_option_TO_cart` -- 상품옵션 -> 장바구니(담긴상품정보)
		FOREIGN KEY (
			`option_no` -- 옵션번호
		)
		REFERENCES `option` ( -- 상품옵션
			`no` -- 옵션번호
		);

-- 장바구니(담긴상품정보)
ALTER TABLE `cart`
	ADD CONSTRAINT `FK_user_TO_cart` -- 회원 -> 장바구니(담긴상품정보)
		FOREIGN KEY (
			`member_no` -- 회원번호
		)
		REFERENCES `user` ( -- 회원
			`no` -- 회원번호
		);

-- 주문
ALTER TABLE `order`
	ADD CONSTRAINT `FK_user_TO_order` -- 회원 -> 주문
		FOREIGN KEY (
			`member_no` -- 회원번호
		)
		REFERENCES `user` ( -- 회원
			`no` -- 회원번호
		);

-- 쿠폰
ALTER TABLE `coupon`
	ADD CONSTRAINT `FK_user_TO_coupon` -- 회원 -> 쿠폰
		FOREIGN KEY (
			`member_no` -- 회원번호
		)
		REFERENCES `user` ( -- 회원
			`no` -- 회원번호
		);

-- 후기
ALTER TABLE `review`
	ADD CONSTRAINT `FK_product_TO_review` -- 상품 -> 후기
		FOREIGN KEY (
			`product_no` -- 상품번호
		)
		REFERENCES `product` ( -- 상품
			`no` -- 상품번호
		);

-- 후기
ALTER TABLE `review`
	ADD CONSTRAINT `FK_user_TO_review` -- 회원 -> 후기
		FOREIGN KEY (
			`member_no` -- 회원번호
		)
		REFERENCES `user` ( -- 회원
			`no` -- 회원번호
		);

-- 결제
ALTER TABLE `payment`
	ADD CONSTRAINT `FK_order_TO_payment` -- 주문 -> 결제
		FOREIGN KEY (
			`order_no` -- 주문번호
		)
		REFERENCES `order` ( -- 주문
			`no` -- 주문번호
		);

-- 상품이미지
ALTER TABLE `image`
	ADD CONSTRAINT `FK_product_TO_image` -- 상품 -> 상품이미지
		FOREIGN KEY (
			`product_no` -- 상품번호
		)
		REFERENCES `product` ( -- 상품
			`no` -- 상품번호
		);

-- 고객동의여부
ALTER TABLE `customer_agreement`
	ADD CONSTRAINT `FK_user_TO_customer_agreement` -- 회원 -> 고객동의여부
		FOREIGN KEY (
			`no` -- 회원번호
		)
		REFERENCES `user` ( -- 회원
			`no` -- 회원번호
		);

-- 주문상세
ALTER TABLE `order_detail`
	ADD CONSTRAINT `FK_order_TO_order_detail` -- 주문 -> 주문상세
		FOREIGN KEY (
			`order_no` -- 주문번호
		)
		REFERENCES `order` ( -- 주문
			`no` -- 주문번호
		);

-- 주문상세
ALTER TABLE `order_detail`
	ADD CONSTRAINT `FK_option_TO_order_detail` -- 상품옵션 -> 주문상세
		FOREIGN KEY (
			`option_no` -- 옵션번호
		)
		REFERENCES `option` ( -- 상품옵션
			`no` -- 옵션번호
		);