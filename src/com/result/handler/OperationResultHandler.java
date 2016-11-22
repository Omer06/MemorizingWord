package com.result.handler;

import org.springframework.stereotype.Component;

@Component
public class OperationResultHandler {
	
	private String operationResult = "";
	
	public  void setOperationResult(String result) {
		operationResult = result;
	}
	
	public String getOperationResult() {
		return operationResult;
	}
	
}
