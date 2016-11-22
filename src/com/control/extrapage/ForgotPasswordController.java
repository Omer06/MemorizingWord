package com.control.extrapage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.contact.entity.MailInformation;
import com.contact.mail.MailSender;
import com.generate.password.GeneratePassword;
import com.logger.Logger;
import com.result.handler.OperationResultHandler;
import com.service.UserService;

@Controller
@RequestMapping("/forgotpassword")
public class ForgotPasswordController {

	private static final Logger log = new Logger(ForgotPasswordController.class);

	@Autowired
	UserService userServiceImp;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	OperationResultHandler operationResultHandler;
	
	@RequestMapping("/**")
	public ModelAndView showPage(){
		log.info("Forgot password sayfasý açýlýyor");
		
		return new ModelAndView("forgotpassword" , "result" , operationResultHandler.getOperationResult());
	}

	@RequestMapping(value = "/getpassword" , method = RequestMethod.POST)
	public ModelAndView forgotPassword(String username, String email) {
		log.info("kullanýcý þifresini unutmuþ ve kullanýcý adý : " + username + " email : " + email);

		try {
			if (userServiceImp.isHasByAttributes("username",username, "email",email)) {
				String newPassword = new GeneratePassword().generate(8);
				if(userServiceImp.updatePassword(username, passwordEncoder.encode(newPassword))){
					new MailSender().sendMail(new MailInformation("kelimeezberleme30@gmail.com", "ÞÝFREMÝ UNUTTUM!", "Sayýn " + username + "\n \n Yeni þifreniz : " + newPassword, email));
					operationResultHandler.setOperationResult("Yeni þifreniz email adresinize gönderildi.");
				}else { operationResultHandler.setOperationResult("Þifreniz deðiþtirilirken , beklenmedik bir hata oluþtu."); }
			} else { operationResultHandler.setOperationResult("Böyle bi kullanýcý adý ve emaile sahip bir kullanýcý yok."); }
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Hata oluþtu");
			operationResultHandler.setOperationResult("Þifreniz deðiþtirilirken , beklenmedik bir hata oluþtu.");
		}
		return new ModelAndView("redirect:/forgotpassword/form");
	}

}
