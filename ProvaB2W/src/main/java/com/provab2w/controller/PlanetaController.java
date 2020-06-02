package com.provab2w.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.provab2w.model.Planet;
import com.provab2w.model.Planeta;
import com.provab2w.responses.Response;
import com.provab2w.services.PlanetaService;

@RestController
@RequestMapping("/api")
public class PlanetaController {

	@Autowired
	private PlanetaService service;

	@Autowired
	private RestTemplate template = new RestTemplate();

	@GetMapping
	public ResponseEntity<Response<List<Planeta>>> buscarTodosPlanetas() {
		return ResponseEntity.ok(new Response<List<Planeta>>(this.service.listarPlanetas()));
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<Planeta>> buscarPlanetaPorId(@PathVariable(name = "id") String id) {
		Planeta planeta = this.service.buscarPorId(id);
		if(planeta == null) {
			return new ResponseEntity<Response<Planeta>>(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(new Response<Planeta>(planeta));
	}

	@PostMapping
	public ResponseEntity<Response<Planeta>> adicionarPlaneta(@Valid @RequestBody Planeta planeta,
			BindingResult result) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<String>();
			for (ObjectError erro : result.getAllErrors()) {
				erros.add(erro.getDefaultMessage());
				return ResponseEntity.badRequest().body(new Response<Planeta>(erros));
			}
		}
		return ResponseEntity.ok(new Response<Planeta>(this.service.adicionarPlaneta(planeta)));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Response<Integer>> removerPlaneta(@PathVariable(name = "id") String id) {
		this.service.removerPlaneta(id);
		return ResponseEntity.ok(new Response<Integer>(1));
	}

	@GetMapping("/planets")
	public void buscarPlanetasApi() throws JsonMappingException, JsonProcessingException {
		String url = "https://swapi.dev/api/planets/";

		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> response = template.getForObject(url, Map.class);

		@SuppressWarnings("unchecked")
		List<Object> results = (List<Object>) response.get("results");
		List<Planet> planets = mapper.convertValue(results, new TypeReference<List<Planet>>() {
		});

		for (Planet planet : planets) {
			Planeta planeta = new Planeta();

			planeta.setNome(planet.getName());
			planeta.setClima(planet.getClimate());
			planeta.setTerreno(planet.getTerrain());
			planeta.setQuantidadeAparicoes(planet.getFilms().size());

			this.service.adicionarPlaneta(planeta);
		}
	}
}