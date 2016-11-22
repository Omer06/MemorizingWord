package com.chat.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import com.chat.entity.ChatMessage;
import com.chat.entity.TrafficMeterials;
import com.chat.entity.UsernameListWrapper;
import com.logger.Logger;

@ServerEndpoint(value = "/chatserver" , encoders = MeterialEncoder.class , decoders = MeterialDecoder.class)
public class ChatServer {
	
	static final private Logger log = new Logger(ChatServer.class);
	
	static List<Session> userList = Collections.synchronizedList(new ArrayList<Session>());
	
	@OnMessage
	public void handleMessage(Session user , TrafficMeterials trafficMeterials) throws IOException, EncodeException, InterruptedException {
		log.info("Bir mesaj tespit edildi.");
		ChatMessage inComingMessage = (ChatMessage)trafficMeterials;
		if(user.getUserProperties().get("username") == null) {
			log.info("Kullanýcýnýn ilk mesajý , kullanýcý kayýt edildi : " + inComingMessage.getMessage());
			registerUser(user, inComingMessage.getMessage()); // kullanýcýn ismini gelen ilk mesaj yapýyoruz
			sendToUsers(new ChatMessage("Sistem", "Genel", user.getUserProperties().get("username") + " isimli kullanýcý aramýza katýldý."));
			sendToUsers(new UsernameListWrapper(getUsernameList()));// kullanýcýyý update ediyoruz
		}
		else{
			log.info("Server mesajý alýcýlara gönderiyor.");
			if(inComingMessage.getRepicient().equals("Genel")){ sendToUsers(new ChatMessage((String) user.getUserProperties().get("username") , inComingMessage.getRepicient() , inComingMessage.getMessage())); }
			else 										      { getUserByUsername(inComingMessage.getRepicient() , user).getAsyncRemote().sendObject(new ChatMessage((String) user.getUserProperties().get("username") , inComingMessage.getRepicient() , inComingMessage.getMessage())); }
		}
		
		
	}
	
	@OnClose
	public void handleClose(Session user) throws IOException, EncodeException {
		log.info((String) user.getUserProperties().get("username") + " kullanýcý sohpedten çýktý.");
		userList.remove(user);
		sendToUsers(new UsernameListWrapper(getUsernameList()));
	}
	
	@OnError
	public void handleError(Throwable t){
		log.error("Chat serverde bir hata oluþtu.");
		t.printStackTrace();
	}
	
	private void sendToUsers(TrafficMeterials trafficMeterials) throws IOException, EncodeException{
		for (Session currentUser : userList) { currentUser.getAsyncRemote().sendObject(trafficMeterials); }
	}
	
	private List<String> getUsernameList(){
		List<String> usernameList = new ArrayList<String>();
		for (Session currentUser : userList) { usernameList.add(currentUser.getUserProperties().get("username").toString()); }
		return usernameList;
	}
	/*
	 * getUserByUsername(username , seekingUser) with
	 * if it will not find the username of session
	 * return seekingUser.!
	 */
	private Session getUserByUsername(String username , Session seekingUser) {
		for (Session user : userList) {
			if(username.equals((String) user.getUserProperties().get("username"))){
				return user;
			}
		}
		return seekingUser;
	}
	
	private void registerUser(Session user , String username){
		user.getUserProperties().put("username", username);
		userList.add(user);
	}

}
