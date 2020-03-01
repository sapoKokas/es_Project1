package com.dbcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.api.RestFullApi;
import com.model.Country;


@Component
@ComponentScan({"com.api"})
@EnableScheduling
public class MainController {
	
	@Autowired
	RestFullApi restService;

	@Autowired
	CountriesRepository countriesRepository; 
	
	public void addCountries () {
		List<Country> countries = restService.getCountries();
		for(int i=0; i<countries.size();i++) {
			if(countries.get(i)!=null) {
				countriesRepository.save(countries.get(i));
			}
		}
		System.out.println("Countries added");

	}
}
