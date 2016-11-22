package com.control.asistantcontrol;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.entities.Title;
import com.entities.Word;
import com.logger.Logger;

@Component
public class StudyPagesAsistant {
	
	private static final Logger log = new Logger(StudyPagesAsistant.class);

	/*
	 * Ba�l���n i�indeki kelimelerde anlamlar� ayn� olan varsa
	 * Son ekleneni siler HashSet S�n�f� ile , word s�n�f�ndaki
	 * equalsto , hashcode methodunu bu y�nde ezdik.
	 * */
	public void removeSameWords(Title title){
		log.info("Ba�l���n i�indeki ayn� kelimeler tespit edilip ��kar�l�yor.");
		HashSet<Word> helperSet = new HashSet<Word>(title.getWords());
		title.getWords().clear();
		title.getWords().addAll(helperSet);
	}
	
	
	/*
	 * Ba�l���n i�inde ka� tane kelime varsa bunlar� kar��t�r�r.
	 * Helper list ile bunlar�n bir �rne�ini al�p , ba�l���n i�indeki
	 * kelimeleri siler. Bu �rnek listedeki kelimeleri kar���k bir �ekilde 
	 * as�l listenin i�inde doldurur
	 * */
	public void hashWords(Title title){
		log.info("Ba�l���n i�indeki kelimeler kar��t�r�l�yor.");
		List<Word> helperList = new ArrayList<>(title.getWords()); // Ba�l�kdaki kelimeleri klonluyoruz
		title.getWords().clear();
		while(helperList.size() != 0){
			int index = new Random().nextInt(helperList.size());
			title.getWords().add(helperList.get(index));
			helperList.remove(index);
		}
	}
}
