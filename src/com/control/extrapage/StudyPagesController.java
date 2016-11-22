package com.control.extrapage;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.control.asistantcontrol.StudyPagesAsistant;
import com.entities.StudyOptions;
import com.entities.Title;
import com.logger.Logger;
import com.service.TitleService;

@Controller
@RequestMapping("study")
public class StudyPagesController {

	private static final Logger log = new Logger(StudyPagesController.class);

	@Autowired
	TitleService titleServiceImp;
	
	@Autowired
	StudyPagesAsistant studyPagesAsistant;
	
	@RequestMapping(value = "/studypage")
	public ModelAndView showStudyPage(HttpServletRequest request) {
		StudyOptions studyOptions = (StudyOptions) request.getSession().getAttribute("studyOptions");
		log.info("Çalýþma sayfasý açýlýyor çalýþma seçenekleri : " + studyOptions);
		
		Title title = (Title) titleServiceImp.getEntityBy(Title.class, studyOptions.getTitleId());
		log.info("Baþlangýçta - Baþlýðýn içindeki kelimeler :  " + title.getWords().toString());
		studyPagesAsistant.removeSameWords(title);
		//studyPagesAsistant.hashWords(title);
		title.getWords().sort((x1,x2) -> new Random(System.currentTimeMillis()).nextInt() * x1.hashCode() - new Random(System.currentTimeMillis()).nextInt() * x2.hashCode()); // Kelimeleri karýþýk bir þekilde diziyoruz
		log.info("Bitiþte - Baþlýðýn içindeki kelimeler :  " + title.getWords().toString());

		return new ModelAndView("studypages/" + studyOptions.getPageName()).addObject("title", title);
	}

}
