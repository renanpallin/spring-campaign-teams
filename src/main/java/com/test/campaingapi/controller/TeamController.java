package com.test.campaingapi.controller;

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

import com.test.campaingapi.model.Team;
import com.test.campaingapi.repository.TeamRepository;

@RestController
@RequestMapping("/team")
public class TeamController {

	@Autowired
	private TeamRepository teamRepository;

	@GetMapping
	Iterable<Team> index() {
		return teamRepository.findAll();
	}

	@GetMapping("{team}")
	ResponseEntity<Team> show(@PathVariable Team team) {
		if (team == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(team, HttpStatus.OK);
	}

	@PostMapping
	Team save(@RequestBody Team team) {
		return teamRepository.save(team);
	}

	@PutMapping("{team}")
	ResponseEntity<Team> update(@PathVariable Team team, @RequestBody Team newTeam) {
		if (team == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		newTeam.setId(team.getId());
		return new ResponseEntity<>(teamRepository.save(newTeam), HttpStatus.OK);
	}

	@DeleteMapping("{team}")
	ResponseEntity<Team> destroy(@PathVariable Team team) {
		if (team == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		teamRepository.delete(team);
		return new ResponseEntity<>(team, HttpStatus.OK);
	}

}
