package com.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import com.dbcontroller.MainController;
import com.model.Country;
import com.model.League;

@Component
public class CallRestService {

    static RestTemplate restTemplate = new RestTemplate();


    public List<Country> getCountries(){
        //System.out.println("aqui");
		ResponseEntity<List<Country>> responses = restTemplate.exchange(
				"https://apiv2.apifootball.com/?action=get_countries&APIkey=c24573c08681f843b34d69519e2053c708c48273b6259cc69d0221d664dde947",
				HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Country>>(){});
		List<Country> countries = responses.getBody(); //List of all countries
		
		
        System.out.println(countries.get(0));
	    return countries;
	}
    
    public List<League> getLeagues() {

		ResponseEntity<List<League>> responses = restTemplate.exchange(
				"https://apiv2.apifootball.com/?action=get_leagues&APIkey=c24573c08681f843b34d69519e2053c708c48273b6259cc69d0221d664dde947",
				HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<League>>(){});
				List<League> leagues = responses.getBody(); //Leagues list
		
		return leagues;
    }
    
    
}
