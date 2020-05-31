package com.provab2w.test;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.provab2w.model.Planeta;
import com.provab2w.repositories.PlanetaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PlanetaFuncionalidadesTest {

	Planeta planeta, novoPlaneta;

	@Autowired
	PlanetaRepository repository;

	@Test
	public void CriarMiniMundo() {
		planeta = new Planeta("1", "Terra", "Tropical", "Populoso", 1);
		this.repository.save(planeta);
	}

	@Test
	public void AdicionarPlaneta() {
		novoPlaneta = new Planeta("2", "Marte", "Seco", "Árido", 1);
		this.repository.save(novoPlaneta);
		Assert.assertFalse(novoPlaneta.getId().isEmpty());

	}

	@Test
	public void buscarTodos() {
		List<Planeta> planetas = repository.findAll();
		Assert.assertFalse(planetas.isEmpty());
	}

	@Test
	public void buscarPorId() {
		Optional<Planeta> planetas = repository.findById("1");
		Assert.assertNotNull(planetas);
	}

	@Test
	public void DeletarPorId() {
		List<Planeta> planetas = repository.findAll();
		Assert.assertNotNull(planetas);

		this.repository.delete(planetas.get(0));
		Assert.assertFalse(planetas.isEmpty());
	}
}
