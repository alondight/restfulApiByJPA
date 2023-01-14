package com.tradlinx.backendtest.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tradlinx.backendtest.dto.JsonResponse;
import com.tradlinx.backendtest.dto.ProfileResponseDto;
import com.tradlinx.backendtest.dto.SignInRequestDto;
import com.tradlinx.backendtest.dto.SignUpRequestDto;
import com.tradlinx.backendtest.entity.User;
import com.tradlinx.backendtest.exception.CustomdException;
import com.tradlinx.backendtest.service.UserService;
import com.tradlinx.backendtest.util.JwtTokenUtil;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "API FOR USER", description = "과제와 관련된 API 입니다.")
@Slf4j
@RestController
public class UserController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	//signup
	@SuppressWarnings("finally")
	@PostMapping(path = "/signup", produces="application/json")
	public JsonResponse signup( @RequestBody @Valid SignUpRequestDto user)
	{

		log.info("API Page - Signup");
		log.info("Signup : " + user.toString());

		JsonResponse result =  new JsonResponse();

		try {
			
			User users = new User();
			users.setPw(passwordEncoder.encode(user.getPw()));
			users.setUserid(user.getUserid());
			users.setUsername(user.getUsername());
			User u = userService.save(users);


			result.setStatus("200");
			result.setData(u);
		} catch (Exception e ) {
			result.setStatus("400");
			result.setData("DUPLICATED_ID");
		}finally{
			return result;
		}


	}

	//signin
	@SuppressWarnings("finally")
	@PostMapping(path = "/signin", produces="application/json")
	public JsonResponse signin(@RequestBody @Valid SignInRequestDto user)
	{

		log.info("API Page - signin");
		log.info("signin : " + user.getUserid() + ":" + user.getPw());
		String token = "";
		try{

			
		} catch(Exception e ) {
			throw new CustomdException("No ID OR PW", 400);
		}
		
		JsonResponse result =  new JsonResponse();
		try {
			User _user = userService.findByUserid(user.getUserid());
			String userName = _user.getUsername().toString();
			String seq      = String.valueOf(_user.getSeq());
			String encdoePassword = _user.getPw();
			if("".equals(userName)) {
				throw new CustomdException("No ID OR PW", 400);
			} else {
				if(!passwordEncoder.matches(user.getPw(), encdoePassword)) {
					throw new CustomdException("No ID OR PW", 400);
				} else {
					token = jwtTokenUtil.makeJwtToken(seq, userName);
				}
			}

			result.setStatus("200");
			result.setData(token);
		} catch (Exception e) {
			result.setStatus("400");
			result.setData("No ID OR PW");
		}finally{
			return result;
		}
	}

	//profile
	@SuppressWarnings("finally")
	@GetMapping(path = "/profile", produces="application/json")
	public JsonResponse profile(Authentication authentication)
	{

		log.info("API Page - profile");
		
		JsonResponse result =  new JsonResponse();
		try {
			User user = (User) authentication.getPrincipal();

			ProfileResponseDto users = new ProfileResponseDto();
			users.setUserid(user.getUserid());
			users.setUsername(user.getUsername());

			result.setStatus("200");
			result.setData((users));
		} catch (Exception e) {
			result.setStatus("403");
			result.setData("No Authorized");
			return result;
		}finally{
			return result;
		}
	}

	//points
	@SuppressWarnings("finally")
	@GetMapping(path = "/points", produces="application/json")
	public JsonResponse points(Authentication authentication)
	{

		log.info("API Page - points");

		User userDetails = (User) authentication.getPrincipal();
		JsonResponse result =  new JsonResponse();

		try{
			result.setStatus("200");
			result.setData(String.valueOf(userDetails.getPoints()));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return result;
		}
	}
}
