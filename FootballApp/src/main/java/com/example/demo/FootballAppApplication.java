package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


import com.dbcontroller.MainController;
@SpringBootApplication(scanBasePackages={"com.dbcontroller","com.api"})
@EnableScheduling
@ComponentScan({"com.dbcontroller","com.api"})
@EnableJpaRepositories({"com.dbcontroller"})
@EntityScan({"com.model"})
public class FootballAppApplication {

	public static void main(String[] args) {
		//ConfigurableApplicationContext context = 
		SpringApplication.run(FootballAppApplication.class, args);
//		
//		MainController mainController = context.getBean(MainController.class);
//		mainController.addCountries();
		
	}

}
