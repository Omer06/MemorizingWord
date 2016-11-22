package com.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	
	private Class<?> clazz;
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
	
	public Logger(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	public void info(String infoText) {
	    String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();	
		System.out.println("ÝNFO : " + simpleDateFormat.format(new Date()) +" - " + clazz.getName() + "." + methodName + " - " + infoText);
	}
	public void error(String errorText) {
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		System.err.println("ERROR : " + simpleDateFormat.format(new Date()) +" - " + clazz.getName() + "." + methodName + " - " + errorText);
	}

}
