package com.control.extrapage;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entities.User;
import com.logger.Logger;
import com.result.handler.OperationResultHandler;
import com.service.UserService;

@Controller
@RequestMapping("register")
public class RegisterController {
	
	private static final Logger log = new Logger(RegisterController.class);

	@Autowired
	UserService userServiceImp;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	OperationResultHandler operationResultHandler;
	
	@RequestMapping("form")
	public ModelAndView showRegisterPage() {
		log.info("Kay�t sayfas� g�steriliyor.");
		
		return new ModelAndView("register" , "newUser" , new User()).addObject("result", operationResultHandler.getOperationResult());
	}
	
	@RequestMapping(value = "save" , method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute User newUser) {
		log.info("Kullan�c� kayd� yap�l�yor.");
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		newUser.setEnable(true);
	
		operationResultHandler.setOperationResult("Ba�ar�yla sisteme kay�t oldunuz.");
		
		if(!userServiceImp.save(newUser)){ operationResultHandler.setOperationResult("Sisteme kay�t olunurken beklenmedik bir hata olu�tu"); }
		
		return new ModelAndView("redirect:/register/form");
	}
	
	@RequestMapping("validate")
	public @ResponseBody Map<String, Object> validate(String columnName , String value) {
		Map<String, Object> response = new HashMap<String,Object>();
															//(kolonAd�   , de�eri)
		boolean isHasUser =  userServiceImp.isHasByAttribute(columnName, value);
		response.put("isHasUser", isHasUser);
		
		log.info(columnName + " : " + value + " olan kullan�c� var m� ? cevap : " + isHasUser);
		return response;
	}
}
