package com.jenkins.utils;

public class FileLogger {
	
	private String closingKey;
	
	public FileLogger(String closingKey) {
		this.closingKey = closingKey;
	}
	
	public void log(String str) {
		System.out.print(str);
	}
	
	public String getClosingKey() {
		return this.closingKey;
	}

}
