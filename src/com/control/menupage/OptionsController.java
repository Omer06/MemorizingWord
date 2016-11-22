package com.control.menupage;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Title;
import com.logger.Logger;
import com.result.handler.OperationResultHandler;
import com.service.TitleService;
import com.service.WordService;

@Controller
@RequestMapping("options")
public class OptionsController {

	static final private Logger log = new Logger(OptionsController.class);

	@Autowired
	TitleService titleServiceImp;
	
	@Autowired
	WordService wordServiceImp;
	
	@Autowired
	OperationResultHandler operationResultHandler;

	@SuppressWarnings("static-access")
	@RequestMapping( value = "/**")
	public ModelAndView showOptionsPage() {
		
		return new ModelAndView("options", "titleList", titleServiceImp.getListBy("title",
				new SecurityContextHolder().getContext().getAuthentication().getName(), "id")).addObject("result", operationResultHandler.getOperationResult());
	}
	
	@RequestMapping("save")
	public ModelAndView save(Title title){
		log.info("Gelen ba�l�k kay�t edilecek : " + title.toString());
		
		operationResultHandler.setOperationResult("Ba�l�k ba�ar�yla kay�t edildi.");
		if(!titleServiceImp.save(title)) { operationResultHandler.setOperationResult("Ba�l��� kay�t ederken , bir hata olu�tu."); }
		return new ModelAndView("redirect:/options/");
	}
	
	@RequestMapping("rename")
	public ModelAndView rename(Integer titleId , String newName) {
		log.info(titleId + "id Li Gelen ba�l�k yeniden isimlendirilecek");
		
		operationResultHandler.setOperationResult("Ba�l�k ba�ar�yla yeniden isimlendirildi.");
		if(!titleServiceImp.rename(titleId, newName)) { operationResultHandler.setOperationResult("Ba�l��� isimlendirirken , bir hata olu�tu."); }
		
		return new ModelAndView("redirect:/options/");
	}
	
	@RequestMapping("delete")
	public ModelAndView delete (Integer titleId) {
		log.info(titleId + " id'li ba�l�k siniliyor");
		
		operationResultHandler.setOperationResult("Ba�l�k ba�ar�yla silindi.");
		
		Title title = (Title) titleServiceImp.getEntityBy(Title.class, titleId);
		if(!titleServiceImp.delete(title)) { operationResultHandler.setOperationResult("Ba�l�k silinirken , bir hata olu�tu."); }
		return new ModelAndView("redirect:/options/");
	}
	
	@RequestMapping(value = "gettitle" , method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getTitle(Integer titleId) {
		log.info(titleId + " id'li ba�l�k g�nderiliyor.");
		Map<String, Object> resultMap = new HashMap<>();
		
		try{
			Title title = (Title) titleServiceImp.getEntityBy(Title.class, titleId);
			resultMap.put("wordList", title.getWords());
			resultMap.put("result", "Silmek istediklerinizi i�aretleyip silin.");
		}
		catch(Exception e){
			resultMap.put("result", "Kelimeler �ekilirken , bir hata olu�tu.");
		}
		
		return resultMap;
		
	}
	
	@RequestMapping("deleteword")
	public ModelAndView deleteWord(HttpServletRequest request){
		
		
		String[] wordIdArray = request.getParameterValues("wordId");
		operationResultHandler.setOperationResult("Kelimeler ba�ar�yla silindi.");
		
		for (String wordId : wordIdArray) {
			log.info(wordId + " ' id li  kelime veritaban�ndan siliniyor.");
			if(!wordServiceImp.deleteById(Integer.valueOf(wordId))){ operationResultHandler.setOperationResult("Silmek istedi�iniz kelimelerden baz�lar� silinmemi� olabilir."); }
		}
		
		return new ModelAndView("redirect:/options/");
		
	}

}
