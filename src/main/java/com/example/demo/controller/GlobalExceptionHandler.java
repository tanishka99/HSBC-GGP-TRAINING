package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.exceptions.ErrorDetails;
import com.example.demo.exceptions.ErrorInfo;
import com.example.demo.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErrorDetails>> handleInvalidMovieException(MethodArgumentNotValidException ex) {
		List<FieldError> listErrors = ex.getFieldErrors();
		List<ErrorDetails> listErrorDetails = listErrors.stream()
				.map(fieldError -> new ErrorDetails(fieldError.getField(), fieldError.getDefaultMessage()))
				.collect(Collectors.toList());

		return new ResponseEntity<List<ErrorDetails>>(listErrorDetails, HttpStatus.BAD_REQUEST);
	}
	// this exception is giving msg for validation
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorInfo> handleResourcenNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorInfo errorInfo = new ErrorInfo(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.NOT_FOUND);
	}

}
