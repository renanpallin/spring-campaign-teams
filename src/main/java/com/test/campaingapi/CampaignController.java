package com.test.campaingapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.campaingapi.model.Campaign;
import com.test.campaingapi.model.Team;

@RestController
@RequestMapping("/campaign")
public class CampaignController {

	@RequestMapping("")
	Campaign campaign() {
		Campaign campaign = new Campaign();
		campaign.setId(564);
		campaign.setNome("Campanha linda do contorler hot reloooadd");
		
		Team team = new Team();
		team.setNome("Curintia");
		campaign.setTeam(team);
		
		return campaign;
	}
}
