package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.api.RestFullApi;

@SpringBootApplication
@EnableScheduling
@ComponentScan({"com.api"})
public class FootballAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballAppApplication.class, args);
	}

}
