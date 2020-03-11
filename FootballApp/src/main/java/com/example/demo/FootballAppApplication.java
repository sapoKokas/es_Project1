package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.api.*;
import com.client.CallRestService;
import com.dbcontroller.MainController;

@SpringBootApplication
@ComponentScan({"com.dbcontroller", "com.api"})
@EnableJpaRepositories({"com.dbcontroller"})
@EntityScan({"com.model"})	
public class FootballAppApplication {

	public static void main(String[] args) {
		//System.out.print("\n\n\n\n\n\n\n----------------------------");
		ConfigurableApplicationContext context = SpringApplication.run(FootballAppApplication.class, args);
		
		MainController mainController = context.getBean(MainController.class);
		//CallRestService rest = context.getBean(CallRestService.class);
		mainController.addCountries();
		mainController.addLeagues();
	}

}
