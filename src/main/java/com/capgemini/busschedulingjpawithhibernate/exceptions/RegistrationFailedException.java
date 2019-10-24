package com.capgemini.busschedulingjpawithhibernate.exceptions;

public class RegistrationFailedException extends Exception{

	private String message="RegistrationFailedException";
	public RegistrationFailedException() {
		super();
				}

	public String RegistrationFailedException() {
		return message;
	}
}
