package com.control.errorpage;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/exception")
public class ExceptionController {
	
	//eri�im hatas�
	@RequestMapping("denied")
	public ModelAndView errorDenied(){
		return new ModelAndView("/errorpages/errorDenied");
	}
	
	//sayfa bulunamad�
	@RequestMapping("/404")
	public ModelAndView error404(HttpServletRequest request){
		return new ModelAndView("/errorpages/error404");
	}
	
}
