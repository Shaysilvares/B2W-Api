package com.provab2w.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.provab2w.model.Planeta;
import com.provab2w.repositories.PlanetaRepository;

@Service
public class PlanetaServiceImpl implements PlanetaService {

	@Autowired
	private PlanetaRepository repository;

	@Override
	public Planeta adicionarPlaneta(Planeta planeta) {
		return this.repository.save(planeta);
	}

	@Override
	public List<Planeta> listarPlanetas() {
		return this.repository.findAll();
	}

	@Override
	public Planeta buscarPorId(String id) {
		return this.repository.findById(id).orElse(null);
	}

	@Override
	public void removerPlaneta(String id) {
		this.repository.deleteById(id);
	}
}