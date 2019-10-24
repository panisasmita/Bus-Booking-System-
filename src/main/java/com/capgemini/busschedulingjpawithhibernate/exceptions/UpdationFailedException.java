package com.capgemini.busschedulingjpawithhibernate.exceptions;

public class UpdationFailedException extends RuntimeException {

	private String message="Id doesn't exist";
	public UpdationFailedException(String string) {
		super( string);
				}

	public String UpdationFailedException() {
		return message;
	}
	
}
