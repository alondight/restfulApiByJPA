
<br />

========================================================================================
1-1. Java API 구현하기

(1) 이 서비스를 위한 SQL(H2 Embeded DB) 스키마를 설계해 주세요. 테이블과 인덱스에 대한 DDL이 필요합니다.
### 1-1.SQL Schema
```sql

CREATE TABLE `USER`
(
    SEQ            IDENTITY        PRIMARY KEY,
    USERID varchar(255)    UNIQUE  not null,
    PW varchar(255)    not null,
    USERNAME varchar(255)    not null,
    POINTS         bigint not null default 0
);

CREATE TABLE ARTICLE
(
    ARTICLEID IDENTITY        PRIMARY KEY,
    SEQ bigint          not null,
    ARTICLETITLE varchar(255)    not null,
    ARTICLECONTENTS varchar(255)
);

CREATE TABLE COMMENT
(
    COMMENTSID IDENTITY        PRIMARY KEY,
    SEQ           bigint          not null,
    ARTICLEID bigint          not null,
    COMMENTSCOTENTS varchar(255)    not null
);

#Unit test를 위해서 입력값은 하나 넣으셔야합니다.
insert into "USER" VALUES(1,"string","$2a$10$kKyAkUllc4cPrUa88RPSZexb9aCJXctiVbwfzvqlXH9inIQL5vBQ6","joon",0)


```
```
(2) 프로젝트 character set은 UTF-8로 설정해 주세요.

설정완료
```
```
(3) 과제 구현 시 Java11, Spring Boot, JPA, H2 Embeded DB, Maven 을 전부 활용합니다.

모두 활용
```
```
(4) 추가 Framework, Library 사용에 제약은 없습니다.

OK
```
```
(5) 모든 응답에 대하여 application/json 타입으로 구현합니다.

설정완료
```
```
(6) 아래 API를 제공하는 서버 애플리케이션을 작성해 주세요.

작성완료
```
```
(7) 각 기능 및 제약사항에 대한 단위 테스트를 작성하시고 Maven 기준으로 실행되게 해주세요.

작성완료
```
```
(8) Swagger를 이용하여 API확인 및 API실행이 가능하도록 구현해 주세요.

구현완료
```
```
(9) README 파일에 Swagger 주소 및 요구사항 구현여부, 구현방법, 테스트 방법과 결과에 대해 작성해 주세요.

Swagger 주소 - localhost:8080/api-docs
요구사항 - 회원가입, 댓글게시판, 포인트
구현여부 - 완료
구현방법 - JAVA11에 최신버전으로 개발
       - Java 11, Spring Boot 2.7.8-SNAPSHOT, Spring Data JPA, Spring Security, Maven, H2 Embeded DB, lombok, jjwt*, springdoc
테스트 방법 - JUnit5 , Spring-test
            /test/
```
```
(10) 작업한 결과물을 Github(public) 에 올리고 URL을 알려주세요.

URL : 
```

### 2.서술형 문제
```
(1) MSA 구성하는 방식에는 어떤 것들이 있고, 그중 어떤 방식을 선택하실지 서술해 주세요.
- API GATEWAY - Spring Cloud
- DIscovery
- Config Server
- Oauth Server & JWT
- Message Queue & RabbitMQ
- Zipkin & Sleuth
등 기본적인 MSA 환경을 바탕으로 구축을 할 것 같습니다.
Cloud나 단일 서버에서도 가능합니다.
```
<br />

========================================================================================