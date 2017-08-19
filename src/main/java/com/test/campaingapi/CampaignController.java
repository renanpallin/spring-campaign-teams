package com.test.campaingapi;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.campaingapi.model.Campaign;
import com.test.campaingapi.model.CampaignRepository;

@RestController
@RequestMapping("/campaign")
public class CampaignController {

	@Autowired
	private CampaignRepository campaignRepository;

	@GetMapping
	Iterable<Campaign> index() {
		return campaignRepository.findAll();
	}

	@GetMapping("{campaign}")
	Campaign show(@PathVariable Campaign campaign) {
		return campaign;
	}

	@PostMapping
	Campaign save(@RequestBody Campaign campaign) {
		return campaignRepository.save(campaign);
	}

	@PutMapping("{campaign}")
	Campaign update(@PathVariable Campaign campaign, @RequestBody Campaign newCampaign, HttpServletResponse response) {
		if (campaign == null)
			return null;

		newCampaign.setId(campaign.getId());
		return campaignRepository.save(newCampaign);
	}

	@DeleteMapping("{campaign}")
	Campaign destroy(@PathVariable Campaign campaign) {
		if (campaign == null)
			return null;

		campaignRepository.delete(campaign);
		return campaign;
	}

}
