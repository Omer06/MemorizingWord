package com.generate.password;

import java.util.Random;

import com.logger.Logger;

public class GeneratePassword {
	
	private static final Logger log = new Logger(GeneratePassword.class);
	
	public String generate(int passwordLength){
		String password="";
		
		String[] letters = new String[]{"q","w","e","r","t","y","u","ý","o","p","ð","ü","a","s","d","g","h","j","k","l","z","þ","i","x","c","v","b","n","m","ö","ç",".","!","Q","W","E","R","Y","U","I","O","P","Ð","Ü","A","S","F","G","H","J","K","L","Þ","Ý","Z","X","C","V","B","N","M","Ö","Ç","1","2","3","4","5","6","7","8","9","0"};
		
		for (int i = 0; i < passwordLength; i++) {
			password += letters[new Random().nextInt(letters.length - 1)]; 
		}
		log.info("Oluþturulan yeni þifre  : " + password);
		return password;
	}

}
