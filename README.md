# cafe24-shoppingmall 프로젝트 구성도

## src/main/java 구성

### com.cafe24 packages

#### config.app
  * DBConfig - MariaDb 연결 설정
    * com/cafe24/config/**app**/properties/jdbc.properties파일에 url,driver 등 db셋팅설정
  * MyBatisConfig - Mybatis 설정
    * com/cafe24/config/**app**/mybatis/configuration.xml 파일에 mapper파일 위치와 Vo alias 설정  
#### config.web
  * FileuploadConfig - MultipartResolver 설정
    * com/cafe24/config/**web**/properties/multipart.properties파일에 업로드 사이즈 및 가상경로 매핑
  * MessageConfig - Validate 할때 쓸 메세지 설정
  * MVCConfig - ViewResolver 설정, Default Servlet Handler 설정, Message Converter(Json) 설정
  * SecurityConfig - Interceptor 경로 및 환경설정
  * SwaggerConfig - Swagger base package 및 path 설정, swagger-ui.html 파일설정

#### pjshop.controller
  * MainController - 메인컨트롤러
  * AdminController
  * OrderController  
  * ProductController  
  * UserController

#### pjshop.controller.api
###### url 및 json형식 데이터 확인 api
  * AdminController - 카테고리 관리, 상품 관리, 옵션 관리, 주문정보 관리, 회원정보 관리, 쿠폰 관리 url 처리  
  * OrderController - 상품주문, 주문내역, 결제 url 처리  
  * ProductController - 상품목록조회, 상품상세보기, 상품검색하기 url 처리  
  * UserController - 회원가입, 로그인, 로그아웃, 장바구니 관리, 마이페이지 관리 url 처리  

#### pjshop.dto
  * JSONResult - Json Class 정의  
   **result,message,(Object)data**  
   `성공,실패시 result에 success,fail 삽입`  
   `성공시 data에 Object 삽입 message에 null값 넣은뒤 JSONResult return`  
   `실패시 data에 null 삽입 message에 실패메세지 넣은뒤 JSONResult return`

#### pjshop.repository
  * AgreementDao
  * CartDao
  * CategoryDao
  * CouponDao
  * FixedOptionDao
  * ImageDao
  * OptionDao
  * OrderDao
  * OrderDetailDao
  * PaymentDao
  * ReviewDao
  * UserDao

#### pjshop.security
  * Auth - @Auth 설정
  * AuthInterceptor - `@Auth` 어노테이션 처리
  * AuthLoginInterceptor - login url 가져와서 세션처리
  * AuthLogoutInterceptor - logout url 가져와서 세션처리
  * AuthUser - @AuthUser 설정
  * AuthUserHandlerMethodArgumentResolver

#### pjshop.service
  * AdminService
  * OrderService
  * ProductService
  * UserService

#### pjshop.vo
  * AgreementVo
  * CartVo
  * CategoryVo
  * CouponVo
  * FixedOptionVo
  * ImageVo
  * OptionVo
  * OrderVo
  * OrderDetailVo
  * PaymentVo
  * ReviewVo
  * UserVo

## src/test/java 구성중...

## Swagger API - localhost:8080/cafe24-shoppingmall/swagger-ui.html
<img src='./Readme image/api목록.PNG
#### UserController  
<img src='./Readme image/회원가입 시퀀스다이어그램.PNG'>  

* ID중복체크요청  
`get`
`/api/user/checkid`  
`param` = `String id`  
* 회원가입  
`post`  
`/api/user/join`  
`param` = `UserVo`  
---
<img src='./Readme image/회원로그인 시퀀스다이어그램.PNG'>  

* 회원 로그인  
`post`  
`/api/user/login`  
`param` = `String id`
`param` = `String password`
---
<img src='./Readme image/회원로그아웃 시퀀스다이어그램.PNG'>  

* 회원 로그아웃  
`post`  
`/api/user/logout`  
`param` = `없음`
---
<img src='./Readme image/회원정보수정 시퀀스다이어그램.PNG'>  

* 회원정보수정  
`put`  
`/api/user/modify`  
`param` = `UserVo`
---
 

* 장바구니담기  
`post`  
`/api/user/cart`  
`param` = `Long no`
---


* 장바구니삭제
`delete`  
`/api/user/cart`  
`param` = `Long no`
