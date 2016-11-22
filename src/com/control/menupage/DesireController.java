package com.control.menupage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.entities.Desire;
import com.logger.Logger;
import com.result.handler.OperationResultHandler;
import com.service.EntityService;

@Controller
@RequestMapping("desire")
public class DesireController {

	private static final Logger log = new Logger(DesireController.class);

	@Autowired
	EntityService entityServiceImp;

	@Autowired
	OperationResultHandler operationResultHandler;

	@RequestMapping("/**")
	public ModelAndView showDesirePage() {
		log.info("showLoginPage : Arzu/Talep sayfas� g�steriliyor");

		return new ModelAndView("desire", "desireObj", new Desire()).addObject("result", operationResultHandler.getOperationResult());
	}

	@RequestMapping("save")
	public ModelAndView save(@ModelAttribute("desireObj") Desire desire) {
		log.info("Talep al�n�yor : " + desire);
		try {
			entityServiceImp.save(desire);
			operationResultHandler.setOperationResult("Talebiniz ba�ar�yla al�nm��t�r.");
		} catch (Exception e) {
			e.printStackTrace();
			operationResultHandler.setOperationResult("Talebiniz al�n�rken beklenmedik bi hata olu�tu.");
		}
		return new ModelAndView("redirect:/desire/form");
	}

}
