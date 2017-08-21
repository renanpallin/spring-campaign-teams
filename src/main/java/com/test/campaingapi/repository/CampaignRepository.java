package com.test.campaingapi.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.test.campaingapi.model.Campaign;

public interface CampaignRepository extends CrudRepository<Campaign, Long> {

	@Query("FROM Campaign c WHERE c.name=:campaingName")
	public Iterable<Campaign> findByName(@Param("campaingName") String campaingName);
	
	@Query("FROM Campaign c WHERE c.start > CURRENT_DATE AND c.end < CURRENT_DATE")
	public Iterable<Campaign> findActives(); 
	
	@Query("FROM Campaign c WHERE c.start > CURRENT_DATE")
	public Iterable<Campaign> findOnGoingCampaigns();
	
	@Query("FROM Campaign c WHERE :start BETWEEN c.start AND c.end OR :end BETWEEN c.start AND c.end ORDER BY c.createdAt DESC")
	public ArrayList<Campaign> findOnGoingCampaignsByDate(@Param("start") LocalDate start, @Param("end") LocalDate end);
	
	@Query("SELECT c FROM Campaign c JOIN c.team t WHERE t.id = :id AND c.start > CURRENT_DATE")
	public HashSet<Campaign> findOnGoingByTeamId(@Param("id") long id);
	
}
