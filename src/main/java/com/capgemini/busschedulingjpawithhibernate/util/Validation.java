package com.capgemini.busschedulingjpawithhibernate.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	
	public Integer validateId(String userId) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(userId);
		if(matcher.matches()){
			return Integer.parseInt(userId);
		}else{
			return null;
		}
	}

	public String validateEmail(String emailId) {
		Pattern pattern = Pattern.compile("\\w+\\@\\w+\\.\\w+");
		Matcher matcher = pattern.matcher(emailId);
		if(matcher.matches()){
			return emailId;
		}else{
			return null;
		}
	}

	public Long validateContact(String contactNo) {
		Pattern pattern = Pattern.compile("\\d{10}");
		Matcher matcher = pattern.matcher(contactNo);
		if(matcher.matches()) {
			return Long.parseLong(contactNo);
		}else {
			return null;
		}
	}
}
