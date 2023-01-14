package com.tradlinx.backendtest.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class ErrorResponse {

	@NonNull
	private String message;

	@NonNull
	private int code;

	public ErrorResponse(@NonNull String message, @NonNull int code) {
		super();
		this.message = message;
		this.code = code;
	}
}
