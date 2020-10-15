package com.example.mailSender;
public class MailText {
	
	private String text;
	private String name;
	private String Subject;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	@Override
	public String toString() {
		return "MailText{" +
				"text='" + text + '\'' +
				'}';
	}
}