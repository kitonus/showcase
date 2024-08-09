package com.jatis.showcase.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jatis.showcase.dto.ResponseDto;
import com.jatis.showcase.dto.ResponseVoid;

import io.micrometer.core.instrument.config.validate.ValidationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<ResponseDto<Map<String, String>>> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
	    		ResponseDto.getInstance(errors, ex.getMessage(), false));
	}

	@ExceptionHandler({ValidationException.class})
	public ResponseEntity<ResponseVoid> handleException(
	  ValidationException ex) {
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
	    		new ResponseVoid(ex.getMessage(), false));
	}

	@ExceptionHandler({Throwable.class})
	public ResponseEntity<ResponseVoid> handleException(
			Throwable ex) {
		log.error("Unexpected error", ex);
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
	    		new ResponseVoid(ex.getMessage(), false));
	}
}
