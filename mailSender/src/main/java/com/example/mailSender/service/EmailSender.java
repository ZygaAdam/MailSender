package com.example.mailSender.service;

public interface EmailSender {
	
	void sendEmail(String to, String subject, String content);

}
