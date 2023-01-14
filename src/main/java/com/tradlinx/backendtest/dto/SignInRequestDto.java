package com.tradlinx.backendtest.dto;

import javax.validation.constraints.NotEmpty;

public class SignInRequestDto {

    @NotEmpty
    private String userid;

    @NotEmpty
    private String pw;

	public String getUserid() {
		return userid;
	}

	public String getPw() {
		return pw;
	}

	public SignInRequestDto setUserid(String userid) {
		this.userid = userid;
		return this;
	}

	public SignInRequestDto setPw(String pw) {
		this.pw = pw;
		return this;
	}
	
}
