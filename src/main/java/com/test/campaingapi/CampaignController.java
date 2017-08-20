package com.test.campaingapi;

import java.time.LocalDate;

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
	
	@GetMapping("test")
	LocalDate test() {
		return LocalDate.of(2015, 05, 05);
	}

	@GetMapping
	Iterable<Campaign> index() {
		return campaignRepository.findAll();
	}
	
    @GetMapping("{campaign}")
	ResponseEntity<Campaign> show(@PathVariable Campaign campaign) {
		if (campaign == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(campaign, HttpStatus.OK);
	}

	@PostMapping
	Campaign save(@RequestBody Campaign campaign) {
		return campaignRepository.save(campaign);
	}

	@PutMapping("{campaign}")
	ResponseEntity<Campaign> update(@PathVariable Campaign campaign, @RequestBody Campaign newCampaign) {
		if (campaign == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		newCampaign.setId(campaign.getId());
		return new ResponseEntity<>(campaignRepository.save(newCampaign), HttpStatus.OK);
	}

	@DeleteMapping("{campaign}")
	ResponseEntity<Campaign> destroy(@PathVariable Campaign campaign) {
		if (campaign == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		campaignRepository.delete(campaign);
		return new ResponseEntity<>(campaign, HttpStatus.OK);
	}

}
