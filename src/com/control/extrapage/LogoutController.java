package com.control.extrapage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.logger.Logger;

@Controller
public class LogoutController {
	
	private static final Logger log = new Logger(LogoutController.class);
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request , HttpServletResponse response){
		log.info("Kullanýcý çýkýþý yapýlýyor");
		
		@SuppressWarnings("static-access")
		Authentication authentication = new SecurityContextHolder().getContext().getAuthentication();
		
		
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
			new CookieClearingLogoutHandler("remember-me").logout(request, response, authentication);
		}
		
		return new ModelAndView("redirect:/index");
	}

}
