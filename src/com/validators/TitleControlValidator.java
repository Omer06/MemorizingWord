package com.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.entities.Title;
import com.entities.Word;
import com.logger.Logger;

public class TitleControlValidator implements Validator{
	
	private static final Logger log = new Logger(TitleControlValidator.class);

	@Override
	public boolean supports(Class<?> paramClass) {
		// TODO Auto-generated method stub
		return Title.class.equals(paramClass);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Title title = (Title) object;
		
		for(Word word : title.getWords()) {
			if(word.getName().equals("") || word.getMeaning().equals("") || title.getId() == 0) {
				errors.reject("updateTitle.field.error");
				log.info("Validate iþlemi yapýlýrken , geçersiz bir alan tespit edildi");
				break;
			}
		}
	}

}
