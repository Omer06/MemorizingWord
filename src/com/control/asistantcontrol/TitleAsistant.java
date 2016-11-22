package com.control.asistantcontrol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entities.Title;
import com.entities.Word;
import com.logger.Logger;
import com.result.handler.OperationResultHandler;
import com.service.TitleService;

@Component
public class TitleAsistant {
	
	private final static Logger log = new Logger(TitleAsistant.class);
	
	@Autowired
	TitleService titleServiceImp;
	
	@Autowired
	OperationResultHandler operationResultHandler;
	
	
	/*
	 * baþlýkId ' e sahip baþlýkdaki kelimeleri günceller
	 * */
	public void updateTitle(int titleId , List<Word> wordList) throws Exception{
		Title realTitle = (Title) titleServiceImp.getEntityBy(Title.class, titleId);
		realTitle.getWords().addAll(new HashSet<Word>(wordList));
		titleServiceImp.update(realTitle);
		log.info("Gelen kelimeler " + titleId + " id ' li baþlýða eklendi.");
	}
	
	/**
	 * 'filePath' parametresi yolundaki txt dosyamýzýn her satýrýný 'marker' parametresine göre bölüyoruz.
	 * 3 deðer elimize geliyor (kelimenin adý , anlamý , hatýrlatma) bu deðiþkenler ile
	 * objemizi oluþturup listemize atýyoruz. 
	 * */
	public List<Word> getWordListFromFile(String filePath , String marker) throws Exception{
		
		List<Word> wordList = null;
		FileReader fileReader = null ;
		BufferedReader bufferedReader = null;
		
		try {
			
			wordList = new ArrayList<Word>();
			
			fileReader = new FileReader(new File(filePath));
			bufferedReader = new BufferedReader(fileReader);
			
			String line = "";
			
			while((line = bufferedReader.readLine()) != null) {
				 String name = line.split(marker)[0].trim().toLowerCase().replaceFirst(line.split(marker)[0].trim().substring(0, 1).toLowerCase(), line.split(marker)[0].trim().substring(0, 1).toUpperCase());
				 String meaning = line.split(marker)[1].trim().toLowerCase().replaceFirst(line.split(marker)[1].trim().substring(0, 1).toLowerCase(), line.split(marker)[1].trim().substring(0, 1).toUpperCase());
				 String reminding = "";
				 if( line.split(marker).length == 3 && line.split(marker)[2] != null ) {
					 	reminding = line.split(marker)[2].trim();
				 }
				 wordList.add(new Word(name, meaning, reminding , ""));
			}
		} catch(IOException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}catch(ArrayIndexOutOfBoundsException indexOutException){
			indexOutException.printStackTrace();
			throw new ArrayIndexOutOfBoundsException(indexOutException.getMessage());
		}finally {
			fileReader.close();
			bufferedReader.close();
		}
		log.info("Dosyanýn içindeki kelimeler : " + wordList);
		return wordList;
	}
	
	
	public TitleService getTitleServiceImp() {
		return titleServiceImp;
	}
	
	public void setTitleServiceImp(TitleService titleServiceImp) {
		this.titleServiceImp = titleServiceImp;
	}
	
	public OperationResultHandler getOperationResultHandler() {
		return operationResultHandler;
	}
	
	public void setOperationResultHandler(OperationResultHandler operationResultHandler) {
		this.operationResultHandler = operationResultHandler;
	}
	
	public void setOperationResult(String result) {
		this.operationResultHandler.setOperationResult(result);
	}
}
