package com.test.campaingapi.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.campaingapi.model.Campaign;
import com.test.campaingapi.model.Team;
import com.test.campaingapi.repository.CampaignRepository;
import com.test.campaingapi.repository.TeamRepository;

@RestController
@RequestMapping("/mock")
public class MockController {

	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private CampaignRepository campaignRepository;
	
	@GetMapping
	public String mock() {
		mockTeams();
		mockCampaing();
		
		return "Mock inserido no banco com sucesso =)";
	}
	
	private void mockCampaing() {
		Campaign c1 = new Campaign();
		c1.setName("Campanha 1");
		c1.setTeam(teamRepository.findOne(1l));
		c1.setStart(LocalDate.of(2017, 10, 01));
		c1.setEnd(LocalDate.of(2017, 10, 03));
		
		Campaign c2 = new Campaign();
		c2.setName("Campanha 2");
		c2.setTeam(teamRepository.findOne(2l));
		c2.setStart(LocalDate.of(2017, 10, 01));
		c2.setEnd(LocalDate.of(2017, 10, 02));
		
		
		new ArrayList<Campaign>(){
			private static final long serialVersionUID = 1L;
		{
			add(c1);
			add(c2);
		}}.forEach(c -> {
			campaignRepository.save(c);
		});
	}

	private void mockTeams() {
		String[] teamNames = {
			"Corinthians",
			"Palmeiras",
			"Vasco",
			"Santos",
			"São Paulo",
			"Cruzeiro",
			"Flamengo"
		};
		
		for (String teamName : teamNames) {
			Team team = new Team();
			team.setName(teamName);
			
			teamRepository.save(team);
		}
	}
}
