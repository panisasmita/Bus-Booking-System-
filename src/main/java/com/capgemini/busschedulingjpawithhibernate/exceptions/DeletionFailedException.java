package com.capgemini.busschedulingjpawithhibernate.exceptions;

public class DeletionFailedException extends Exception {
	
	private String message="this id does not exist!!!!!";
	public DeletionFailedException(String string) {
		super(string);
				}

	public String DeletionFailedException() {
		return message;
	}
}
