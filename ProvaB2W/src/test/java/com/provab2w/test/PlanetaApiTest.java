package com.provab2w.test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.provab2w.model.Planeta;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PlanetaApiTest {

	final String url = "http://localhost:8080/api";

	Planeta planeta;
	String id = "/5ed18ba3086a52288b3047d4";
	RestTemplate template = new RestTemplate();

	@Test
	public void adicionarPlaneta() {
		planeta = new Planeta("1,", "Terra", "Tropical", "Populoso", 1);
		ResponseEntity<String> response = this.template.postForEntity(this.url, this.planeta, String.class);
		Assert.assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void buscarTodos() {
		ResponseEntity<String> response = this.template.getForEntity(this.url, String.class);
		Assert.assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void buscarPorId() {
		ResponseEntity<String> response = this.template.getForEntity(this.url + this.id, String.class);
		Assert.assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void deletarPorId() {
		ResponseEntity<String> response = this.template.getForEntity(this.url + id, String.class);
		Assert.assertEquals(200, response.getStatusCodeValue());
		
		ResponseEntity<String> result = template.exchange(this.url + this.id, 
				HttpMethod.DELETE, getHeader(), String.class);
		Assert.assertEquals(200, result.getStatusCodeValue());
	}
	
	private HttpEntity<String> getHeader() {
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		return entity;
	}
	
	@Test
	public void buscarPlanetasSwapi() {
		ResponseEntity<String> response = this.template.getForEntity(this.url + "/planets", String.class);
		Assert.assertEquals(200, response.getStatusCodeValue());
	}
}
