package com.api;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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
import java.util.Date;
import com.dbcontroller.CountriesRepository;

@Component
@RestController
public class RestFullApi {
	

	@Autowired
	CountriesRepository countriesRepository; 
	
	private static final Logger log = LoggerFactory.getLogger(RestFullApi.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	static RestTemplate restTemplate = new RestTemplate();
	
	
	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
		log.info("Fetching Country Data...");
		//List<Country> countries = getCountries();
	}
	

	public List<Country> getCountries(){
		ResponseEntity<List<Country>> responses = restTemplate.exchange(
				"https://apiv2.apifootball.com/?action=get_countries&APIkey=25dcebbafa8a6d6b4810bc2d2ffd3d48c5abda234d67d92ed64c68e35b5edcb5",
				HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Country>>(){});
		List<Country> countries = responses.getBody(); //Leagues list
		
		for(int i=0; i<countries.size();i++) {
			if(countries.get(i)!=null) {
				countriesRepository.save(countries.get(i));
			}
		}
		return countries;
	}
	
	
	@RequestMapping(value="/country",  method = RequestMethod.GET)
	public String Countries () {		
		List<Country> countries = getCountries(); //Leagues list
		
		
		String html = "<table>\n" + 
				"  <tr>\n" + 
				"    <th>ContryKey</th>\n" + 
				"    <th>ContryName</th>\n" + 
				"    <th>Logo</th>\n" + 
				"  </tr>\n" ;
				for(int i=0;i<countries.size();i++) {
					html +=  "<tr>\n" +
							"<td>" + countries.get(i).getCountry_id() + "</td>" +
							"<td> <a href=http://localhost:8080/country/leagues/?country_id="+ countries.get(i).getCountry_id() +">" + countries.get(i).getCountry_name() + "</a></td>" +
							"<td> <img src='" + countries.get(i).getCountry_logo() + "'></td>" +
							"</tr>\n";	
				}
				html += "</table>";
				
		
		return html;	
	}
	
	
	@RequestMapping(value="/country/leagues",  method = RequestMethod.GET)
	public String Leagues (@RequestParam("country_id")String country_id) {

		ResponseEntity<List<League>> responses = restTemplate.exchange(
				"https://apiv2.apifootball.com/?action=get_leagues&country_id="+country_id+"&APIkey=25dcebbafa8a6d6b4810bc2d2ffd3d48c5abda234d67d92ed64c68e35b5edcb5",
				HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<League>>(){});
		List<League> leagues = responses.getBody(); //Leagues list
		
		
		String html = "<table>\n" + 
				"  <tr>\n" + 
				"    <th>ContryKey</th>\n" + 
				"    <th>ContryName</th>\n" + 
				"    <th>LeagueId</th>\n" + 
				"    <th>LeagueName</th>\n" + 
				"  </tr>\n" ;
				for(int i=0;i<leagues.size();i++) {
					html +=  "<tr>\n" +
							"<td>" + leagues.get(i).getCountry_id() + "</td>" +
							"<td> " + leagues.get(i).getCountry_name() + "</td>" +
							"<td> " + leagues.get(i).getLeague_id() + "</td>" +
							
							"<td> <a href=http://localhost:8080/league/?league_id="+ leagues.get(i).getLeague_id() +">" + leagues.get(i).getLeague_name() + "</a></td>" +
							"</tr>\n";	
				}
				html += "</table>";
				
		
		return html;	
	}
	
	@RequestMapping(value="/league/",  method = RequestMethod.GET)
	public String Teams (@RequestParam("league_id")String league_id) {
		// Get All Leagues
		//league_id=148 -> Premier League
		ResponseEntity<List<Team>> responses = restTemplate.exchange(
				"https://apiv2.apifootball.com/?action=get_teams&league_id="+league_id+"&APIkey=25dcebbafa8a6d6b4810bc2d2ffd3d48c5abda234d67d92ed64c68e35b5edcb5",
				HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Team>>(){});
		List<Team> leagues = responses.getBody(); //Leagues list
		
		
		String html = "<table>\n" + 
				"  <tr>\n" + 
				"    <th>TeamKey</th>\n" + 
				"    <th>TeamName</th>\n" + 
				"    <th>Logo</th>\n" + 
				"  </tr>\n" ;
				for(int i=0;i<leagues.size();i++) {
					html += "<tr>\n" +
								"<td>" + leagues.get(i).getTeam_key() + "</td>" +
								"<td> <a href=http://localhost:8080/teams/?team_key="+ leagues.get(i).getTeam_key() +">" + leagues.get(i).getTeam_name() + "</a></td>" +
								"<td> <img src='" + leagues.get(i).getTeam_badge() + "'></td>" +
							"</tr>\n";	
				}
				html += "</table>";
				
		
		return html;	
	}

