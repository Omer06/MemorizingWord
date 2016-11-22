package com.control.extrapage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.logger.Logger;
import com.result.handler.OperationResultHandler;
import com.service.UserService;

@Controller
@RequestMapping("/changepassword")
public class ChangePasswordController {

	private static final Logger log = new Logger(ChangePasswordController.class);

	@Autowired
	UserService userServiceImp;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	OperationResultHandler operationResultHandler;
	
	@RequestMapping("/**")
	public ModelAndView showPage(){
		log.info("Þifre deðiþtirme sayfasý açýlýyor");
		
		return new ModelAndView("changepassword" , "result" , operationResultHandler.getOperationResult());
	}
	
	@RequestMapping(value = "/change" , method = RequestMethod.POST)
	public ModelAndView newPassword(String password , String newPassword){
		
		@SuppressWarnings("static-access")
		String username = new SecurityContextHolder().getContext().getAuthentication().getName();
		log.info(username + " isimli kullanýcýnýn þuanki passwordu : " + password + " iken " + newPassword + " ' e update ediliyor oluyor.");
		
		if(passwordEncoder.matches(password, userServiceImp.getPassword(username))){
			userServiceImp.updatePassword(username, passwordEncoder.encode(newPassword));
			operationResultHandler.setOperationResult("Þifreniz baþarýyla deðiþtirildi.");
		}else { operationResultHandler.setOperationResult("Lütfen þifrenizi doðru giriniz."); }
		
		return new ModelAndView("redirect:/changepassword/form");
	}
}
