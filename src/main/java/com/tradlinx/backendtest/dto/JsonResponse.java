package com.tradlinx.backendtest.dto;

public class JsonResponse {
	private String status;
	private Object data;

	public String getStatus() {
		return status;
	}
	public JsonResponse setStatus(String status) {
		this.status = status;
		return this;
	}
	public Object getData() {
		return data;
	}
	public JsonResponse setData(Object data) {
		this.data = data;
		return this;
	}
}
