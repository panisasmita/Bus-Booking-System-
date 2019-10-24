package com.capgemini.busschedulingjpawithhibernate.exceptions;

public class BusNotFoundException extends RuntimeException{

	private String message="No Buses Available";

	public BusNotFoundException(String string) {
		super(string);
				}

	public String getMessage() {
		return message;
	}
	
}
