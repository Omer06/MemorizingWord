package com.control.asistantcontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.entities.Title;
import com.logger.Logger;
import com.service.TitleService;

@Component
public class StudyOptionsAsistant {
	
	private static final Logger log = new Logger(StudyOptionsAsistant.class);

	@Autowired
	TitleService titleServiceImp;
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getTitleMapOfTheUser() {
		
		@SuppressWarnings("static-access")
		ArrayList<Title> titleList = (ArrayList<Title>) titleServiceImp.getListBy("title",
				new SecurityContextHolder().getContext().getAuthentication().getName(), "id");
		Map<String, Object> titleMap = new HashMap<String, Object>();

		for (Title title : titleList) {
			titleMap.put(title.getName(), title.getId());
			log.info("" + title.getUser().getUsername() + " kullanýcýsýnýn baþlýklarýnýn isimleri : " + title.getName());
		}
		return titleMap;
	}
	
	public Map<String, Object> getStyleMap() {
		Map<String, Object> styleMap = new HashMap<String,Object>();
		
		styleMap.put("YABANCI => (YEREL)", "localtranslation");
		styleMap.put("YEREL => (YABANCI)", "foreigntranslation");
		styleMap.put("YABANCI => YEREL", "memorize");
		
		return styleMap;
	}

}
