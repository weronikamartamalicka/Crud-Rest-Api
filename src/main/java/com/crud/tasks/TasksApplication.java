package com.crud.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin("*")
public class TasksApplication {

	public static void main(String[] args) {

		SpringApplication.run(TasksApplication.class, args);
	}

}
