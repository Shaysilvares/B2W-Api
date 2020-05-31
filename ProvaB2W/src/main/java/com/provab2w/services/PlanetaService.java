package com.provab2w.services;

import java.util.List;

import com.provab2w.model.Planeta;

public interface PlanetaService {

	Planeta adicionarPlaneta(Planeta planeta);
	
	List<Planeta> listarPlanetas();
	
	Planeta buscarPorId(String id);
	
	void removerPlaneta(String id);
}