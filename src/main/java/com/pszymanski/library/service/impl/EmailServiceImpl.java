package com.pszymanski.library.service.impl;

import com.pszymanski.library.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    @Qualifier("JavaEmailConfig")
    public JavaMailSender emailSender;

    @Autowired
    @Qualifier("templateEngine")
    public TemplateEngine templateEngine;

    @Override
    public String build(List<?> list, Object object, String mailTemplate) {
        Context context = new Context();
        context.setVariable("list", list);
        context.setVariable("object", object);
        return templateEngine.process(mailTemplate, context);
    }

    @Override
    public void sendEmail(String to, String subject, List<?> list, Object object, String template) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            String content = build(list, object, template);
            messageHelper.setText(content, true);
        };
        try {
            emailSender.send(messagePreparator);
        } catch (MailException e) {
        }
    }
}