	@RequestMapping(value="/teams/",  method = RequestMethod.GET)
	public String Players(@RequestParam("team_key")String team_key) {
		ResponseEntity<List<Team>> responses = restTemplate.exchange(
				"https://apiv2.apifootball.com/?action=get_teams&team_id="+team_key+"&APIkey=25dcebbafa8a6d6b4810bc2d2ffd3d48c5abda234d67d92ed64c68e35b5edcb5",
				HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Team>>(){});
		List<Team> team = responses.getBody();
		List<Player> players = team.get(0).getPlayers();
		
		//System.out.print(team.get(0).toString());
		String html = "<table border='1' style='width:100%'>" + 
				"  <tr>\n" + 
				"    <th>Player_name</th>\n" + 
				"    <th>Player_number</th>\n" + 
				"    <th>Player_country</th>\n" + 
				"    <th>Player_type</th>\n" + 
				"	 <th>Player_age</th>\\n" +	
				"    <th>Player_match_played</th>\n" + 
				"    <th>Player_goals</th>\n" + 
				"    <th>Player_yellow_cards</th>\n" + 
				"    <th>Player_red_cards</th>\n" + 
				"  </tr>\n" ;
				for(int i=0;i<players.size();i++) {
					html += "<tr>\n" +
								"<td>" + players.get(i).getPlayer_name() + "</td>" +
								"<td>" + players.get(i).getPlayer_number() + "</td>" +
								"<td>" + players.get(i).getPlayer_country() + "</td>" +
								"<td>" + players.get(i).getPlayer_type() + "</td>" +
								"<td>" + players.get(i).getPlayer_age() + "</td>" +
								"<td>" + players.get(i).getPlayer_match_played() + "</td>" +
								"<td>" + players.get(i).getPlayer_goals() + "</td>" +
								"<td>" + players.get(i).getPlayer_yellow_cards() + "</td>" +
								"<td>" + players.get(i).getPlayer_red_cards() + "</td>" +
							"</tr>\n";	
				}
				html += "</table>";
				
		
		return html;
	}
	
	@RequestMapping(value="/teams/standings/",  method = RequestMethod.GET)
	public String LeagueStandings(@RequestParam("league_id")String league_id) {
		ResponseEntity<List<Standings>> responses = restTemplate.exchange(
				"https://apiv2.apifootball.com/?action=get_standings&league_id="+league_id+"&APIkey=25dcebbafa8a6d6b4810bc2d2ffd3d48c5abda234d67d92ed64c68e35b5edcb5",
				HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Standings>>(){});
		List<Standings> stds = responses.getBody(); //Leagues list
		
		String html = "<table border='1' style='width:100%'>" + 
				"  <tr>\n" + 
				"    <th>Team_name</th>\n" + 
				"    <th>Overall_league_position</th>\n" + 
				"    <th>Overall_league_payed</th>\n" + 
				"    <th>Overall_league_w</th>\n" + 
				"    <th>Overall_league_D</th>\n" + 
				"    <th>Overall_league_L</th>\n" + 
				"    <th>Overall_league_position</th>\n" +
				"    <th>Overall_league_PTS</th>\n" +
				"  </tr>\n" ;
				for(int i=0;i<stds.size();i++) {
					html += "<tr>\n" +
								"<td>" + stds.get(i).getTeam_name() + "</td>" +
								"<td>" + stds.get(i).getOverall_league_position() + "</td>" +
								"<td>" + stds.get(i).getOverall_league_payed() + "</td>" +
								"<td>" + stds.get(i).getOverall_league_w() + "</td>" +
								"<td>" + stds.get(i).getOverall_league_D() + "</td>" +
								"<td>" + stds.get(i).getOverall_league_L()+ "</td>" +
								"<td>" + stds.get(i).getOverall_league_position()+ "</td>" +
								"<td>" + stds.get(i).getOverall_league_PTS()+ "</td>" +
							"</tr>\n";	
				}
				html += "</table>";
				
		return html;		
	}
	
}
