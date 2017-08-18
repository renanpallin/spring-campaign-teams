package com.test.campaingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CampaignApiApplication {

	@RequestMapping("/")
	public String hello() {
		return "hello!!!";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CampaignApiApplication.class, args);
	}
}
