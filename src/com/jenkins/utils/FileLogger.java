package com.jenkins.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class FileLogger {
	
	private String closingKey;
	
	private BufferedWriter writer;
	
	private Date date;
	
	public FileLogger(String closingKey) throws IOException {
		date = new Date();
		this.closingKey = closingKey;
		File file = new File("deployer_"+date.toString()+"_.txt");
		writer = new BufferedWriter(new FileWriter(file));
	}
	
	public void log(String str) throws IOException {
		writer.write(date.toString()+" :: " +str);
	}
	
	public void close() throws IOException {
		writer.close();
	}
	
	public String getClosingKey() {
		return this.closingKey;
	}

}
