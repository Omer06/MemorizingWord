package com.chat.server;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import com.chat.entity.ChatMessage;
import com.chat.entity.TrafficMeterials;
import com.chat.entity.UsernameListWrapper;

public class MeterialEncoder implements Encoder.Text<TrafficMeterials>{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String encode(TrafficMeterials trafficMeterials) throws EncodeException {
		if(trafficMeterials instanceof ChatMessage){
			ChatMessage chatMessage = (ChatMessage)trafficMeterials;
			return Json.createObjectBuilder()
					.add("sender", chatMessage.getSender())
					.add("message", chatMessage.getMessage())
					.add("recipient", chatMessage.getRepicient())
					.add("returnType", chatMessage.getClass().getSimpleName())
					.build().toString();
			
		}else if((trafficMeterials instanceof UsernameListWrapper)){
			UsernameListWrapper usernameListWrapper = (UsernameListWrapper)trafficMeterials;
			JsonArrayBuilder jsonArray = Json.createArrayBuilder();
			for(String username : usernameListWrapper.getUsernameList()) { jsonArray.add(username); }
			return Json.createObjectBuilder()
					.add("usernameList", jsonArray)
					.add("returnType", usernameListWrapper.getClass().getSimpleName())
					.build().toString();
		}
		return null;
	}

}
