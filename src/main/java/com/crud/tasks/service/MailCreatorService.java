package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MailCreatorService {
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    DbService dbService;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("preview", "New task created on your account!");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("good_bye", "Regards");
        context.setVariable("company_details", adminConfig.getCompanyDetails());
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        context.setVariable("your_tasks", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildScheduledCardEmail(String message) {
        List<String> tasks;
        tasks = dbService.getAllTasks().stream()
                .map(task -> task.getTitle())
                .collect(Collectors.toList());

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://trello.com/login");
        context.setVariable("button", "Check tasks for today!");
        context.setVariable("preview", "Let's see what you could do today...");
        context.setVariable("good_bye", "Regards");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_details", adminConfig.getCompanyDetails());
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        context.setVariable("your_tasks", tasks);
        return templateEngine.process("mail/everyday-tasks", context);
    }
}
