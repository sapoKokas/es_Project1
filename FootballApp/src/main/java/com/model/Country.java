package com.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="countries")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
	@Id
	@GeneratedValue
	private int id;	
	private String country_id;
	private String country_name;
	private String country_logo;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry_id() {
		return this.country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

	public String getCountry_name() {
		return this.country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getCountry_logo() {
		return this.country_logo;
	}

	public void setCountry_logo(String country_logo) {
		this.country_logo = country_logo;
	}
	
}