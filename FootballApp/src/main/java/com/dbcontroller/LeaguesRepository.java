package com.dbcontroller;

import org.springframework.data.repository.CrudRepository;

import com.model.League;


public interface LeaguesRepository extends CrudRepository<League, String> {

}