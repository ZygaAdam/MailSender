package com.example.mailSender.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.mailSender.ThemailText;
import com.example.mailSender.service.EmailSender;

@Controller
public class EmailController {

	@GetMapping("/")
	public String test(Model theModel) {

		return "start";
	}

	private final EmailSender emailSender;
	private final TemplateEngine templateEngine;

	@Autowired
	public EmailController(EmailSender emailSender, TemplateEngine templateEngine) {

		this.emailSender = emailSender;
		this.templateEngine = templateEngine;
	}

	@GetMapping("/")
	public String send(Model model) {

		ThemailText theMailText = new ThemailText();
		model.addAttribute("mail", theMailText);
		return "/start";
	}

	@PostMapping("/send")
	public String emailSend( @ModelAttribute("mail") ThemailText theMailText) {
		
		Context context = new Context();
		context.setVariable("header", "Nowy artykuł");
		context.setVariable("title", "szablon i wysyłanie - test");
		context.setVariable("description", theMailText.getText());
		String body = templateEngine.process("template", context);
		emailSender.sendEmail("adam.zyga@gmail.com", "Newsletter Test", body);
		
		return "/start";
	}

}
