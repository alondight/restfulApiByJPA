package com.tradlinx.backendtest.exception;

import lombok.NonNull;

@SuppressWarnings("serial")
public class CustomdException extends RuntimeException{

	public @NonNull int statusCode;

	public CustomdException(String msg, int code) {
		super(msg);
		this.statusCode = code;
	}
	
}
