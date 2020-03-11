package com.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
import com.model.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.dbcontroller.CountriesRepository;
import com.dbcontroller.LeaguesRepository;

@Component
@RestController
public class RestFullApi {
	@Autowired
	CountriesRepository countriesRepository; 
	
	@Autowired
	LeaguesRepository leaguesRepository;
	
	private static final Logger log = LoggerFactory.getLogger(RestFullApi.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	static RestTemplate restTemplate = new RestTemplate();
	
	
	//@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
		log.info("Fetching Country Data...");
		//List<Country> countries = getCountries();
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/country",  method = RequestMethod.GET)
	public ResponseEntity<List<Country>> Countries(){
		
		Iterable<Country> countries = countriesRepository.findAll();
		List<Country> c = new ArrayList<Country>();
		countries.forEach(e -> c.add(e));
		System.out.print(c.get(0));
		return new ResponseEntity<List<Country>>(c, HttpStatus.OK);		
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/country/leagues",  method = RequestMethod.GET)
	public ResponseEntity<List<League>> Leagues(@RequestParam("country_id")String country_id) {
		
		Iterable<League> leagues = leaguesRepository.findAll();
		List<League> c = new ArrayList<League>();
		leagues.forEach(e -> {if(e.getCountry_id().equals(country_id)) {
								c.add(e); 
							}
						});
		
		return new ResponseEntity<List<League>>(c, HttpStatus.OK);		
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/league/",  method = RequestMethod.GET)
	public ResponseEntity<List<Team>>Teams(@RequestParam("league_id")String league_id) {
		// Get teams from the league param added
		//league_id=148 -> Premier League
		ResponseEntity<List<Team>> responses = restTemplate.exchange(
				"https://apiv2.apifootball.com/?action=get_teams&league_id="+league_id+"&APIkey=c24573c08681f843b34d69519e2053c708c48273b6259cc69d0221d664dde947",
				HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Team>>(){});
		List<Team> teams = responses.getBody(); //Leagues list

		return new ResponseEntity<List<Team>>(teams, HttpStatus.OK);			
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/team/",  method = RequestMethod.GET)
	public ResponseEntity<List<Player>> Players(@RequestParam("team_key")String team_key) {
		
		
		ResponseEntity<List<Team>> responses = restTemplate.exchange(
				"https://apiv2.apifootball.com/?action=get_teams&team_id="+team_key+"&APIkey=c24573c08681f843b34d69519e2053c708c48273b6259cc69d0221d664dde947",
				HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Team>>(){});
		List<Team> team = responses.getBody();
		List<Player> players = team.get(0).getPlayers();
		
		return new ResponseEntity<List<Player>>(players, HttpStatus.OK);
	}	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/league/standings/",  method = RequestMethod.GET)
	public ResponseEntity<List<Standings>> LeagueStandings(@RequestParam("league_id")String league_id) {
		
		ResponseEntity<List<Standings>> responses = restTemplate.exchange(
				"https://apiv2.apifootball.com/?action=get_standings&league_id="+league_id+"&APIkey=c24573c08681f843b34d69519e2053c708c48273b6259cc69d0221d664dde947",
				HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Standings>>(){});
		List<Standings> stds = responses.getBody(); //Leagues list
	
		
		return new ResponseEntity<List<Standings>>(stds, HttpStatus.OK);			
	}	
}
