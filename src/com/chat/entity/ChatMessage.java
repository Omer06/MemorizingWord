package com.chat.entity;

public class ChatMessage implements TrafficMeterials {

	private String sender;
	private String repicient;
	private String message;
	
	public ChatMessage() { }

	public ChatMessage(String sender, String repicient, String message) {
		super();
		this.sender = sender;
		this.repicient = repicient;
		this.message = message;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRepicient() {
		return repicient;
	}

	public void setRepicient(String repicient) {
		this.repicient = repicient;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ChatMessage [sender=" + sender + ", repicient=" + repicient + ", message=" + message + "]";
	}
	
}
