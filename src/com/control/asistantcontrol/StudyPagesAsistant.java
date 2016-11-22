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
	 * Baþlýðýn içindeki kelimelerde anlamlarý ayný olan varsa
	 * Son ekleneni siler HashSet Sýnýfý ile , word sýnýfýndaki
	 * equalsto , hashcode methodunu bu yönde ezdik.
	 * */
	public void removeSameWords(Title title){
		log.info("Baþlýðýn içindeki ayný kelimeler tespit edilip çýkarýlýyor.");
		HashSet<Word> helperSet = new HashSet<Word>(title.getWords());
		title.getWords().clear();
		title.getWords().addAll(helperSet);
	}
	
	
	/*
	 * Baþlýðýn içinde kaç tane kelime varsa bunlarý karýþtýrýr.
	 * Helper list ile bunlarýn bir örneðini alýp , baþlýðýn içindeki
	 * kelimeleri siler. Bu örnek listedeki kelimeleri karýþýk bir þekilde 
	 * asýl listenin içinde doldurur
	 * */
	public void hashWords(Title title){
		log.info("Baþlýðýn içindeki kelimeler karýþtýrýlýyor.");
		List<Word> helperList = new ArrayList<>(title.getWords()); // Baþlýkdaki kelimeleri klonluyoruz
		title.getWords().clear();
		while(helperList.size() != 0){
			int index = new Random().nextInt(helperList.size());
			title.getWords().add(helperList.get(index));
			helperList.remove(index);
		}
	}
}
