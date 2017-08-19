package com.test.campaingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.campaingapi.model.Campaign;
import com.test.campaingapi.model.Team;

@RestController
@SpringBootApplication
public class CampaignApiApplication {

	@RequestMapping("/")
	public String hello() {
		return "hello!!!";
	}
	
	@RequestMapping("/campaign")
	Campaign campaign() {
		Campaign campaign = new Campaign();
		campaign.setId(564);
		campaign.setNome("fuuck");
		
		Team team = new Team();
		team.setNome("Curintia");
		campaign.setTeam(team);
		
		return campaign;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CampaignApiApplication.class, args);
	}
}
