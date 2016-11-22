package com.editors;

import java.beans.PropertyEditorSupport;

import com.entities.Title;
import com.entities.Word;
import com.logger.Logger;

public class TitleControlEditor extends PropertyEditorSupport{

	private static final Logger log = new Logger(TitleControlEditor.class);
	
	@Override
	public void setValue(Object value) {
		
		Title title = (Title)value;
		
		for(Word word : title.getWords()){
				word.setName(word.getName().trim().toLowerCase().replaceFirst(word.getName().substring(0, 1).toLowerCase(), word.getName().substring(0, 1).toUpperCase()));
				word.setMeaning(word.getMeaning().trim() .toLowerCase().replaceFirst(word.getMeaning().substring(0, 1).toLowerCase(), word.getMeaning().substring(0, 1).toUpperCase()));
		}
		log.info("Editorden geçen titlenin yeni hali : " + title);
			
		super.setValue(title);
	}

}
