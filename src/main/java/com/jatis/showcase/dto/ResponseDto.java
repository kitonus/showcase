package com.jatis.showcase.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ResponseDto<T> {

	private T data;
	
	private String message;
	private boolean success = true;
	
	ResponseDto(T data, String message, boolean success) {
		super();
		this.data = data;
		this.message = message;
		this.success = success;
	}
	
	public static <T> ResponseDto<T> getInstance(T data, String message, boolean success){
		return new ResponseDto<T>(data, message, success) {
		};
	}
	public static <T> ResponseDto<T> getInstance(T data, String message){
		return new ResponseDto<T>(data, message, true) {
		};
	}
	public static <T> ResponseDto<T> getInstance(T data){
		return new ResponseDto<T>(data, null, true) {
		};
	}

	public static ResponseDto<Void> getInstance(){
		return new ResponseDto<>(null, null, true) {
		};
	}
}
