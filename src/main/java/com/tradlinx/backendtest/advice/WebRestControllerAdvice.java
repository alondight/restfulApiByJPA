package com.tradlinx.backendtest.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tradlinx.backendtest.dto.ErrorResponse;
import com.tradlinx.backendtest.exception.CustomdException;

@RestControllerAdvice
public class WebRestControllerAdvice {
	
	@ExceptionHandler(CustomdException.class)
	public ErrorResponse handleNotFoundException(CustomdException ex) {
		ErrorResponse responseMsg = new ErrorResponse(ex.getMessage(), ex.statusCode);
		return responseMsg;
	}
}