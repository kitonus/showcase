package com.jatis.showcase.dto;

public class ResponseVoid extends ResponseDto<Void>{

	public ResponseVoid(String message, boolean success) {
		super(null, message, success);
	}


}
