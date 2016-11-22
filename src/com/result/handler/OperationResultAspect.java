package com.result.handler;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.logger.Logger;

@Aspect
@Component
public class OperationResultAspect {

	private static final Logger log = new Logger(OperationResultAspect.class);
	
	@Autowired
	OperationResultHandler operationResultHandler;
	
	@After("execution(String com.result.handler.OperationResultHandler.getOperationResult())")
	public void resetResult(){
		log.info("Ýþlemin sonucu teslim edildi , resetleniyor...");
		operationResultHandler.setOperationResult("");
	}
}
