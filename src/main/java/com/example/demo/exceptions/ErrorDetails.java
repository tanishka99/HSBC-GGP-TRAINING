package com.example.demo.exceptions;

public class ErrorDetails {
		
	private String fieldName;
	private String message;
	public ErrorDetails() {
		super();
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ErrorDetails(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	

}
