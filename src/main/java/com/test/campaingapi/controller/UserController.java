package com.test.campaingapi.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
import com.test.campaingapi.model.User;
import com.test.campaingapi.repository.CampaignRepository;
import com.test.campaingapi.repository.UserRepository;


@RestController
@RequestMapping("/socio-torcedor")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CampaignRepository campaignRepository;

	/**
	 * Show all the on going Users
	 */
	@GetMapping
	Iterable<User> index() {
		return userRepository.findAll();
	}

	/** 
	 * Create a new user and associate with campaigns by his team
	 * @param user
	 * @return
	 */
	@PostMapping
	Map<String, Object> associate(@RequestBody User requester) {
		/* Not the best way, but prevents null fields accidentally */ 
		User user = userRepository.findOne(requester.getEmail());
		boolean isNewUser = false;
		if (user == null) {
			user = userRepository.save(requester);			
			isNewUser = true;
		}
		
		Set<Campaign> campaigns = campaignRepository.findOnGoingByTeamId(user.getTeam().getId());
		user.associate(campaigns);
		
		Map<String, Object> response = new HashMap<>();
		String messageFormat = isNewUser ?
				"Seja bem vindo, %s! Associamos você às campanhas do seu time do coração." :
				"Bem vindo de volta, %s! As novas campanhas já foram associadas à você ;)";
			
		
		response.put("message", String.format(messageFormat, user.getName()));
		response.put("campaigns", user.getCampaigns());
		
		return response;
	}

	/**
	 * Update a user.
	 *
	 * @param user
	 * @param newUser
	 * @return
	 */
	@PutMapping("{user}")
	ResponseEntity<User> update(@PathVariable User user, @RequestBody User newUser) {
		if (user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		if (newUser.getTeam() != null)
			user.setTeam(newUser.getTeam());

		if (newUser.getName() != null)
			user.setName(newUser.getName());
		
		if (newUser.getBirthday() != null)
			user.setBirthday(newUser.getBirthday());
		
		if (newUser.getEmail() != null)
			user.setEmail(newUser.getEmail());

		return new ResponseEntity<User>(userRepository.save(user), HttpStatus.OK);
	}

	/** 
	 * Soft delete an user by ID
	 *
	 * @param user
	 * @return
	 */
	@DeleteMapping("{user}")
	ResponseEntity<User> disable(@PathVariable User user) {
		if (user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		user.disable();
		userRepository.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
