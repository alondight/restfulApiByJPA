package com.tradlinx.backendtest.dto;


import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class SignUpRequestDto {

    @NotEmpty
    private  String userid;

    @NotEmpty
    private  String pw;

    @NotEmpty
    @Length(max = 200)
    private  String username;

	public String getUserid() {
		return userid;
	}

	public String getPw() {
		return pw;
	}

	public String getUsername() {
		return username;
	}

	public SignUpRequestDto setUserid(String userid) {
		this.userid = userid;
		return this;
	}

	public SignUpRequestDto setPw(String pw) {
		this.pw = pw;
		return this;
	}

	public SignUpRequestDto setUsername(String username) {
		this.username = username;
		return this;
	}
}
