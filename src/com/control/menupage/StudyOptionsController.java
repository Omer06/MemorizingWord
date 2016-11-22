package com.control.menupage;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.control.asistantcontrol.StudyOptionsAsistant;
import com.entities.StudyOptions;
import com.logger.Logger;

@Controller
@RequestMapping("study")
public class StudyOptionsController {
	
	private static final Logger log = new Logger(StudyOptionsController.class);
	
	@Autowired
	StudyOptionsAsistant studyOptionsAsistant;
	
	@RequestMapping("/**")
	public ModelAndView studyOptions() {
		log.info("çalýþma seçenekleri sayfasý gösteriliyor.");
		
		return new ModelAndView("studyoptions").addObject("titleMap", studyOptionsAsistant.getTitleMapOfTheUser()).addObject("styleMap", studyOptionsAsistant.getStyleMap());
	}
	
	@RequestMapping(value = "redirecter" , method = RequestMethod.POST)
	public ModelAndView goToStudyPage(StudyOptions studyOptions , HttpServletRequest request){
		request.getSession().setAttribute("studyOptions", studyOptions);
		return new ModelAndView("redirect:/study/studypage");
	}

}
