package com.test.campaingapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.campaingapi.model.Campaign;
import com.test.campaingapi.repository.CampaignRepository;

@RestController
@RequestMapping("/campaign")
public class CampaignController {

	@Autowired
	private CampaignRepository campaignRepository;

	/**
	 * Show all the on going Campaigns 
	 */
	@GetMapping
	Iterable<Campaign> index() {
		return campaignRepository.findOnGoingCampaigns();
	}

	/**
	 * Show a campaign by ID
	 * @param campaign
	 * @return
	 */
	@GetMapping("{campaign}")
	ResponseEntity<Campaign> show(@PathVariable Campaign campaign) {
		if (campaign == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(campaign, HttpStatus.OK);
	}

	@PostMapping
	Campaign save(@RequestBody Campaign campaign) {
		List<Campaign> onGoingCampaignsByDate = campaignRepository.findOnGoingCampaignsByDate(campaign.getStart(),
				campaign.getEnd());

		int max = onGoingCampaignsByDate.size();
		for (Campaign c : onGoingCampaignsByDate) {
			c.addDayInTheEnd();
			
			int i = 0;
			while(i < max) {
				Campaign anotherCampaign = onGoingCampaignsByDate.get(i);
				if (anotherCampaign == c) {
					i++;
					continue; 
				}
				
				if (c.getEnd().equals(campaign.getEnd()) || c.getEnd().equals(anotherCampaign.getEnd())) {
					c.addDayInTheEnd();
					i = 0;
				} else {
					i++;
				}
			}
		}
		
		campaignRepository.save(onGoingCampaignsByDate);
		return campaignRepository.save(campaign);
	}

	/**
	 * Test method, use in develop only
	 */
//	@PostMapping("just-save")
//	Campaign justSave(@RequestBody Campaign campaign) {
//		return campaignRepository.save(campaign);
//	}

	/**
	 * Update a campaign.
	 * Obs: You can't update the start or end date
	 * 
	 * @param campaign
	 * @param newCampaign
	 * @return
	 */
	@PutMapping("{campaign}")
	ResponseEntity<Campaign> update(@PathVariable Campaign campaign, @RequestBody Campaign newCampaign) {
		if (campaign == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		if (newCampaign.getTeam() != null)
			campaign.setTeam(newCampaign.getTeam());
		
		if (newCampaign.getName() != null)
			campaign.setName(newCampaign.getName());
		
		return new ResponseEntity<Campaign>(campaignRepository.save(campaign), HttpStatus.OK);
	}

	/**
	 * Delete an campaign by ID
	 * 
	 * @param campaign
	 * @return
	 */
	@DeleteMapping("{campaign}")
	ResponseEntity<Campaign> destroy(@PathVariable Campaign campaign) {
		if (campaign == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		campaignRepository.delete(campaign);
		return new ResponseEntity<>(campaign, HttpStatus.OK);
	}

}
