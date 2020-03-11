package com.dbcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.api.RestFullApi;
import com.client.CallRestService;
import com.model.Country;
import com.model.*;

@Component
@ComponentScan({"com.client"})
public class MainController {
	
	@Autowired
	CallRestService restService;

	@Autowired
	CountriesRepository countriesRepository; 
	
	@Autowired
	LeaguesRepository leaguesRepository; 
	
	public void addCountries () {
		List<Country> countries = restService.getCountries();
		for(int i=0; i<countries.size();i++) {
			if(countries.get(i)!=null) {
				countriesRepository.save(countries.get(i));
			}
		}
		System.out.println("Countries added");
	}
	
	public void addLeagues() {
		List<League> leagues = restService.getLeagues();
		for(int i=0; i<leagues.size();i++) {
			if(leagues.get(i)!=null) {
				leaguesRepository.save(leagues.get(i));
			}	
		}
		
		System.out.println("Leagues added");
	}
}
