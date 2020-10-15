package com.example.mailSender.Controller;
import com.example.mailSender.MailText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.example.mailSender.service.EmailSender;
@Controller
public class EmailController {
	private final EmailSender emailSender;
	private final TemplateEngine templateEngine;
	@Autowired
	public EmailController(EmailSender emailSender, TemplateEngine templateEngine) {
		this.emailSender = emailSender;
		this.templateEngine = templateEngine;
	}
	

	
	
	@GetMapping("/")
	public String send(Model model) {
		MailText mailText = new MailText();
		//mailText.setText("");
		model.addAttribute("mail", mailText);
		model.addAttribute("name", mailText);
		model.addAttribute("subject", mailText);
		return "/start";
	}
	@PostMapping("/send")
	public String emailSend( @ModelAttribute("mail") MailText mailText) {
		System.out.print(mailText);
		Context context = new Context();
		context.setVariable("header", "Wiadomość od " + mailText.getName());
		context.setVariable("title", mailText.getSubject());
		context.setVariable("description", mailText.getText());
		String body = templateEngine.process("template", context);
		emailSender.sendEmail("", "Newsletter Test", body);
		return "/start";
	}
}