package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmailScheduler {

    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;
    private static final String SUBJECT = "Tasks: Once a day email";

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {

        long size = taskRepository.count();

        Mail mail = Mail.builder()
                        .mailTo(adminConfig.getAdminMail())
                        .subject(SUBJECT)
                        .message(size != 1 ? "Currently in database you got: " + size + " tasks"
                                : "Currently in database you got: " + size + " task")
                        .build();

        simpleEmailService.send(mail);
    }
}
