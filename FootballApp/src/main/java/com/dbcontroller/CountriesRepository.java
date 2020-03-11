package com.dbcontroller;

import org.springframework.data.repository.CrudRepository;

import com.model.Country;

public interface CountriesRepository extends CrudRepository<Country, String> {

}
