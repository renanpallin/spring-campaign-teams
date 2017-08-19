package com.test.campaingapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.campaingapi.model.Campaign;
import com.test.campaingapi.model.Team;

@RestController
@RequestMapping("/campaign")
public class CampaignController {

	@RequestMapping()
	List<Campaign> index() {
		List<Campaign> campaigns = new ArrayList<>();
		
		int i = 10;
		while (i --> 0) {
			Campaign campaign = new Campaign();
			campaign.setId(i);
			campaign.setNome("Campanha linda " + i);
			
			Team team = new Team();
			team.setNome("Curintia " + i);
			campaign.setTeam(team);
			
			campaigns.add(campaign);
		}
		
		return campaigns;
	}
	
	@RequestMapping("{id}")
	int show(@PathVariable int id) {
		return id;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	Campaign store() {
		Campaign campaign = new Campaign();
		campaign.setId(564);
		campaign.setNome("Campanha linda do contorler hot reloooadd");
		
		Team team = new Team();
		team.setNome("Curintia hheheh");
		campaign.setTeam(team);
		
		return campaign;
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	Campaign update() {
		return new Campaign();
	}
	
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	Campaign destroy() {
		return new Campaign();
	}
		
}
