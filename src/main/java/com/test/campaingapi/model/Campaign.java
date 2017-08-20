package com.test.campaingapi.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Campaign {

	@Id
	@GeneratedValue
	private long id;
	private String name;

//	@JsonProperty("team_id")
//	@JsonUnwrapped 
	@OneToOne
	private Team team;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate start;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate end;
	
	@JsonIgnore
	private LocalDateTime createdAt = LocalDateTime.now();
	
//	@JsonGetter("team_id") 
//	public Long getTeamId() {
//		if (team == null)
//			return null;
//		return team.getId();
//	}
	
	public void addDayInTheEnd() {
		setEnd(getEnd().plusDays(1));
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Campaign [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", team=");
		builder.append(team);
		builder.append(", start=");
		builder.append(start);
		builder.append(", end=");
		builder.append(end);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append("]");
		return builder.toString();
	}

	
	// public String getStart() {
	// if (start == null)
	// return null;
	// return start.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	// }
	//
	// public void setStart(String start) {
	// this.start = this.createLocalDate(start);
	// }
	//
	// public String getEnd() {
	// if (end == null)
	// return null;
	// return end.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	// }
	//
	// public void setEnd(String end) {
	// this.end = this.createLocalDate(end);
	// }

	/**
	 * Conver brazilian date pattern to LocalDate
	 * 
	 * @param date
	 * @return LocalDate
	 */
	// private LocalDate createLocalDate(String date) {
	// int[] dates =
	// Stream.of(date.split("/")).mapToInt(Integer::parseInt).toArray();
	// LocalDate localDate = LocalDate.of(dates[2], dates[1], dates[0]);
	// // System.out.println("DATE:::" + localDate);
	// return localDate;
	// }

}
