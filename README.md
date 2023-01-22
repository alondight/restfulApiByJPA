
<br />

========================================================================================
### 1.SQL Schema
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

<br />

========================================================================================


### 2. 사용방법
```
1. git clone & clean & install & run
2. http://localhost:8080/h2console 접속 후 
   JDBC-URL : jdbc:h2:~/springEmbeded-db
   ID : sa
   비밀번호 없음
3. DDL 생성
4. http://localhost:8080/api-docs 접속 후 테스트
5. Unit Test는 JWT 토큰인증받는 부분까지만 테스트 완료 후 Swagger로 테스트 하였음
   /test/*
```