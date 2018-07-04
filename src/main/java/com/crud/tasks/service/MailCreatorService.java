package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        ArrayList<String> functionality = new ArrayList<>();
        functionality.add("You can manage your task");
        functionality.add("Provides connection with Trello account");
        functionality.add("Application allows sending task to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("goodbye_message", "Goodbye. Until the next time.");
        context.setVariable("company_name", "${info.company.name}");
        context.setVariable("company_goal", "${info.company.goal}");
        context.setVariable("company_phone", "${info.company.phone}");
        context.setVariable("company_email" , "${info.company.email}");
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
