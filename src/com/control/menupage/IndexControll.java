package com.control.menupage;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.logger.Logger;

@Controller
public class IndexControll {
	
	private static final Logger log = new Logger(IndexControll.class);
	
	@RequestMapping(value = {"/" , "/index"})
	public ModelAndView showIndexPage(HttpServletRequest request) throws Exception {
		
		log.info("showLoginPage : AnaSayfa gösteriliyor.");
		return new ModelAndView("index");
	}
}
