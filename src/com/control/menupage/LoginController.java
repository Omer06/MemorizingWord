package com.control.menupage;


import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.logger.Logger;

@Controller
@RequestMapping("login")
public class LoginController {
	
	private static final Logger log = new Logger(LoginController.class);
	

	@RequestMapping("/**")
	public ModelAndView showLoginPage() {
		log.info("Giriþ sayfasý gösteriliyor");
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/error")
	public ModelAndView loginError(HttpServletRequest request) {
		log.info("Kullanýcý giriþ yaparken hata oluþtu.");
		return new ModelAndView("login" , "result" , "*Kullanýcý adý yada þifreniz hatalý.");
	}
	
}
