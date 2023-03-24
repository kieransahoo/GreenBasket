package com.masai.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserSession {
	
	@Id
	@Column(unique = true)
	private Integer id;
	private String uuid;
	private LocalDateTime localDateTime;

	private Integer role;

	public CurrentUserSession() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CurrentUserSession(Integer id, String uuid, LocalDateTime localDateTime, Integer role) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.localDateTime = localDateTime;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}



}
