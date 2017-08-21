package com.test.campaingapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * Create a new user
	 * @param user
	 * @return
	 */
	@PostMapping
	Map<String, Object> save(@RequestBody Map<String, Object> payload) {
		User u = userRepository.findOne(payload.get("email").toString());

		Map<String, Object> response = new HashMap<>();
		
		if (u != null) {
			List<Campaign> campaigns = campaignRepository.findOnGoingByTeamId(u.getTeam().getId());

			response.put("campaigns", campaigns);
			
			/**
			 * TODO: Checar se o usuári opossui campanhas associadas, a mensagem abaixo é para o caso de não ter.
			 * Caso tenha, vamos enviar as já associadas por ele
			 */
			if (!campaigns.isEmpty())
				response.put("message", String.format("Olá, %s! Seu cadastro já foi efetuado e "
						+ "você não está associado a nenhuma campanha. "
						+ "Aqui está uma lista de campanhas do seu time do coração! "
						+ "Você pode se cadastrar com um POST no endpoint /campaign/subscribe, "
						+ "enviando no body um JSON com o campaign_id e seu e-mail.", u.getName()));
			else
				response.put("message", String.format("Olá, %s! Seu cadastro já foi efetuado e "
						+ "você não está associado a nenhuma campanha. "
						+ "Infelizmente não temos nenuma campanha do seu time do coração no momento =(. Mas continue ligado!", u.getName()));
		} else {
			response.put("message", String.format("Olá, %s! Seu cadastro foi efetuado com sucesso!"));
		}

		return response;
	}

	@PostMapping("add")
	User save(@RequestBody User user) {
		return userRepository.save(user);	
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
