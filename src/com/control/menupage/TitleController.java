package com.control.menupage;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.control.asistantcontrol.TitleAsistant;
import com.editors.TitleControlEditor;
import com.entities.Title;
import com.entities.Word;
import com.logger.Logger;
import com.validators.TitleControlValidator;
import com.wrapper.TitleWrapper;

@Controller
@RequestMapping("title")
public class TitleController {

	private final static Logger log = new Logger(TitleController.class);
	
	@Autowired
	TitleAsistant titleAsistant;
	

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Title.class, new TitleControlEditor());
		binder.setValidator(new TitleControlValidator());
	}

	@SuppressWarnings("static-access")
	@RequestMapping("/*")
	public ModelAndView showNewTitleForm() {
		log.info("Yeni baþlýk sayfasý gösteriliyor");
		
		return new ModelAndView("title", "titleObj", new TitleWrapper().getTitle()).addObject("titleList",
				titleAsistant.getTitleServiceImp().getListBy("title",
						new SecurityContextHolder().getContext().getAuthentication().getName(), "id")).addObject("result", titleAsistant.getOperationResultHandler().getOperationResult());
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("titleObj") @Valid Title title, BindingResult result) {
		log.info("Gelen baþlýk update ediliyor : " + title.toString());
		
		if (result.hasErrors()) {
			log.info("Title update edilirken bir hata oluþtu.");
			return new ModelAndView("title");
		}
		
		titleAsistant.setOperationResult("Yeni kelimeler baþarýyla eklendi.");
		try { titleAsistant.updateTitle(title.getId(), title.getWords()); } 
		catch (Exception e) { e.printStackTrace(); titleAsistant.setOperationResult("Yeni kelimeler kayýt edilirken , beklenmedik bir hata oluþtu"); }

		return new ModelAndView("redirect:/title/form");
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/updatewithfile" , method = RequestMethod.POST)
	public ModelAndView updateWithFile(MultipartFile file , String marker , Integer titleId , HttpServletRequest request) throws Exception{
		log.info("Baþlýk text dosyasý ile update ediliyor.");
		String filePath = request.getRealPath("/") + file.getOriginalFilename();
		try {
			file.transferTo(new File(filePath));
			List<Word> wordList = titleAsistant.getWordListFromFile(filePath, marker);
			titleAsistant.updateTitle(titleId, wordList);
			titleAsistant.setOperationResult("Yeni kelimeler baþarýyla eklendi.");
		} catch (Exception e) {
			e.printStackTrace();
			titleAsistant.setOperationResult("Yeni kelimeler kayýt edilirken, bir hata oluþtu. (Lütfen aþaðýdaki kullaným videosunu izleyiniz.)");
		}finally {
			if(new File(filePath).exists()) { new File(filePath).delete(); }
		}
		
		return new ModelAndView("redirect:/title/form");
	}

	

}
