package com.control.errorpage;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/exception")
public class ExceptionController {
	
	//eriþim hatasý
	@RequestMapping("denied")
	public ModelAndView errorDenied(){
		return new ModelAndView("/errorpages/errorDenied");
	}
	
	//sayfa bulunamadý
	@RequestMapping("/404")
	public ModelAndView error404(HttpServletRequest request){
		return new ModelAndView("/errorpages/error404");
	}
	
}
