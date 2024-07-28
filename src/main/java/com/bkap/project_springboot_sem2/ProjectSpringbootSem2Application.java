package com.bkap.project_springboot_sem2;

import com.bkap.project_springboot_sem2.services.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectSpringbootSem2Application {

	public static void main(String[] args) {
		SpringApplication.run(ProjectSpringbootSem2Application.class, args);
	}
	@Bean
	CommandLineRunner init(StorageService storageService){
		return (args) -> {
			storageService.init();
		};
	}
}
