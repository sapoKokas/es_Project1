package com.dbcontroller;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;


import com.model.Country;

@Repository
public interface CountriesRepository extends CrudRepository<Country, String>{
	
}