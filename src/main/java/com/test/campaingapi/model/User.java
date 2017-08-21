package com.test.campaingapi.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Where(clause = "is_active = 1")
public class User {

	@Id
	@Email
	private String email;

	@GeneratedValue
	private String name;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthday;

	@ManyToOne
	@NotNull
	private Team team;

	@ManyToMany
	private Set<Campaign> campaigns = new HashSet<>();

	@JsonIgnore
	@Column(name = "is_active")
	private Boolean active = true;

	public void disable() {
		this.active = false;
	}
	
	public boolean associate(Set<Campaign> campaigns) {
		return getCampaigns().addAll(campaigns);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Set<Campaign> getCampaigns() {
		return campaigns;
	}

}
