package com.provab2w.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.provab2w.model.Planeta;

public interface PlanetaRepository extends MongoRepository<Planeta, String>{

}