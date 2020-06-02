package com.provab2w.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document
public class Planeta {

	@Id
	private String id;

	@NotNull
	@JsonProperty(required = true)
	private String nome;

	@NotNull
	@JsonProperty(required = true)
	private String clima;

	@NotNull
	@JsonProperty(required = true)
	private String terreno;

	private int quantidadeAparicoes;

	public Planeta() {
		super();
	}

	public Planeta(String id, String nome, String clima, String terreno, int quantidadeAparicoes) {
		super();
		this.id = id;
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
		this.quantidadeAparicoes = quantidadeAparicoes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public int getQuantidadeAparicoes() {
		return quantidadeAparicoes;
	}

	public void setQuantidadeAparicoes(int quantidadeAparicoes) {
		this.quantidadeAparicoes = quantidadeAparicoes;
	}
}