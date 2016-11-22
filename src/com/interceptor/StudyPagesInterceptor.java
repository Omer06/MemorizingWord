package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.entities.StudyOptions;

public class StudyPagesInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("interceotpr çalýþtý");
		StudyOptions studyOptions = (StudyOptions) request.getSession().getAttribute("studyOptions");
		
		if(studyOptions == null){
			response.sendRedirect(request.getContextPath() + "/study/options");
			return false;
		}
		
		return true;
	}

}
