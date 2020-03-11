package com.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@Entity // This tells Hibernate to make a table out of this class
//@Table(name="Teams")
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {
	private int id;
	private int team_key;
	private String team_name;
	private String team_badge;
	private List<Player> players;
	public int getTeam_key() {
		return team_key;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTeam_key(int team_key) {
		this.team_key = team_key;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	public String getTeam_badge() {
		return team_badge;
	}
	public void setTeam_badge(String team_badge) {
		this.team_badge = team_badge;
	}
	@Override
	public String toString() {
		return "Team [team_key=" + team_key + ", team_name=" + team_name + ", team_badge=" + team_badge + "]";
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	

}