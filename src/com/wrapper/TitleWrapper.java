package com.wrapper;

import com.entities.Title;
import com.entities.Word;
import com.logger.Logger;

public class TitleWrapper {
	
	private final Logger log = new Logger(this.getClass());
	
	private Title title =  new Title();
	
	
	public Title getTitle() {
		if(title.getWords().size() == 0) {
			fillWord();
		}
		log.info("Baþlýk alýnýyor boyutu : " + title.getWords().size());
		return title;
	}
	
	public void setTitle(Title title) {
		this.title = title;
	}
	
	private void fillWord() {
		
		for(int i =0; i<20; i++) {
			title.getWords().add(new Word("","","",""));
		}
		log.info("Baþlýk boþtu doldurulup geri gönderildi.");
	}
	

}
