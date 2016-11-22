package com.contact.entity;

public class MailInformation {

	private String sender;
	private String subject;
	private String text;
	private String recipientMail;

	public MailInformation() { }

	public MailInformation(String sender, String subject, String text,
			String recipientMail) {
		super();
		this.sender = sender;
		this.subject = subject;
		this.text = text;
		this.recipientMail = recipientMail;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRecipientMail() {
		return recipientMail;
	}

	public void setRecipientMail(String recipientMail) {
		this.recipientMail = recipientMail;
	}

}
