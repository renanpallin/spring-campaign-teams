package com.test.campaingapi.model;

public class Campaign {

	private long id;
	private String nome;
	private Team team;

	// private DateRange dataVigencia;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}
