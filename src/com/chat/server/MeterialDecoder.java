package com.chat.server;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.chat.entity.ChatMessage;
import com.chat.entity.TrafficMeterials;


public class MeterialDecoder implements Decoder.Text<TrafficMeterials>{

	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TrafficMeterials decode(String message) throws DecodeException {
		JsonObject jsonObject = Json.createReader(new StringReader(message)).readObject();
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setRepicient(jsonObject.getString("recipient"));
		chatMessage.setMessage(jsonObject.getString("message"));
		
		return chatMessage;
	}

	@Override
	public boolean willDecode(String message) {
		boolean appropriate = true;
		try { Json.createReader(new StringReader(message)).readObject(); } 
		catch (Exception e) { appropriate = false;  e.printStackTrace();  }
		return appropriate;
	}

}
