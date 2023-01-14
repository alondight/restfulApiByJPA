package com.tradlinx.backendtest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.tradlinx.backendtest.dto.SignInRequestDto;
import com.tradlinx.backendtest.entity.User;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserService userService;

	@Autowired
	private MockMvc mockMvc;
	/*
	- 회원 가입 API
	endpoint    :    /signup
	method        :    POST
	필수 파라미터
	    아이디 : userid (String)
	    패스워드 : pw (String)
	    이름 : username (String)
	파라미터 예시
	    {
	        "userid":"userid",
	        "pw":"passw0rd",
	        "username":"username"
	    }
	*/
		
	    @DisplayName("signup")
	    @Test
	    void signup(){
	        //given
	        final User user = new User();
	        user.setUserid("string");
	        user.setPw("string");
	        user.setUsername("JOON");

	        //when
	        when(userService.save(user)).thenReturn(user);
	        final User saveUser = userService.save(user);

	        //then
	        assertEquals(user.getUsername(), saveUser.getUsername());
	        verify(userService).save(user);
	    }	
		


	/*
	- 로그인 API (accessToken 발급해서 응답으로 반환)
	    endpoint    :    /signin
	    method        :    POST
	    필수 파라미터
	        아이디 : userid (String)
	        패스워드 : pw (String)
	    파라미터 예시
	        {
	            "userid":"userid",
	            "pw":"passw0rd"
	        }
	*/
	    @DisplayName("signin")
	    @Test
	    void signin() throws Exception{
	        // given
	    	SignInRequestDto json = new SignInRequestDto();
	        json.setUserid("string");
	        json.setPw("string");

	        // when
	    	ResultActions resultActions = mockMvc.perform(
	    	        MockMvcRequestBuilders.post("/signin")
	    	                .contentType(MediaType.APPLICATION_JSON)
	    	                .content(new Gson().toJson(json)));
	    	//then 
		        resultActions.andExpect(status().isOk())
		                .andExpect(jsonPath("status","200").exists());
	    }

	    /*
	- 내 정보 조회 API
	    endpoint    :    /profile
	    method        :    GET
	    필수 파라미터
	        accessToken (JWT) : header
	    파라미터 예시
	        -header 'Authorization: Bearer JwtToken'
	*/
	    @DisplayName("profile")
	    @Test
	    void profile() throws Exception{
	        // given
	    	SignInRequestDto json = new SignInRequestDto();
	        json.setUserid("string");
	        json.setPw("string");
	        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/signin")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.accept(MediaType.APPLICATION_JSON)
	                .content(new Gson().toJson(json)))
	                .andExpect(status().isOk()).andReturn();

	        String response = result.getResponse().getContentAsString();
	        JSONObject jsonObject = new JSONObject(response);
	        String token = jsonObject.getString("data");
	        // when
	        MvcResult result2 = mockMvc.perform(MockMvcRequestBuilders.get("/profile")
	        		.header("Authorization", "Bearer " + token)).andExpect(status().isOk()).andReturn();

	    	//then 
	        String response2 = result2.getResponse().getContentAsString();
			System.out.println(response2);
	    }
	/*
	- 내 Points 조회 API
	    endpoint    :    /points
	    method        :    GET
	    필수 파라미터
	        accessToken (JWT) : header
	    파라미터 예시
	        -header 'Authorization: Bearer JwtToken'
	*//*
	- 글 쓰기 API (응답으로 articleId 반환, Points 3 증가)
	    endpoint    :    /article
	    method        :    POST
	    필수 파라미터
	        accessToken (JWT) : header
	        글 제목 : articleTitle(String)
	        글 내용 : articleContents(String)
	    파라미터 예시
	        -header 'Authorization: Bearer JwtToken'
	        {
	            "articleTitle":"articleTitle",
	            "articleContents":"articleContents"
	        }
	*//*
	- 글 수정 API (응답으로 articleId 반환)
	    endpoint    :    /article
	    method        :    PUT
	    필수 파라미터
	        accessToken (JWT) : header
	        글 아이디 : articleId (String)
	        글 제목 : articleTitle(String)
	        글 내용 : articleContents(String)
	    파라미터 예시
	        -header 'Authorization: Bearer JwtToken'
	        {
	            "articleId":"articleId",
	            "articleTitle":"articleTitle",
	            "articleContents":"articleContents"
	        }    
	*//*
	- 글 조회 API (응답으로 articleId 와 연결된 commentsId 반환)
	    endpoint    :    /article/{articleId}
	    method        :    GET
	    필수 파라미터
	        accessToken (JWT) : header
	        글 아이디 : articleId (String)
	    파라미터 예시
	        -header 'Authorization: Bearer JwtToken'
	        /article/1
	*//*
	- 글 삭제 API (응답으로 삭제 count 1 반환, 해당 글로 획득한 Points 전부 제거)
	    endpoint    :    /article/{articleId}
	    method        :    DELETE
	    필수 파라미터
	        accessToken (JWT) : header
	        글 아이디 : articleId (String)
	    파라미터 예시
	        -header 'Authorization: Bearer JwtToken'
	        /article/1    
	*//*
	- 댓글 쓰기 API (응답으로 articleId 와 연결된 commentsId 반환, 댓글 작성자 Points 2 증가, 원글 작성자 Points 1 증가)
	    endpoint    :    /comments
	    method        :    POST
	    필수 파라미터
	        accessToken (JWT) : header
	        글 id : articleId (String)
	        댓글 내용 : commentsCotents (String)
	    파라미터 예시
	        -header 'Authorization: Bearer JwtToken'
	        {
	            "articleId":"articleId",
	            "commentsCotents ":"commentsCotents "
	        }
	*//*
	- 댓글 삭제 API (응답으로 articleId 와 연결된 commentsId 반환, 해당 댓글로 획득된 모든 Points 제거)
	    endpoint    :    /comments/{commentsId}
	    method        :    DELETE
	    필수 파라미터
	        accessToken (JWT) : header
	        댓글 아이디 : commentsId (String)
	    파라미터 예시
	        -header 'Authorization: Bearer JwtToken'
	        /comments/1  
	*/
}
