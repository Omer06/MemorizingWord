package com.control.extrapage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("result")
public class ResultController {
	
	@RequestMapping(value = "controll" , method = RequestMethod.POST)
	public ModelAndView resultControll() {
		return new ModelAndView("redirect:/result/succesfull");
	}
	
	@RequestMapping(value = "succesfull")
	public ModelAndView succesfull() {
		return new ModelAndView("/result/succesfull");
	}
	

}
