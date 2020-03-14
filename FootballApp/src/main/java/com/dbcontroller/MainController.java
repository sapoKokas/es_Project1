package com.dbcontroller;

import java.util.List;
import java.util.Optional;
import com.KafkaCOntroler.KafkaProducer;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.client.CallRestService;
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

	@Autowired
	GamesRepository gamesRepository;

	@Autowired
	KafkaProducer producer;

	public String producers(String data){
		producer.send(data);
		
		return "Done";
	}

	public void addCountries () {
		List<Country> countries = restService.getCountries();
		for(int i=0; i<countries.size();i++) {
			if(countries.get(i)!=null) {
				countriesRepository.save(countries.get(i));
			}
		}
		producers("Countries added")
;		System.out.println("Countries added");
	}
	
	public void addLeagues() {
		List<League> leagues = restService.getLeagues();
		for(int i=0; i<leagues.size();i++) {
			if(leagues.get(i)!=null) {
				leaguesRepository.save(leagues.get(i));
			}	
		}
		producers("Leagues added");
		System.out.println("Leagues added");
	}


	@Scheduled(fixedRate=60000)
	public void addGames() {
		List<Games> games= restService.GetGames();
		for(int i=0; i<games.size();i++) {
			if(games.get(i)!=null) {
				gamesRepository.save(games.get(i));
				}
			}	
		
		producers("Games added");
		System.out.println("Games added");
	}
}
